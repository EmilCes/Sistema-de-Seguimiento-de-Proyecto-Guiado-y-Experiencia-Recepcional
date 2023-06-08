package sspger.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sspger.utils.Utilidades;




public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private AnchorPane apMenuPrincipal;
<<<<<<< HEAD
<<<<<<< HEAD
    
    @FXML
    private AnchorPane apRegistrarUsuario;
   
=======
    @FXML
    private AnchorPane apMenuDirector;
>>>>>>> main
=======
    
    @FXML
    private AnchorPane apRegistrarUsuario;
    @FXML
    private AnchorPane apMenuDirector;
>>>>>>> 78d66ed7c2a6b3cbc2aa5ce98cacffc372d1c8f4
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
<<<<<<< HEAD

<<<<<<< HEAD
    
     @FXML
    private void clicBtnOpcion1(ActionEvent event) {
        apMenuPrincipal.getChildren().clear();
        apRegistrarUsuario.setVisible(true);
        cambiarPane("/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
    }
    
    @FXML
=======
>>>>>>> main
=======
    
    @FXML

>>>>>>> 78d66ed7c2a6b3cbc2aa5ce98cacffc372d1c8f4
    private void clicBtnOpcion2(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLRegistrarUsuario.fxml");
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @FXML
    private void clicBtnOpcion3(ActionEvent event) {
       apMenuPrincipal.getChildren().clear();
        cambiarPane("/sspger/vistas/FXMLCrearActividad.fxml");
=======
    private void clicBtnOpcion1(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
>>>>>>> main
=======

    private void clicBtnOpcion1(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
>>>>>>> 78d66ed7c2a6b3cbc2aa5ce98cacffc372d1c8f4
    }
   
    
    private void irInicioSesion(){
        Stage escenarioBase = (Stage) apMenuPrincipal.getScene().getWindow();
        escenarioBase.setScene(Utilidades.inicializarEscena("vistas/FXMLInicioSesion.fxml"));
        Utilidades.centrarEscenario(escenarioBase);
        escenarioBase.setTitle("Inicio Sesíon");    
        escenarioBase.show();   
    }

    @FXML
    private void clicBtnCerrarSesion(ActionEvent event) {
        irInicioSesion();
        
    }

<<<<<<< HEAD
<<<<<<< HEAD
    
=======
=======

>>>>>>> 78d66ed7c2a6b3cbc2aa5ce98cacffc372d1c8f4
    private void clicBtnOpcion3(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectosPorDirector.fxml");
    }
     
    //Director    
    @FXML
    private void clicBtnCrearAnteproyecto(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
    }

    @FXML
    private void clicBtnVerAnteproyectos(ActionEvent event) {
    }

    @FXML
    private void clicBtnRegistrarUsuario(ActionEvent event) {
    }
<<<<<<< HEAD


>>>>>>> main
=======
>>>>>>> 78d66ed7c2a6b3cbc2aa5ce98cacffc372d1c8f4
    
}
