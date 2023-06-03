package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.CuerpoAcademicoDAO;
import sspger.modelos.dao.LGACDAO;
import sspger.modelos.pojo.CuerpoAcademico;
import sspger.modelos.pojo.CuerpoAcademicoRespuesta;
import sspger.modelos.pojo.LGAC;
import sspger.modelos.pojo.LGACRespuesta;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;



public class FXMLAnteproyectoFormularioController implements Initializable {

    @FXML
    private AnchorPane apCrearAnteproyecto;
    @FXML
    private ScrollPane spAnteproyectoFormulario;
    @FXML
    private TextField tfNombreProyectoInvestigacion;
    @FXML
    private ComboBox<LGAC> cbLGAC;
    @FXML
    private TextArea taLineaInvestigacion;
    @FXML
    private TextField tfDuracionAproximada;
    @FXML
    private ComboBox<?> cbModalidadTrabajoRecepcional;
    @FXML
    private TextField tfNombreTrabajoRecepcional;
    @FXML
    private TextArea taRequisitos;
    @FXML
    private ComboBox<Integer> cbNumeroEstudiantes;
    @FXML
    private TextArea taDescripcionProyectoInvestigacion;
    @FXML
    private TextArea taDescripcionTrabajoRecepcional;
    @FXML
    private TextArea taResultadosEsperados;
    @FXML
    private TextArea taBibliografiaRecomendada;
    @FXML
    private ComboBox<CuerpoAcademico> cbCuerpoAcademico;
    
    private ObservableList<CuerpoAcademico> cuerposAcademicos;
    private ObservableList<LGAC> lgac;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionCuerposAcademicos();
        cbCuerpoAcademico.valueProperty().addListener(new ChangeListener<CuerpoAcademico>(){
            @Override
            public void changed(ObservableValue<? extends CuerpoAcademico> observable, CuerpoAcademico oldValue, CuerpoAcademico newValue){
                if(newValue != null){
                    cargarInformacionLGAC(newValue.getIdCuerpoAcademico());
                }
            }
                
        });
        
    }    
 
    @FXML
    private void clicBtnEnviarParaAprobacion(ActionEvent event) {
    }

    @FXML
    private void clicBtnGuardarBorrador(ActionEvent event) {
    }
    
    private void cargarInformacionCuerposAcademicos(){
        cuerposAcademicos = FXCollections.observableArrayList();
        CuerpoAcademicoRespuesta cuerposAcademicosBD = CuerpoAcademicoDAO.obtenerCuerposAcademicos();
        switch (cuerposAcademicosBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case(Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case(Constantes.OPERACION_EXITOSA):
                cuerposAcademicos.addAll(cuerposAcademicosBD.getCuerposAcademicos());
                cbCuerpoAcademico.setItems(cuerposAcademicos);
        }
    }
    
    private void cargarInformacionLGAC(int idCuerpoAcademico){
        lgac = FXCollections.observableArrayList();
        LGACRespuesta lgacBD = LGACDAO.obtenerLGACPorCuerpoAcademicos(idCuerpoAcademico);
        switch(lgacBD.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                lgac.addAll(lgacBD.getLgac());
                cbLGAC.setItems(lgac);
                break;
        }
    }
    
}
