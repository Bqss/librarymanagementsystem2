package util;

import blueprint.Crud_Abstract;
import blueprint.Login_In;
import entity.Admin;
import entity.Buku;
import entity.PeminjamanBuku;
import koneksi.KoneksiDB;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crud extends Crud_Abstract implements Login_In {
    private Connection koneksi ;
    private PreparedStatement prstt;
    private ResultSet dataset;
    private Admin admin ;


    public Crud(){
        KoneksiDB tryT0  = new KoneksiDB("libraryManagementSystem","root","");
        try{
            koneksi = tryT0.getKoneksi();
        }catch(Exception e){
            System.out.println("gagal koneksi ke db");
        }
    }

    public boolean cekLogin(String username , String password){

        String query = "select nama from administrator where username=? and password=?";
        try{

            prstt = koneksi.prepareStatement(query);
            prstt.setString(1,username);
            prstt.setString(2,password);
            dataset = prstt.executeQuery();

            return dataset.next() ;
        }catch(SQLException e){

            System.out.println(e);
            return false;
        }

    }


    @Override
    public void update(String statament , Object a,String entityName) {
        if (entityName.equals("buku")){
            Buku buku = (Buku)a;
            try{
                prstt = koneksi.prepareStatement(statament);
                prstt.setString(1,buku.getIsbn());
                prstt.setString(2,buku.getJudul());
                prstt.setString(3,buku.getNamaPenulis());
                prstt.setString(4,buku.getPublisher());
                prstt.setString(5,buku.getKategori());
                prstt.setInt(6,buku.getStock());
                prstt.setString(7,buku.getTanggalterbit());
                prstt.setInt(8,buku.getId());
                System.out.println(buku.getId());

                prstt.executeUpdate();
                System.out.println("berhasil mengupdate");
            }catch(SQLException e){
                System.out.println("\ngagal mengupdate data"+e);
            }

        }else if(entityName.equals("buku2")){
           try{
               prstt = koneksi.prepareStatement(statament);
               prstt.executeUpdate();
           }catch(SQLException e){
               System.out.println("error "+e);
           }

        }else if(entityName.equals("peminjaman")){
            try{
                prstt = koneksi.prepareStatement(statament);
                prstt.setString(1,"sudah dikembalikan");
                prstt.executeUpdate();
            }catch(Exception e){
                System.out.println(e);
            }
        }


    }

    @Override
    public void delete(String statament ,Object a) {
        Buku buku = (Buku) a ;
        try{
            prstt = koneksi.prepareStatement(statament);
            prstt.setInt(1,buku.getId());
            prstt.executeUpdate();
            JOptionPane.showMessageDialog(null,"berhasil menghapus data");
        }catch(Exception e){
            System.out.println("gagal mengapus data karena "+e);
        }

    }

    @Override
    public void add(String statement, Object a,String entityName) {
        if (entityName.equals("buku")) {
            Buku buku = (Buku) a;
            try {
                prstt = koneksi.prepareStatement(statement);
                prstt.setString(1, buku.getIsbn());
                prstt.setString(2, buku.getJudul());
                prstt.setString(3, buku.getNamaPenulis());
                prstt.setString(5, buku.getKategori());
                prstt.setString(4, buku.getPublisher());
                prstt.setInt(6, buku.getStock());
                prstt.setString(7, buku.getTanggalterbit());
                prstt.setInt(8,buku.getTersisa());
                prstt.executeUpdate();
                System.out.println("berhasil menambahkan buku");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if(entityName.equals("peminjamanbuku")){

            System.out.println("di run");
            PeminjamanBuku pjm = (PeminjamanBuku) a;
            System.out.println(pjm.getIdMember());
            System.out.println(pjm.getIdBuku());
            System.out.println(pjm.getIdBuku());
            System.out.println(pjm.getIdBuku());
            try {
                prstt = koneksi.prepareStatement(statement);
                prstt.setInt(1,pjm.getIdBuku());
                prstt.setString(2,pjm.getIdMember());
                prstt.setString(3,pjm.getTanggalPeminjaman());
                prstt.setString(4,pjm. getStatusPeminjaman());
                prstt.executeUpdate();

                System.out.println("berhasil menambahkan");
            }catch (Exception e) {
                System.out.println("query error"+e);
            }

        }
    }

    @Override
    public ResultSet getAll(String statament) {

        try{

            prstt = koneksi.prepareStatement(statament);
            dataset = prstt.executeQuery();

        }catch (SQLException e){
            System.out.println("gagal mengambil semua data"+e);
        }
        return dataset;

    }

    public ResultSet get(String statament ) {

        try {

            prstt = koneksi.prepareStatement(statament);

            dataset = prstt.executeQuery();
        }catch(Exception e) {
            System.out.println(e);
        }
        return dataset;

    }
}
