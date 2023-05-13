package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;




public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private AnchorPane listaAnteproyectos;
    @FXML
    private AnchorPane registroAnteproyectos;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnOpcion2(ActionEvent event) {
        registroAnteproyectos.setVisible(false);
        listaAnteproyectos.setVisible(true);
    }

    @FXML
    private void clicBtnOpcion1(ActionEvent event) {
        listaAnteproyectos.setVisible(false);
        registroAnteproyectos.setVisible(true);
    }
    
}
