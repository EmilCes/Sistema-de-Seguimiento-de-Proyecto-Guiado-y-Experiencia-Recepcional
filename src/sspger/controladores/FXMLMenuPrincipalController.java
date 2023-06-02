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
    private AnchorPane listaAnteproyectos;

    @FXML
    private AnchorPane apMenuPrincipal;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void cambiarPane(String path){
        try {
            FXMLLoader crearAnteproyectoLoader = new FXMLLoader(getClass().getResource(path));
            AnchorPane crearAnteproyectoPane = crearAnteproyectoLoader.load();
            apMenuPrincipal.getChildren().add(crearAnteproyectoPane);
        } catch (IOException ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    @FXML
    private void clicBtnOpcion2(ActionEvent event) {
        apMenuPrincipal.getChildren().clear();
        listaAnteproyectos.setVisible(true);
    }

    @FXML
    private void clicBtnOpcion1(ActionEvent event) {
        listaAnteproyectos.setVisible(false);
        cambiarPane("/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
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
    
}
