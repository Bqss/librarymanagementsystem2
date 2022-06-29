package assets.controllers;

import blueprint.Back_btn;
import blueprint.PeminjamanRepository;
import entity.Buku;
import entity.Member;
import entity.PeminjamanBuku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Crud;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Borrow_controller extends Back_btn implements PeminjamanRepository, Initializable {

    @FXML
    private TableView<PeminjamanBuku> tabelPeminjaman;
    @FXML
    private TableColumn<PeminjamanBuku,Integer> tc_idBuku , tc_idPeminjaman;
    @FXML
    private TableColumn<PeminjamanBuku,String> tc_judulBuku ,tc_idMember,tc_namaMember,tc_tanggalPeminjaman ,tc_statusPeminjaman;

    @FXML
    private TableView<Buku>  tabel_daftarBuku ;
    @FXML
    private TableColumn<Buku,Integer> tc_idBuku2;
    @FXML
    private TableColumn<Buku,String> tc_judulBuku2 , tc_namaPenulis2 , tc_tahunterbit2 , tc_stockBuku2;

    @FXML
    private TableView<Member> tabel_member;
    @FXML
    private TableColumn<Member, String> tc_idMember2, tc_namaMember2;

    @FXML
    private TableView<PeminjamanBuku> pengembalian;
    @FXML
    private TableColumn<PeminjamanBuku, Integer> tc_idPeminjaman3;
    @FXML
    private TableColumn<PeminjamanBuku,String> tc_judulBuku3, tc_tanggalPeminjaman3, tc_statusBuku3;

    @FXML
    private TextField tf_idbuku,tf_idpeminjam1,tf_idPeminjam2;
    Crud a = new Crud();

    private ResultSet data ;
    private ResultSet data2;
    private ResultSet small;
    private ObservableList<Buku> bukus = FXCollections.observableArrayList();
    private ObservableList<Member> members  = FXCollections.observableArrayList();
    private ObservableList<PeminjamanBuku> peminjamans =  FXCollections.observableArrayList();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tc_idPeminjaman.setCellValueFactory(new PropertyValueFactory<>("idPeminjaman"));
        tc_idBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        tc_idMember.setCellValueFactory(new PropertyValueFactory<>("idMember"));
        tc_namaMember.setCellValueFactory(new PropertyValueFactory<>("namaPeminjam"));
        tc_tanggalPeminjaman.setCellValueFactory(new PropertyValueFactory<>("tanggalPeminjaman"));
        tc_judulBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        tc_statusPeminjaman.setCellValueFactory(new PropertyValueFactory<>("statusPeminjaman"));

        tc_idBuku2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_judulBuku2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        tc_namaPenulis2.setCellValueFactory(new PropertyValueFactory<>("namaPenulis"));
        tc_tahunterbit2.setCellValueFactory(new PropertyValueFactory<>("tanggalterbit"));
        tc_stockBuku2.setCellValueFactory(new PropertyValueFactory<>("tersisa"));

        tc_idMember2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_namaMember2.setCellValueFactory(new PropertyValueFactory<>("nama"));

        tc_idPeminjaman3.setCellValueFactory(new PropertyValueFactory<>("IdPeminjaman"));
        tc_judulBuku3.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        tc_tanggalPeminjaman3.setCellValueFactory(new PropertyValueFactory<>("tanggalPeminjaman"));
        tc_statusBuku3.setCellValueFactory(new PropertyValueFactory<>("StatusPeminjaman"));

        getAllData();
        addPeminjamanToTable();

    }

    @Override
    public void buatPeminjaman() {
        if(tabel_member.getSelectionModel().getSelectedIndex()==-1 || tabel_daftarBuku.getSelectionModel().getSelectedIndex()==-1 ){
            JOptionPane.showMessageDialog(null,"tolong pilih id buku atau juga youtuber 1 lagi");

        }else{

            PeminjamanBuku dummy = new PeminjamanBuku();
            dummy.setIdBuku(Integer.parseInt(tf_idbuku.getText()));
            int sisa = getSisa(dummy.getIdBuku());
            kurangiStok(sisa,dummy.getIdBuku());
            dummy.setIdMember(tf_idpeminjam1.getText());
            dummy.setTanggalPeminjaman(LocalDate.now().toString());
            dummy.setStatusPeminjaman("Sedang di pinjam");
            a.add("insert into peminjamanbuku (idBuku,idMember,tanggalPeminjaman,statusPeminjaman) values(?,?,?,?)",dummy,"peminjamanbuku");
            refreshPeminjamanBuku();
            JOptionPane.showMessageDialog(null,"berhasil membuat peminjaman");
        }

    }

    @Override
    public void buatPengembalian() {
        if(pengembalian.getSelectionModel().getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null,"tolong pilih buku yang akan dikembalikan");
        }else{
            updateStatus(Integer.parseInt(tf_idPeminjam2.getText()));
            int idbuku = pengembalian.getItems().get(pengembalian.getSelectionModel().getSelectedIndex()).getIdBuku();
            int sisa = getSisa(idbuku);
            tambahStok(sisa,idbuku);

        }
        refreshPeminjamanBuku();
        JOptionPane.showMessageDialog(null,"berhasil mengembalikan buku");
    }

    @Override
    public void cariMember() {
        if(tf_idpeminjam1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"id tidak boleh kosong");
        }
        data = a.get(String.format("select idMember,namaMember from member where idMember=%s ",tf_idpeminjam1.getText()));
        tabel_member.getItems().removeAll();
        members.clear();
        tabel_daftarBuku.getItems().removeAll();
        tabelPeminjaman.getItems().removeAll();
        bukus.clear();
        addMemberToTable();
        cariBuku();
    }

    @Override
    public void cariBuku() {

        if(tf_idbuku.getText().equals("")){
            JOptionPane.showMessageDialog(null,"id buku tidak boleh kosong");
        }
        data = a.get(String.format("select id,judul_buku,penulis,tanggal_penerbitan,tersisa from buku where id=%s ",tf_idbuku.getText()));
        addBukuToTable();
    }
    public void getAllData(){
        data2 = a.getAll("select * from peminjamanbuku");

    }
    public void addPeminjamanToTable(){
        try{

            while(data2.next()){

                PeminjamanBuku dummy = new PeminjamanBuku();
                dummy.setIdPeminjaman(data2.getInt(1));
                dummy.setIdBuku(data2.getInt(2));
                dummy.setIdMember(data2.getString(3));
                dummy.setTanggalPeminjaman(data2.getString(4));
                dummy.setStatusPeminjaman(data2.getString(5));
                dummy.setNamaBuku(getMemberName(dummy.getIdMember()));
                dummy.setNamaPeminjam(getMemberName(dummy.getIdMember()));
                peminjamans.add(dummy);
            }
            tabelPeminjaman.getItems().setAll(peminjamans);
        }catch(Exception e){
            System.out.println("data tidak ditemukan");
            e.printStackTrace();
        }

    }

    public void addBukuToTable(){
        try{

            while(data.next()){
                Buku dummy = new Buku();
                dummy.setId(data.getInt(1));
                dummy.setJudul(data.getString(2));
                dummy.setNamaPenulis(data.getString(3));
                dummy.setTanggalterbit( data.getString(4));
                dummy.setTersisa(data.getInt(5));
                bukus.add(dummy);

            }
            tabel_daftarBuku.getItems().setAll(bukus);
            data = null;
        }catch(Exception e){
            System.out.println("data tidak ditemukan");
            System.out.println(e);
        }
    }

    public void addMemberToTable(){
        try{

            while(data.next()){
                Member dummy = new Member();
                dummy.setId(data.getString(1));
                dummy.setNama(data.getString(2));
                members.add(dummy);
            }
            tabel_member.getItems().setAll(members);
            data = null;
        }catch(Exception e){
            System.out.println("data tidak ditemukan");
            System.out.println(e);
        }
    }
    String getMemberName(String id){

        data = a.get(String.format("select namaMember from member where idMember=%s " ,id));
        try{
            if(data.next()){

                return data.getString(1);

            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    public int  getSisa (int id){
        int tersisa = 0;
        small = a.get(String.format("select tersisa from buku where id=%d",id));
        try{
              while(small.next()){
                  tersisa = small.getInt(1);
              }
        }catch(Exception e){
            System.out.println("data tidak ditemukan");
        }
        return tersisa;

    }

    public void kurangiStok(int stoklama , int id){
        a.update(String.format("update buku set tersisa=%d where id=%d",stoklama-1,id),new Buku(),"buku2");

        bukus.clear();
    }
    public void refreshPeminjamanBuku(){
        peminjamans.clear();
        getAllData();
        addPeminjamanToTable();
        tabelPeminjaman.getItems().removeAll();
    }
    public void cariPeminjaman(){
        data = a.getAll(String.format("select * from peminjamanbuku where idPeminjaman=%d",Integer.parseInt(tf_idPeminjam2.getText())));
        peminjamans.clear();

        PeminjamanBuku pjm = new PeminjamanBuku();
        try{
            while(data.next()){
                pjm.setIdPeminjaman(data.getInt(1));
                pjm.setIdBuku(data.getInt(2));
                pjm.setNamaBuku(ambilNamaBuku(pjm.getIdBuku()));
                pjm.setTanggalPeminjaman(data.getString(4));
                pjm.setStatusPeminjaman(data.getString(5));
                peminjamans.add(pjm);

            }
            pengembalian.getItems().setAll(peminjamans);
            peminjamans.clear();
        }catch (Exception e){
            System.out.println("errror"+e);
        }

    }
    public String ambilNamaBuku(int id){

        small = a.get(String.format("select judul_buku from buku where id=%d",id));
        String nama = "";
        try{
            while(small.next()){
                nama = small.getString(1);
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return nama;
    }
    public void updateStatus(int id ){
        a.update(String.format("update peminjamanbuku set statusPeminjaman=? where idPeminjaman=%d",id),new PeminjamanBuku(),"peminjaman");

    }
    public void tambahStok(int stoklama , int id){

        a.update(String.format("update buku set tersisa=%d where id=%d",stoklama+1,id),new Buku(),"buku2");

        bukus.clear();
    }






}
