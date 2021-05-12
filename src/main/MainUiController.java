/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import javafx.scene.Parent;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author nour_
 */
public class MainUiController implements Initializable {
    
    @FXML
    private AnchorPane content_area;
      private Button signupbutton;

    @FXML
    public JFXTextField usernamesignup;

    @FXML
    public  JFXTextField emailsignup;

    @FXML
    public  JFXPasswordField passwordsignup;

    @FXML
    public  Button loginbutton;

    @FXML
    public  JFXTextField usernameid;

    @FXML
    public  JFXPasswordField passowrdid;

    @FXML
    public  Label label;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        DatabaseClient database = new DatabaseClient();
    }
   @FXML
   //javafx.event.Event event
    public void signup(MouseEvent event) {
     DatabaseClient database = new DatabaseClient();
       
        String a = usernamesignup.getText();
        String b = emailsignup.getText();
        String c = passwordsignup.getText();
        String labelans =database.databasework(a,b,c);
     
             //System.out.print(a + " " + b + " "+  c+ " ");
               label.setText(labelans);
               System.out.print(labelans + "y nour  ");
    }
      @FXML
    public void loginLaunch(MouseEvent event) throws SQLException, IOException {
        DatabaseClient database = new DatabaseClient();
        String a = usernameid.getText();
        String c = passowrdid.getText();
        boolean vailed =database.checkpassword(a,c);
        if (!vailed)
        {
           label.setText("username or password are wrong ");
        }
        else {
        Parent fxml = FXMLLoader.load(getClass().getResource("nono.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        }
     
    }
     
    @FXML
    public void close_app(MouseEvent event) {
        System.exit(0);
    }
 
}
