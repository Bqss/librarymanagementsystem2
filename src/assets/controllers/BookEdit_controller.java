package assets.controllers;


import entity.Buku;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class BookEdit_controller implements Initializable {

    @FXML
    private TextField tf_Isbn,tf_judul,tf_penulis,tf_publisher;
    @FXML
    private ChoiceBox<String> choicebox;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private DatePicker datepicker;


    private String [] kategoriList = {"Novel","Pendidikan","Tekhnologi","Manga","Peternakan","LifeStyle"};


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
        return new Buku(isbn,judul,penulis,publisher,kategori,jumlah,tanggalPublish,jumlah);


    }
    public boolean checkIsNull(){
        if(tf_Isbn.getText() ==null || Objects.equals(tf_judul.getText(), null) || tf_penulis.getText()==null||tf_publisher.getText()==null||choicebox.getSelectionModel().getSelectedIndex()==-1|| spinner.getValue()==0 || datepicker.getValue()==null){
            if(tf_Isbn.getText().length()> 13){
                return false;
            }
            return true;
        }
        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choicebox.getItems().setAll(kategoriList);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        valueFactory.setValue(0);
        spinner.setValueFactory(valueFactory);

    }
}
