package sspger.controladores;
import sspger.controladores.FXMLFormularioUsuarioController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.UsuarioSingleton;
import sspger.utils.Utilidades;




public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private AnchorPane apMenuPrincipal;    
    @FXML
    private AnchorPane apMenuDirector;
    @FXML
    private AnchorPane apMenuAdministrador;
    @FXML
    private AnchorPane apMenuEstudiante;
    @FXML
    private AnchorPane apMenuEstudianteConAnteproyecto;
    @FXML
    private AnchorPane apMenuEncargadoCA; 
    @FXML
    private Label lbNombreUsuarioAdministrador;
    @FXML
    private Label lbNombreUsuarioDirector;
    @FXML
    private Label lbNombreUsuarioEstudiante;
    @FXML
    private Label lbNombreUsuarioEstudianteConAnteproyecto;
    @FXML
    private Label lbNombreUsuarioEncargadoCA;
    @FXML
    private Button btnModificarUsuario;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personalizarMenuPrincipal();
    }    
       
    private void irInicioSesion(){
        Stage escenarioBase = (Stage) apMenuPrincipal.getScene().getWindow();
        escenarioBase.setScene(Utilidades.inicializarEscena("vistas/FXMLInicioSesion.fxml"));
        Utilidades.centrarEscenario(escenarioBase);
        escenarioBase.setTitle("Inicio Sesíon");    
        escenarioBase.show();   
    }
    
    private void ocultarMenus(){
        apMenuAdministrador.setVisible(false);
        apMenuDirector.setVisible(false);
        apMenuEstudiante.setVisible(false);
        apMenuEstudianteConAnteproyecto.setVisible(false);
        apMenuEncargadoCA.setVisible(false);
    }
    
    public void personalizarMenuPrincipal(){
        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();
        int idTipoUsuario = usuario.getIdTipoUsuario();
        String nombreUsuario = usuario.getNombre();
        switch (idTipoUsuario) {
            case Constantes.ADMINISTRADOR:
                ocultarMenus();
                apMenuAdministrador.setVisible(true);
                lbNombreUsuarioAdministrador.setText(nombreUsuario);
                break;
            case Constantes.PROFESOR:
            case Constantes.DIRECTOR:
                ocultarMenus();
                apMenuDirector.setVisible(true);
                lbNombreUsuarioDirector.setText(nombreUsuario);
                break;
            case Constantes.ESTUDIANTE:
                ocultarMenus();
                apMenuEstudiante.setVisible(true);
                lbNombreUsuarioEstudiante.setText(nombreUsuario);
                break;
            case Constantes.ESTUDIANTE_CON_ANTEPROYECTO:
                ocultarMenus();
                apMenuEstudianteConAnteproyecto.setVisible(true);
                lbNombreUsuarioEstudianteConAnteproyecto.setText(nombreUsuario);
                break;
            case Constantes.ENCARGADO_CA:
                ocultarMenus();
                apMenuEncargadoCA.setVisible(true);
                lbNombreUsuarioEncargadoCA.setText(nombreUsuario);
                break;
        }
    }

    @FXML
    private void clicBtnCerrarSesion(ActionEvent event) {
        irInicioSesion();
    }
     
    //Director    
    @FXML
    private void clicBtnCrearAnteproyecto(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
    }

    @FXML
    private void clicBtnVerAnteproyectos(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLListaAnteproyectosDelDirector.fxml");

    }

    
    //Administrador
   @FXML
private void clicBtnRegistrarUsuario(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLFormularioUsuario.fxml");
}





    //Estudiante Sin Anteproyecto
    @FXML
    private void clicBtnVerAnteproyectosDisponibles(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLListaAnteproyectosDisponibles.fxml");
    }

    //Encargado CA
    @FXML
    private void clicBtnVerAnteproyectosPorValidar(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLListaAnteproyectosPorValidar.fxml");
    }

    @FXML
    private void clicBtnVerAnteproyectosPorDirector(ActionEvent event) {
        Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLAnteproyectosPorDirector.fxml");
    }

    @FXML
    private void clicBtnModificarUsuario(ActionEvent event) {
       Utilidades.cambiarPane(apMenuPrincipal, "/sspger/vistas/FXMLModificarUsuario.fxml");
    }
    
}
