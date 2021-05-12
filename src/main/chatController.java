package main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class chatController extends MainUiController {

    @FXML
    private Label mid;

    @FXML
    private TextField type;

    @FXML
    private Button sendid;

    @FXML
    public void sendm(MouseEvent event) throws FileNotFoundException {
        File file = new File("admin.txt"); 
        String m = "";
        Scanner sc = new Scanner(file); 
         while (sc.hasNextLine()){
          m+=sc.nextLine();
          m+="\n";
              } 
        System.out.println(m);
        mid.setText(m); 
        String Message=type.getText();
        mid.setText(m);
        DatabaseClient d = new DatabaseClient();
        String s = d.saymyname();
        System.out.println(s+" "+ "check");
        Client client = new Client("nour","127.0.0.1",8090);
        client.send(Message);

    }

}
