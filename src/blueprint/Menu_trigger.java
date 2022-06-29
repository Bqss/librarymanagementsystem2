package blueprint;


import javafx.scene.input.MouseEvent;

public interface Menu_trigger {
    void goTobookMenu(MouseEvent e) throws Exception;
    void goToBorrowMenu(MouseEvent e) throws Exception;
    void goToMemberMenu(MouseEvent e) throws Exception;
    void goToAdminMenu(MouseEvent e) throws Exception;
    void logOut(MouseEvent e);
}
