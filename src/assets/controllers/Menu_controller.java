package assets.controllers;

import blueprint.Menu_trigger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;


public class Menu_controller implements Menu_trigger {
    private Stage stage ;
    private Parent root;


    private void setStage(MouseEvent l){
        this.stage = (Stage)((Node)l.getSource()).getScene().getWindow();
    }
    private void setLoader (String path){
        try{
            FXMLLoader loader  = new FXMLLoader();
            this.root = loader.load(Objects.requireNonNull(getClass().getResource(path)));

        }catch(Exception e){
            System.out.println(e);
        }


    }
    private void setScene(){
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
    }

    @Override
    public void goTobookMenu(MouseEvent e) throws Exception {
        String locat = "../FXMLS/menus/book_section.fxml";
        setStage(e);
        setLoader(locat);
        setScene();
    }

    @Override
    public void goToBorrowMenu(MouseEvent e) throws Exception {
        String locat = "../FXMLS/menus/borrow.fxml";
        setStage(e);
        setLoader(locat);
        setScene();
    }

    @Override
    public void goToMemberMenu(MouseEvent e) throws Exception {
        String locat = "../FXMLS/menus/member_section.fxml";
        setStage(e);
        setLoader(locat);
        setScene();
    }

    @Override
    public void goToAdminMenu(MouseEvent e) throws Exception {
        String locat = "../FXMLS/menus/administrator_section.fxml";
        setStage(e);
        setLoader(locat);
        setScene();

    }

    @Override
    public void logOut(MouseEvent e) {
        String locat = "../FXMLS/login/login_section.fxml";
        setStage(e);
        setLoader(locat);
        setScene();
    }
}
