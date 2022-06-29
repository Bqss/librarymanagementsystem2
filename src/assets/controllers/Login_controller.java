package assets.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Crud;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Login_controller {
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;

    final private Crud a = new Crud() ;

    public void login(ActionEvent event) throws IOException {
        boolean canILogin  ;
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        canILogin = a.cekLogin(username,password);
        FXMLLoader loader = new FXMLLoader();
        Parent main = loader.load(Objects.requireNonNull(getClass().getResource("../FXMLS/main/Menu.fxml")));
        String css = Objects.requireNonNull(this.getClass().getResource("../style.css")).toExternalForm();

        main.getStylesheets().add(css);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


        if(canILogin) {
            stage.setScene(new Scene(main));
        }else{
            JOptionPane.showMessageDialog(null,"username atau password salah");
        }

    }

}
