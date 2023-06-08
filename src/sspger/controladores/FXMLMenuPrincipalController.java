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
    @FXML
    private AnchorPane apMenuDirector;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    private void clicBtnOpcion2(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLRegistrarUsuario.fxml");
    }

    private void clicBtnOpcion1(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
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


    
}
