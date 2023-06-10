
package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sspger.modelos.dao.EstadoAnteproyectoDAO;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.EstadoAnteproyecto;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;


public class FXMLAnteproyectoFragmentoController implements Initializable {

    @FXML
    private Pane pnAnteproyecto;
    @FXML
    private Label lbNombreAnteproyecto;
    @FXML
    private Label lbNombreDirector;
    @FXML
    private Label lbEstadoAnteproyecto;
    
    private AnchorPane apPadre;
    private int idEstadoAnteproyecto;
    private int idAnteproyecto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void cargarInformacionAnteproyecto(Anteproyecto anteproyecto, AnchorPane apPadre){
        lbNombreAnteproyecto.setText(anteproyecto.getNombreProyectoInvestigacion());
        lbNombreDirector.setText(anteproyecto.getNombreProfesor());
        this.idEstadoAnteproyecto = anteproyecto.getIdEstado();
        this.apPadre = apPadre;
        this.idAnteproyecto = anteproyecto.getIdAnteproyecto();
        cargarEstadoAnteproyecto();
    }
    
    private void cargarEstadoAnteproyecto(){
        EstadoAnteproyecto estadoAnteproyecto = EstadoAnteproyectoDAO.obtenerEstadoAnteproyectoPorId(idEstadoAnteproyecto);
        switch (estadoAnteproyecto.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case(Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case(Constantes.OPERACION_EXITOSA):
                lbEstadoAnteproyecto.setText(estadoAnteproyecto.getDescripcion());
                break;
        }
    }


    @FXML
    private void clicBtnVerAnteproyecto(ActionEvent event) {
        FXMLAnteproyectoFormularioController anteproyectoFormularioController = Utilidades.cambiarPaneObtenerControlador(apPadre, "/sspger/vistas/FXMLAnteproyectoFormulario.fxml");
        anteproyectoFormularioController.cargarInformacionVerAnteproyecto(idAnteproyecto);
    }

    @FXML
    private void clicBtnVerAvances(ActionEvent event) {
    }
    
}
