package blendgroup;

import java.io.IOException;
import javafx.fxml.FXML;


public class PrimaryController {

    String[]lists = {};


    @FXML
    private void up() throws IOException {
        System.out.println("Hewwo");
    }
   @FXML
    private void addList() throws IOException {
        System.out.println(lists[0]);
    }
}
