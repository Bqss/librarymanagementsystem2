package assets.controllers;

import entity.Buku;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;


public class BookUpdate_controller implements Initializable   {

    @FXML
    private TextField tf_Isbn,tf_judul,tf_penulis,tf_publisher;
    @FXML
    private ChoiceBox<String> choicebox;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private DatePicker datepicker;

    private Buku buku;
    private String [] kategoriList = {"Novel","Pendidikan","Tekhnologi","Manga","Peternakan","LifeStyle"};

    public BookUpdate_controller(Buku buku){
        this.buku = buku;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choicebox.getItems().setAll(kategoriList);
        tf_Isbn.setText(buku.getIsbn());
        tf_judul.setText(buku.getJudul());
        tf_penulis.setText(buku.getNamaPenulis());
        tf_publisher.setText(buku.getPublisher());
        choicebox.getSelectionModel().select(buku.getKategori());
        SpinnerValueFactory<Integer> fc = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        fc.setValue(buku.getStock());
        spinner.setValueFactory(fc);
        datepicker.setValue(convert(buku.getTanggalterbit()));

    }

    private LocalDate convert(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
    public Buku getData(){
        String isbn,judul,penulis,publisher,kategori;
        int jumlah;
        String tanggalPublish;




        isbn = tf_Isbn.getText();
        judul = tf_judul.getText();
        penulis = tf_penulis.getText();
        publisher = tf_publisher.getText();
        kategori = choicebox.getItems().get(choicebox.getSelectionModel().getSelectedIndex());
        jumlah = spinner.getValue();
        tanggalPublish = datepicker.getValue().toString();
        Buku buku =new Buku(isbn,judul,penulis,publisher,kategori,jumlah,tanggalPublish,jumlah);
        buku.setId(this.buku.getId());
        return buku;


    }

    public boolean checkIsNull(){
        if(tf_Isbn.getText() ==null || Objects.equals(tf_judul.getText(), null) || tf_penulis.getText()==null||tf_publisher.getText()==null||choicebox.getSelectionModel().getSelectedIndex()==-1|| spinner.getValue()==0 || datepicker.getValue()==null){

            return true;
        }
        return false;
    }


}
