package assets.controllers;

import blueprint.Back_btn;
import blueprint.BooksRepository;
import entity.Buku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Crud;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class Books_controller extends Back_btn implements Initializable, BooksRepository {
    @FXML
    private TextField tfSearch;
    @FXML
    private ChoiceBox<String> choicebox;
    @FXML
    private TableView<Buku> table;
    @FXML
    private TableColumn<Buku,String> tc_id ,tc_namabuku,tc_penulis,tc_publisher,tc_kategori,tc_isbn;
    @FXML
    private TableColumn<Buku,Integer> tc_jumlahbuku,tc_tersisa;
    @FXML
    private TableColumn<Buku, LocalDate> tc_tahunpublish;

    private ResultSet data;
    private final String [] searchOption = {" Judul Buku "," Penulis "," Kategori","Publisher"};
    private ObservableList<Buku> bukus  = FXCollections.observableArrayList();
    private Buku buku = new Buku();
    Crud a = new Crud();
    private int selectedIndex = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choicebox.getItems().setAll(searchOption);
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tc_namabuku.setCellValueFactory(new PropertyValueFactory<>("judul"));
        tc_penulis.setCellValueFactory(new PropertyValueFactory<>("namaPenulis"));
        tc_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tc_kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        tc_jumlahbuku.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tc_tersisa.setCellValueFactory(new PropertyValueFactory<>("tersisa"));
        tc_tahunpublish.setCellValueFactory(new PropertyValueFactory<>("tanggalterbit"));

        ambilSemuaBuku();
        addToTable();


    }
    public void addBook() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXMLS/dialog/Book_edit.fxml"));
        DialogPane root = loader.load();
        Dialog<ButtonType> dialog = new Dialog<>();
        String css = Objects.requireNonNull(Objects.requireNonNull(this.getClass().getResource("../style.css")).toExternalForm());
        root.getStylesheets().add(css);
        dialog.setDialogPane(root);
        Optional<ButtonType> result = dialog.showAndWait();
        checkIsNul(result,loader,dialog);

    }

    public void checkIsNul(Optional<ButtonType>result , FXMLLoader loader,Dialog<ButtonType>dialog){
        if(result.isPresent() && result.get() == ButtonType.OK){
            BookEdit_controller controller = loader.getController();
            if(controller.checkIsNull()){
                JOptionPane.showMessageDialog(null,"Sebagian data hilang , atau isbn terlalu panjang( maksimal 13 char)");
                checkIsNul(dialog.showAndWait(),loader,dialog);

            }else{
                JOptionPane.showMessageDialog(null,"berhasil menambahkan data");
                this.buku = controller.getData();
                tambahkanBuku();
            }
        }
    }
    public void checkForUpdate(Optional<ButtonType>result , FXMLLoader loader,Dialog<ButtonType>dialog) {
        if(result.isPresent() && result.get() == ButtonType.OK){
            BookUpdate_controller controller = loader.getController();
            if(controller.checkIsNull()){
                JOptionPane.showMessageDialog(null,"Sebagian data hilang , mohon isi kembali data");
                checkForUpdate(dialog.showAndWait(),loader,dialog);

            }else{
                this.buku = controller.getData();
                updateBuku();
            }
        }
    }

    public void setIndex(){
        this.selectedIndex = table.getSelectionModel().getSelectedIndex() ;
    }

    public void updateBook() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new BookUpdate_controller(table.getItems().get(selectedIndex)));
        loader.setLocation(getClass().getResource("../FXMLS/dialog/Book_update.fxml"));
        DialogPane root = loader.load();
        Dialog<ButtonType> dialog = new Dialog<>();
        String css = Objects.requireNonNull(Objects.requireNonNull(this.getClass().getResource("../style.css")).toExternalForm());
        root.getStylesheets().add(css);
        dialog.setDialogPane(root);
        Optional<ButtonType> result = dialog.showAndWait();
        checkForUpdate(result,loader,dialog);
    }



    @Override
    public void tambahkanBuku() {
        a.add("insert into buku (isbn,judul_buku,penulis,publisher,kategori,jumlah,tanggal_penerbitan,tersisa) values(?,?,?,?,?,?,?,?)",buku,"buku");
        deleteAllFromTable();
        ambilSemuaBuku();
        addToTable();
    }

    @Override
    public void updateBuku() {
        a.update("update buku set isbn=?,judul_buku=?,penulis=?,publisher=?,kategori=?,jumlah=?,tanggal_penerbitan =?  where id=? ",buku,"buku");
        table.getItems().set(selectedIndex,this.buku);

    }

    @Override
    public void hapusBuku() {
        if( this.selectedIndex ==-1){
            JOptionPane.showMessageDialog(null,"tolong pilih buku yang akan dihapus terlebih dahulu !!");
        }else{
            a.delete("delete from buku where id=?",table.getItems().get(this.selectedIndex));
            table.getItems().remove(this.selectedIndex);
            this.selectedIndex = -1;

        }

    }

    @Override
    public void ambilSemuaBuku() {
        data = a.getAll("select * from buku");
    }

    public void deleteAllFromTable(){
        bukus.clear();

        for(int i =0 ; i< table.getItems().size();i++){
            table.getItems().remove(1);
        }

    }
    private void addToTable(){
        try{

            while(data.next()){
                Buku dummy = new Buku();
                dummy.setId(data.getInt(1));
                dummy.setIsbn(data.getString(2));
                dummy.setJudul(data.getString(3));
                dummy.setPublisher(data.getString(5));
                dummy.setKategori(data.getString(6));
                dummy.setStock(data.getInt(7));
                dummy.setNamaPenulis(data.getString(4));
                dummy.setTanggalterbit( data.getString(8));
                dummy.setTersisa(data.getInt(9));
                bukus.add(dummy);

            }
            table.getItems().setAll(bukus);
            data = null;
        }catch(Exception e){
            System.out.println("data tidak ditemukan");
            System.out.println(e);
        }
    }

}
