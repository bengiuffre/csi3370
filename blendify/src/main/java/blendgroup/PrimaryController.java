package blendgroup;

import java.io.IOException;
import javafx.fxml.FXML;


public class PrimaryController {

    String[] lists = {"wow!", "cool!", "amazing!"};
    String textItem = "";


    @FXML
    private void up() throws IOException {
        System.out.println("Hewwo");
    }
   @FXML
    private void addList() throws IOException {
        System.out.println(lists[0]);
    }
    @FXML
    private void showList() throws IOException {
        for (String list : lists){
            System.out.println(list);
        }
    @FXML
    private void enterItem() throws IOException {
        for (String list : lists){
            System.out.println(list);
        }
    }
    }
}
