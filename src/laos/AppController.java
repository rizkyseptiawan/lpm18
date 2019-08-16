/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laos;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author rizky
 */
public class AppController implements Initializable {
    
     @FXML
    private JFXButton buttonInstaller;

    @FXML
    private Pane pane;

    @FXML
    private AnchorPane AnchorPane;
    
    @FXML
    private Label judulApp;

    
    public homeController getStore(homeController param) {
        
        return param;
    }
    
//    private String getJudul(){
//        String title = komponen.getTitle();
//        return title;
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        
        
    }    
    
}
;