
package main;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nour_
 */
public class roomController implements Initializable {

    @FXML
    private AnchorPane content;

      @FXML
    private TextField roomid;

    @FXML
    private ImageView joinroom;

    @FXML
    private TextField newroomid;

    @FXML
    private Label label2;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  @FXML
    public void joinnewroom(MouseEvent eventt) throws SQLException, IOException {
        DatabaseClient database = new DatabaseClient();
        
         String roomname=newroomid.getText();
        boolean vailed =database.createroom(roomname);
        if(!vailed){
          String Message="$#$"; 
          Message+=roomname;
          Client client = new Client("nour","127.0.0.1",8090);
          client.send(Message);
          Parent fxml2 = FXMLLoader.load(getClass().getResource("chat window.fxml"));
          content.getChildren().removeAll();
          content.getChildren().setAll(fxml2);
        }
        else {
            
            label2.setText("room with this name already exist");
        }
    }

    @FXML
    public void joinroom(MouseEvent event) throws IOException, SQLException {
        DatabaseClient database = new DatabaseClient();
       
        String roomname=roomid.getText();
        boolean vailed =database.checkroom(roomname);
        if(vailed){
         String Message="$#$"; 
         Message+=roomname;
         System.out.println(Message);
         Client client = new Client("nour","127.0.0.1",8090);
         client.send(Message);
         client.send(Message);
         Parent fxml2 = FXMLLoader.load(getClass().getResource("chat window.fxml"));
         content.getChildren().removeAll();
         content.getChildren().setAll(fxml2);
        }
        else {
            label2.setText("no room with this name ");
        }
        
    }    

    @FXML
    private void chat_window(MouseEvent event) throws IOException {
    Parent fxml2 = FXMLLoader.load(getClass().getResource("chat window.fxml"));
    content.getChildren().removeAll();
    content.getChildren().setAll(fxml2);
    }
}
