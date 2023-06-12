
package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sspger.modelos.dao.ActividadDAO;
import sspger.modelos.pojo.Actividad;

public class FXMLEntregarActividadFormularioController implements Initializable {

    @FXML
    private AnchorPane apEntregarActividadFormulario;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaFin;
    @FXML
    private Button btnSubirArchivo;
    @FXML
    private Pane paneArchivoConfirmacion;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnEntregar;
    @FXML
    private TextArea taComentarios;
    @FXML
    private Text txDescripcion;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    

    @FXML
    private void clicBtnSubirArchivo(ActionEvent event) {
    }

    @FXML
    private void clicBtnVolver(ActionEvent event) {
    }

    @FXML
    private void clicBtnEntregar(ActionEvent event) {
    }

    
    public void cargarInformacionActividad(int idActividad){
        Actividad actividad = ActividadDAO.obtenerInformacionActividaPorIdActividad(idActividad);
        lbTitulo.setText(actividad.getTitulo());
        lbFechaFin.setText(actividad.getFechaFin());
        lbFechaInicio.setText(actividad.getFechaInicio());
        txDescripcion.setText(actividad.getDescripcion());        
    }
    
}
