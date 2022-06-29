package blueprint;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Back_btn {
    public void backToMenu(ActionEvent e) throws Exception{
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        String css = Objects.requireNonNull(this.getClass().getResource("../style.css")).toExternalForm();
        Parent root = loader.load(Objects.requireNonNull(getClass().getResource("../FXMLS/main/menu.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);

    }
}
