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
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.dao.CuerpoAcademicoDAO;
import sspger.modelos.dao.LGACDAO;
import sspger.modelos.dao.TipoAnteproyectoDAO;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.CuerpoAcademico;
import sspger.modelos.pojo.CuerpoAcademicoRespuesta;
import sspger.modelos.pojo.LGAC;
import sspger.modelos.pojo.LGACRespuesta;
import sspger.modelos.pojo.TipoAnteproyecto;
import sspger.modelos.pojo.TipoAnteproyectoRespuesta;
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
    private ComboBox<TipoAnteproyecto> cbModalidadTrabajoRecepcional;
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
    private ObservableList<TipoAnteproyecto> tipoAnteproyectos;
    
    private int idCuerpoAcademico;
    private String nombreProyectoInvestigacion;
    private int idLGAC;
    private String lineaInvestigacion;
    private String duracionAproximada;
    private int idTipoAnteproyecto;
    private String nombreTrabajoRecepcional;
    private String requisitos;
    private int numeroEstudiantes;
    private String descripcionProyectoInvestigacion;
    private String descripcionTrabajoRecepcional;
    private String resultadosEsperados;
    private String bibliografiaRecomendada;
    private int idEstado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionCuerposAcademicos();
        cargarInformacionTiposAnteproyectos();
        cargarInformacionAlumnosParticipantes();
        cbCuerpoAcademico.valueProperty().addListener(new ChangeListener<CuerpoAcademico>(){
            @Override
            public void changed(ObservableValue<? extends CuerpoAcademico> observable, CuerpoAcademico oldValue, CuerpoAcademico newValue){
                if(newValue != null){
                    cargarInformacionLGAC(newValue.getIdCuerpoAcademico());
                }
            }
                
        });
        
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
    
    private void cargarInformacionTiposAnteproyectos(){
        tipoAnteproyectos = FXCollections.observableArrayList();
        TipoAnteproyectoRespuesta tiposAnteproyectosBD = TipoAnteproyectoDAO.obtenerTiposAnteproyectos();
        switch (tiposAnteproyectosBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case(Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case(Constantes.OPERACION_EXITOSA):
                tipoAnteproyectos.addAll(tiposAnteproyectosBD.getTipoAnteproyectos());
                cbModalidadTrabajoRecepcional.setItems(tipoAnteproyectos);
        }
    }
    
    private void cargarInformacionAlumnosParticipantes() {
        cbNumeroEstudiantes.getItems().addAll(1, 2, 3);
    }
    
    private void obtenerTextoDeCamposTexto(){
        nombreProyectoInvestigacion = tfNombreProyectoInvestigacion.getText();
        lineaInvestigacion = taLineaInvestigacion.getText();
        duracionAproximada = tfDuracionAproximada.getText();
        nombreTrabajoRecepcional = tfNombreTrabajoRecepcional.getText();
        requisitos = taRequisitos.getText();
        descripcionProyectoInvestigacion = taDescripcionProyectoInvestigacion.getText();
        descripcionTrabajoRecepcional = taDescripcionTrabajoRecepcional.getText();
        resultadosEsperados = taResultadosEsperados.getText();
        bibliografiaRecomendada = taBibliografiaRecomendada.getText();
    }
    
    private void obtenerTextoComboBox(){
        if(cbCuerpoAcademico.getValue() != null){
            idCuerpoAcademico = cbCuerpoAcademico.getSelectionModel().getSelectedItem().getIdCuerpoAcademico();
        }
        if(cbLGAC.getValue() != null){
            idLGAC = cbLGAC.getSelectionModel().getSelectedItem().getIdLGAC();
        }
        if(cbModalidadTrabajoRecepcional.getValue() != null){
            idTipoAnteproyecto = cbModalidadTrabajoRecepcional.getSelectionModel().getSelectedItem().getIdTipoProyecto();
        }
        if(cbNumeroEstudiantes.getValue() != null){
            numeroEstudiantes = cbNumeroEstudiantes.getValue();
        }
    }
    
        private void resetearEstilos() {
        tfNombreProyectoInvestigacion.setStyle("");
        taLineaInvestigacion.setStyle("");
        tfDuracionAproximada.setStyle("");
        tfNombreTrabajoRecepcional.setStyle("");
        taRequisitos.setStyle("");
        taDescripcionProyectoInvestigacion.setStyle("");
        taDescripcionTrabajoRecepcional.setStyle("");
        taResultadosEsperados.setStyle("");
        taBibliografiaRecomendada.setStyle("");
    }
    
    private void limpiarCampos(){
        tfNombreProyectoInvestigacion.setText("");
        taLineaInvestigacion.setText("");
        tfDuracionAproximada.setText("");
        tfNombreTrabajoRecepcional.setText("");
        taRequisitos.setText("");
        taDescripcionProyectoInvestigacion.setText("");
        taDescripcionTrabajoRecepcional.setText("");
        taResultadosEsperados.setText("");
        taBibliografiaRecomendada.setText("");
        cbCuerpoAcademico.setValue(null);
        cbLGAC.setValue(null);
        cbModalidadTrabajoRecepcional.setValue(null);
        cbNumeroEstudiantes.setValue(null);
    }
    
    private void validarCamposRegistro(){
        resetearEstilos();
        obtenerTextoDeCamposTexto();
        obtenerTextoComboBox();
        
        boolean camposValidos = true;
        
        if(cbCuerpoAcademico.getValue()== null){
            cbCuerpoAcademico.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(nombreProyectoInvestigacion.isEmpty()){
            tfNombreProyectoInvestigacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(cbLGAC.getValue() == null){
            cbLGAC.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(lineaInvestigacion.isEmpty()){
            taLineaInvestigacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(duracionAproximada.isEmpty()){
            tfDuracionAproximada.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(cbModalidadTrabajoRecepcional.getValue() == null){
            cbModalidadTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(nombreTrabajoRecepcional.isEmpty()){
            tfNombreTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(requisitos.isEmpty()){
            taRequisitos.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(cbNumeroEstudiantes.getValue() == null){
            cbNumeroEstudiantes.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(descripcionProyectoInvestigacion.isEmpty()){
            taDescripcionProyectoInvestigacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(descripcionTrabajoRecepcional.isEmpty()){
            taDescripcionTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(resultadosEsperados.isEmpty()){
            taResultadosEsperados.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        if(bibliografiaRecomendada.isEmpty()){
            taBibliografiaRecomendada.setStyle("-fx-border-color: red");
            camposValidos = false;
        }
        
        
        
        if(camposValidos){
            Anteproyecto nuevoAnteproyecto = new Anteproyecto();
            nuevoAnteproyecto.setIdEstado(idEstado);
            nuevoAnteproyecto.setIdCuerpoAcademico(idCuerpoAcademico);
            nuevoAnteproyecto.setNombreProyectoInvestigacion(nombreProyectoInvestigacion);
            nuevoAnteproyecto.setIdLAGC(idLGAC);
            nuevoAnteproyecto.setLineaInvestigacion(lineaInvestigacion);
            nuevoAnteproyecto.setDuracionAproximada(duracionAproximada);
            nuevoAnteproyecto.setIdTipoAnteproyecto(idTipoAnteproyecto);
            nuevoAnteproyecto.setNombreTrabajoRecepcional(nombreTrabajoRecepcional);
            nuevoAnteproyecto.setRequisitos(requisitos);
            nuevoAnteproyecto.setNumeroEstudiantes(numeroEstudiantes);
            nuevoAnteproyecto.setDescripcionProyectoInvestigacion(descripcionProyectoInvestigacion);
            nuevoAnteproyecto.setDescripcionTrabajoRecepcional(descripcionTrabajoRecepcional);
            nuevoAnteproyecto.setResultadosEsperados(resultadosEsperados);
            nuevoAnteproyecto.setBibliografiaRecomendada(bibliografiaRecomendada);
            registrarAnteproyecto(nuevoAnteproyecto);    
        }
        
    }
    
    private void validarCamposBorrador(){
    }
    
    private void registrarAnteproyecto(Anteproyecto anteproyecto){
        int codigoRespuesta = AnteproyectoDAO.guardarAnteproyecto(anteproyecto);
        switch(codigoRespuesta){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", 
                                                "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.", 
                                                Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información", 
                                                "La información del ANTEPROYECTO no puede ser guardada, verifique su información", 
                                                Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Anteproyecto registrado", 
                                                "Anteproyecto guardado correctamente.", 
                                                Alert.AlertType.INFORMATION);
                limpiarCampos();
                break;
        }
    }
            
    
    @FXML
    private void clicBtnEnviarParaAprobacion(ActionEvent event) {
        idEstado = Constantes.POR_VALIDAR;
        validarCamposRegistro();
    }

    @FXML
    private void clicBtnGuardarBorrador(ActionEvent event) {
        idEstado = Constantes.BORRADOR;

    }
    
}