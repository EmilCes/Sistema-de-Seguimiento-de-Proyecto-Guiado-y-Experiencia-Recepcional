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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sspger.modelos.dao.AlumnoDAO;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.dao.CuerpoAcademicoDAO;
import sspger.modelos.dao.LGACDAO;
import sspger.modelos.dao.ProfesorDAO;
import sspger.modelos.dao.TipoAnteproyectoDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.Alumno;
import sspger.modelos.pojo.AlumnoRespuesta;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.CuerpoAcademico;
import sspger.modelos.pojo.CuerpoAcademicoRespuesta;
import sspger.modelos.pojo.LGAC;
import sspger.modelos.pojo.LGACRespuesta;
import sspger.modelos.pojo.Profesor;
import sspger.modelos.pojo.TipoAnteproyecto;
import sspger.modelos.pojo.TipoAnteproyectoRespuesta;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.UsuarioSingleton;
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
    @FXML
    private Label lbTituloMenu;
    @FXML
    private Button btnEnviarAprobacion;
    @FXML
    private Button btnGuardarBorrador;
    @FXML
    private Button btnModificarAnteproyecto;
    @FXML
    private Button btnAsignarEstudiante;
    @FXML
    private TextArea taEstudiantes;
    @FXML
    private Pane pnEstudiantes;
    @FXML
    private Button btnAprobarAnteproyecto;
    @FXML
    private Button btnEnviarCorrecciones;
    @FXML
    private Pane pnNotas;
    @FXML
    private TextArea taNotas;
    @FXML
    private Button btnGuardarBorradorCorreccion;
    @FXML
    private Button btnEnviarAprobacionCorreccion;
    @FXML
    private Button btnModificarAnteproyectoCorreccion;
    @FXML
    private Button btnAñadirActividad;
    @FXML
    private Button btnRgresar;

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
    private int idProfesor;
    private int idEstado;
    private int idAnteproyecto;

    private boolean siendoModificado;
    private boolean comboBoxModificado;
    private Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        siendoModificado = false;
        comboBoxModificado = false;
        cargarInformacionCuerposAcademicos();
        cargarInformacionTiposAnteproyectos();
        cargarInformacionAlumnosParticipantes();

        cbCuerpoAcademico.valueProperty().addListener(new ChangeListener<CuerpoAcademico>() {
            @Override
            public void changed(ObservableValue<? extends CuerpoAcademico> observable, CuerpoAcademico oldValue, CuerpoAcademico newValue) {
                if (newValue != null) {
                    cargarInformacionLGAC(newValue.getIdCuerpoAcademico());
                }
            }
        });

        int idUsuario = UsuarioSingleton.getInstancia().getUsuario().getIdUsuario();
        idProfesor = ProfesorDAO.obtenerProfesorPorIdUsuario(idUsuario).getIdProfesor();

        ocultarBotones();
        taNotas.setVisible(false);
        btnEnviarAprobacion.setVisible(true);
        btnGuardarBorrador.setVisible(true);
        if (usuario.getIdTipoUsuario() == Constantes.DIRECTOR) {
            Profesor profesor = ProfesorDAO.obtenerProfesorPorIdUsuario(usuario.getIdUsuario());
            int idCuerpoAcademico = profesor.getIdCuerpoAcademico();
            CuerpoAcademico cuerpoAcademicoSeleccionado = null;
            for (CuerpoAcademico cuerpoAcademico : cbCuerpoAcademico.getItems()) {
                if (cuerpoAcademico.getIdCuerpoAcademico() == idCuerpoAcademico) {
                    cuerpoAcademicoSeleccionado = cuerpoAcademico;
                    break;
                }
            }
            cbCuerpoAcademico.setValue(cuerpoAcademicoSeleccionado);
            cbCuerpoAcademico.setDisable(true);
        }
    }

    private void cargarInformacionCuerposAcademicos() {
        cuerposAcademicos = FXCollections.observableArrayList();
        CuerpoAcademicoRespuesta cuerposAcademicosBD = CuerpoAcademicoDAO.obtenerCuerposAcademicos();
        switch (cuerposAcademicosBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                cuerposAcademicos.addAll(cuerposAcademicosBD.getCuerposAcademicos());
                cbCuerpoAcademico.setItems(cuerposAcademicos);
        }
    }

    private void cargarInformacionLGAC(int idCuerpoAcademico) {
        lgac = FXCollections.observableArrayList();
        LGACRespuesta lgacBD = LGACDAO.obtenerLGACPorCuerpoAcademicos(idCuerpoAcademico);
        switch (lgacBD.getCodigoRespuesta()) {
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

    private void cargarInformacionTiposAnteproyectos() {
        tipoAnteproyectos = FXCollections.observableArrayList();
        TipoAnteproyectoRespuesta tiposAnteproyectosBD = TipoAnteproyectoDAO.obtenerTiposAnteproyectos();
        switch (tiposAnteproyectosBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                tipoAnteproyectos.addAll(tiposAnteproyectosBD.getTipoAnteproyectos());
                cbModalidadTrabajoRecepcional.setItems(tipoAnteproyectos);
        }
    }

    private void cargarInformacionAlumnosParticipantes() {
        cbNumeroEstudiantes.getItems().addAll(1, 2, 3);
    }

    private void obtenerTextoDeCamposTexto() {
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

    private void obtenerTextoComboBox() {
        if (cbCuerpoAcademico.getValue() != null) {
            CuerpoAcademico cuerpoAcademico = cbCuerpoAcademico.getSelectionModel().getSelectedItem();
            idCuerpoAcademico = cuerpoAcademico.getIdCuerpoAcademico();
        }
        if (cbLGAC.getValue() != null) {
            LGAC lgac = cbLGAC.getSelectionModel().getSelectedItem();
            idLGAC = lgac.getIdLGAC();
        }
        if (cbModalidadTrabajoRecepcional.getValue() != null) {
            TipoAnteproyecto tipoAnteproyecto = cbModalidadTrabajoRecepcional.getSelectionModel().getSelectedItem();
            idTipoAnteproyecto = tipoAnteproyecto.getIdTipoProyecto();
        }
        if (cbNumeroEstudiantes.getValue() != null) {
            int numeroEstudiantesInt = cbNumeroEstudiantes.getSelectionModel().getSelectedItem();
            numeroEstudiantes = numeroEstudiantesInt;
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

    private void limpiarCampos() {
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

    private void validarCamposRegistro() {
        resetearEstilos();
        obtenerTextoDeCamposTexto();
        obtenerTextoComboBox();

        boolean camposValidos = true;

        if (cbCuerpoAcademico.getValue() == null) {
            cbCuerpoAcademico.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (nombreProyectoInvestigacion == null || nombreProyectoInvestigacion.isEmpty()) {
            tfNombreProyectoInvestigacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (cbLGAC.getValue() == null) {
            cbLGAC.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (lineaInvestigacion == null || lineaInvestigacion.isEmpty()) {
            taLineaInvestigacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (duracionAproximada == null || duracionAproximada.isEmpty()) {
            tfDuracionAproximada.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (cbModalidadTrabajoRecepcional.getValue() == null) {
            cbModalidadTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (nombreTrabajoRecepcional == null || nombreTrabajoRecepcional.isEmpty()) {
            tfNombreTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (requisitos == null || requisitos.isEmpty()) {
            taRequisitos.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (cbNumeroEstudiantes.getValue() == null || cbNumeroEstudiantes.getValue() == 0) {
            cbNumeroEstudiantes.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (descripcionProyectoInvestigacion == null || descripcionProyectoInvestigacion.isEmpty()) {
            taDescripcionProyectoInvestigacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (descripcionTrabajoRecepcional == null || descripcionTrabajoRecepcional.isEmpty()) {
            taDescripcionTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (resultadosEsperados == null || resultadosEsperados.isEmpty()) {
            taResultadosEsperados.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (bibliografiaRecomendada == null || bibliografiaRecomendada.isEmpty()) {
            taBibliografiaRecomendada.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (camposValidos) {
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
            nuevoAnteproyecto.setIdProfesor(idProfesor);
            if (siendoModificado) {
                modificarAnteproyecto(nuevoAnteproyecto);
            } else {
                registrarAnteproyecto(nuevoAnteproyecto);
                Usuario usuarioActual = UsuarioSingleton.getInstancia().getUsuario();
                if (usuarioActual.getIdTipoUsuario() != Constantes.DIRECTOR) {
                    UsuarioDAO.actualizarTipoUsuario(Constantes.DIRECTOR, usuarioActual.getIdUsuario());
                    ProfesorDAO.modificarCuerpoAcademico(usuarioActual.getIdUsuario(), idCuerpoAcademico);
                }
            }
        }

    }

    private void validarCamposBorrador() {
        resetearEstilos();
        obtenerTextoDeCamposTexto();
        obtenerTextoComboBox();

        boolean camposValidos = true;

        if (cbCuerpoAcademico.getValue() == null) {
            cbCuerpoAcademico.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (nombreProyectoInvestigacion == null || nombreProyectoInvestigacion.isEmpty() || nombreProyectoInvestigacion.trim().equals("")) {
            nombreProyectoInvestigacion = null;
        }

        if (cbLGAC.getValue() == null) {
            cbLGAC.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (lineaInvestigacion == null || lineaInvestigacion.isEmpty() || lineaInvestigacion.trim().equals("")) {
            lineaInvestigacion = null;
        }

        if (duracionAproximada == null || duracionAproximada.isEmpty() || duracionAproximada.trim().equals("")) {
            duracionAproximada = null;
        }

        if (cbModalidadTrabajoRecepcional.getValue() == null) {
            cbModalidadTrabajoRecepcional.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (nombreTrabajoRecepcional == null || nombreTrabajoRecepcional.isEmpty() || nombreTrabajoRecepcional.trim().equals("")) {
            nombreTrabajoRecepcional = null;
        }

        if (requisitos == null || requisitos.isEmpty() || requisitos.trim().equals("")) {
            requisitos = null;
        }

        if (cbNumeroEstudiantes.getValue() == null || cbNumeroEstudiantes.getValue() == 0) {
            cbNumeroEstudiantes.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (descripcionProyectoInvestigacion == null || descripcionProyectoInvestigacion.isEmpty() || descripcionProyectoInvestigacion.trim().equals("")) {
            descripcionProyectoInvestigacion = null;
        }

        if (descripcionTrabajoRecepcional == null || descripcionTrabajoRecepcional.isEmpty() || descripcionTrabajoRecepcional.trim().equals("")) {
            descripcionTrabajoRecepcional = null;
        }

        if (resultadosEsperados == null || resultadosEsperados.isEmpty() || resultadosEsperados.trim().equals("")) {
            resultadosEsperados = null;
        }

        if (bibliografiaRecomendada == null || bibliografiaRecomendada.isEmpty() || bibliografiaRecomendada.trim().equals("")) {
            bibliografiaRecomendada = null;
        }

        if (camposValidos) {
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
            nuevoAnteproyecto.setIdProfesor(idProfesor);
            if (siendoModificado) {
                modificarAnteproyecto(nuevoAnteproyecto);
            } else {
                registrarAnteproyecto(nuevoAnteproyecto);
            }
        } else {
            Utilidades.mostrarDialogoSimple("Borrador sin información",
                    "Es necesario que selecciones la información del cuerpo academico, LGAC, modalidad del " +
                    "trabajo recepcional y el numero de estudiantes para poder guardar como borrador.",
                    Alert.AlertType.WARNING);
        }

    }

    private void registrarAnteproyecto(Anteproyecto anteproyecto) {
        int codigoRespuesta = AnteproyectoDAO.guardarAnteproyecto(anteproyecto);
        switch (codigoRespuesta) {
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
        regresar();
    }

    private void modificarAnteproyecto(Anteproyecto anteproyecto) {
        anteproyecto.setIdAnteproyecto(idAnteproyecto);
        int codigoRespuesta = AnteproyectoDAO.modificarAnteproyectoPorId(anteproyecto);
        switch (codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información del ANTEPROYECTO no puede ser modificada, verifique su información",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Anteproyecto modificado",
                        "Anteproyecto modificado correctamente.",
                        Alert.AlertType.INFORMATION);
                break;
        }
        regresar();
    }

    private void cargarInformacionComboBox(Anteproyecto anteproyecto) {
        int idCuerpoAcademico = anteproyecto.getIdCuerpoAcademico();
        CuerpoAcademico cuerpoAcademicoSeleccionado = null;
        for (CuerpoAcademico cuerpoAcademico : cbCuerpoAcademico.getItems()) {
            if (cuerpoAcademico.getIdCuerpoAcademico() == idCuerpoAcademico) {
                cuerpoAcademicoSeleccionado = cuerpoAcademico;
                break;
            }
        }
        cbCuerpoAcademico.getSelectionModel().select(cuerpoAcademicoSeleccionado);

        int idLGAC = anteproyecto.getIdLAGC();
        LGAC lgacSeleccionado = null;
        for (LGAC lgac : cbLGAC.getItems()) {
            if (lgac.getIdLGAC() == idLGAC) {
                lgacSeleccionado = lgac;
                break;
            }
        }
        cbLGAC.getSelectionModel().select(lgacSeleccionado);

        int idTipoAnteproyecto = anteproyecto.getIdTipoAnteproyecto();
        TipoAnteproyecto tipoAnteproyectoSeleccionado = null;
        for (TipoAnteproyecto tipoAnteproyecto : cbModalidadTrabajoRecepcional.getItems()) {
            if (tipoAnteproyecto.getIdTipoProyecto() == idTipoAnteproyecto) {
                tipoAnteproyectoSeleccionado = tipoAnteproyecto;
                break;
            }
        }
        cbModalidadTrabajoRecepcional.getSelectionModel().select(tipoAnteproyectoSeleccionado);

        cbNumeroEstudiantes.setValue(anteproyecto.getNumeroEstudiantes());
    }

    private void cargarInformacionComboBoxModificacion(Anteproyecto anteproyecto) {
        int idLGAC = anteproyecto.getIdLAGC();
        LGAC lgacSeleccionado = null;
        for (LGAC lgac : cbLGAC.getItems()) {
            if (lgac.getIdLGAC() == idLGAC) {
                lgacSeleccionado = lgac;
                break;
            }
        }
        cbLGAC.getSelectionModel().select(lgacSeleccionado);

        int idTipoAnteproyecto = anteproyecto.getIdTipoAnteproyecto();
        TipoAnteproyecto tipoAnteproyectoSeleccionado = null;
        for (TipoAnteproyecto tipoAnteproyecto : cbModalidadTrabajoRecepcional.getItems()) {
            if (tipoAnteproyecto.getIdTipoProyecto() == idTipoAnteproyecto) {
                tipoAnteproyectoSeleccionado = tipoAnteproyecto;
                break;
            }
        }
        cbModalidadTrabajoRecepcional.getSelectionModel().select(tipoAnteproyectoSeleccionado);

        cbNumeroEstudiantes.setValue(anteproyecto.getNumeroEstudiantes());
    }

    private void ocultarBotones() {
        btnAsignarEstudiante.setVisible(false);
        btnEnviarAprobacion.setVisible(false);
        btnGuardarBorrador.setVisible(false);
        btnModificarAnteproyecto.setVisible(false);
        pnEstudiantes.setVisible(false);
        taEstudiantes.setVisible(false);
        pnNotas.setVisible(false);
        taNotas.setVisible(false);
        btnModificarAnteproyecto.setVisible(false);
        btnEnviarAprobacionCorreccion.setVisible(false);
        btnModificarAnteproyectoCorreccion.setVisible(false);
        btnGuardarBorradorCorreccion.setVisible(false);
        btnAñadirActividad.setVisible(false);
    }

    private void setEditableCamposTexto(boolean esEditable) {
        tfNombreProyectoInvestigacion.setEditable(esEditable);
        taLineaInvestigacion.setEditable(esEditable);
        tfDuracionAproximada.setEditable(esEditable);
        tfNombreTrabajoRecepcional.setEditable(esEditable);
        taRequisitos.setEditable(esEditable);
        taDescripcionProyectoInvestigacion.setEditable(esEditable);
        taDescripcionTrabajoRecepcional.setEditable(esEditable);
        taResultadosEsperados.setEditable(esEditable);
        taBibliografiaRecomendada.setEditable(esEditable);
        cbLGAC.setEditable(esEditable);
        cbModalidadTrabajoRecepcional.setEditable(esEditable);
        cbNumeroEstudiantes.setEditable(esEditable);

    }

    public void cargarInformacionVerAnteproyecto(int idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
        lbTituloMenu.setText("Anteproyecto");
        lbTituloMenu.setLayoutX(265);
        btnRgresar.setVisible(true);
        Anteproyecto anteproyecto = AnteproyectoDAO.obtenerAnteproyectoPorId(idAnteproyecto);
        switch (anteproyecto.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información del ANTEPROYECTO no puede ser obtenida, verifique su información",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                cargarInformacionComboBox(anteproyecto);
                tfNombreProyectoInvestigacion.setText(anteproyecto.getNombreProyectoInvestigacion());
                taLineaInvestigacion.setText(anteproyecto.getLineaInvestigacion());
                tfDuracionAproximada.setText(anteproyecto.getDuracionAproximada());
                tfNombreTrabajoRecepcional.setText(anteproyecto.getNombreTrabajoRecepcional());
                taRequisitos.setText(anteproyecto.getRequisitos());
                taDescripcionProyectoInvestigacion.setText(anteproyecto.getDescripcionProyectoInvestigacion());
                taDescripcionTrabajoRecepcional.setText(anteproyecto.getDescripcionTrabajoRecepcional());
                taResultadosEsperados.setText(anteproyecto.getResultadosEsperados());
                taBibliografiaRecomendada.setText(anteproyecto.getBibliografiaRecomendada());
                taNotas.setText(anteproyecto.getNotas());
                btnEnviarAprobacion.setVisible(false);
                btnGuardarBorrador.setVisible(false);
                setEditableCamposTexto(false);
                cbCuerpoAcademico.setEditable(false);
                this.idEstado = anteproyecto.getIdEstado();
                this.idCuerpoAcademico = anteproyecto.getIdCuerpoAcademico();
                this.idLGAC = anteproyecto.getIdLAGC();
                this.idTipoAnteproyecto = anteproyecto.getIdTipoAnteproyecto();
                this.numeroEstudiantes = anteproyecto.getNumeroEstudiantes();
                switch (anteproyecto.getIdEstado()) {
                    case Constantes.ASIGNADO:
                        ocultarBotones();
                        obtenerNombresEstudiantes();
                        pnEstudiantes.setVisible(true);
                        taEstudiantes.setVisible(true);
                        taEstudiantes.setEditable(false);
                        btnAñadirActividad.setVisible(true);
                        break;
                    case Constantes.BORRADOR:
                        ocultarBotones();
                        btnModificarAnteproyecto.setVisible(true);
                        break;
                    case Constantes.DISPONILE:
                        ocultarBotones();
                        switch (UsuarioSingleton.getInstancia().getUsuario().getIdTipoUsuario()) {
                            case Constantes.DIRECTOR:
                                btnAsignarEstudiante.setVisible(true);
                        }
                        break;
                    case Constantes.POR_VALIDAR:
                        ocultarBotones();
                        switch (UsuarioSingleton.getInstancia().getUsuario().getIdTipoUsuario()) {
                            case Constantes.DIRECTOR:
                                btnModificarAnteproyecto.setVisible(true);
                                break;
                            case Constantes.ENCARGADO_CA:
                                pnNotas.setVisible(true);
                                taNotas.setVisible(true);
                                btnAprobarAnteproyecto.setVisible(true);
                                btnEnviarCorrecciones.setVisible(true);
                        }
                        break;
                    case Constantes.CORREGIR:
                        ocultarBotones();
                        pnNotas.setVisible(true);
                        taNotas.setVisible(true);
                        taNotas.setEditable(false);
                        btnModificarAnteproyectoCorreccion.setVisible(true);
                        break;

                }
                break;
        }

    }

    private void obtenerNombresEstudiantes() {
        AlumnoRespuesta alumnoRespuesta = AlumnoDAO.obtenerAlumnosPorIdAnteproyetco(idAnteproyecto);
        switch (alumnoRespuesta.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "Ocurrio un error al buscar la información.",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                String alumnosString = "";
                for (Alumno alumno : alumnoRespuesta.getAlumnos()) {
                    alumnosString += alumno.getNombreCompleto() + "\n";
                }
                taEstudiantes.setText(alumnosString);
                break;
        }
    }

    private void aprobarAnteproyecto() {
        int codigoRespuesta = AnteproyectoDAO.actualizarEstadoAnteproyecto(Constantes.DISPONILE, idAnteproyecto);
        switch (codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No hay conexión con la base de datos. Intentelo más tarde",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "Verifique la información que proporciono",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Anteproyecto aprobado correctamente",
                        "El anteproyecto ha sido aprobado",
                        Alert.AlertType.INFORMATION);
                break;
        }
    }

    private void validarCampoNotas() {
        taNotas.setStyle("");
        String notas = taNotas.getText();
        if (taNotas.getText() != null) {
            if (!notas.isEmpty() && !notas.trim().equals("")) {
                mandarCorreccionesAnteproyecto();
            } else {
                taNotas.setStyle("-fx-border-color: red");
                Utilidades.mostrarDialogoSimple("Faltan notas de correción",
                        "Antes de envíar una correción debes llenar el campo notas",
                        Alert.AlertType.WARNING);
            }
        } else {
            taNotas.setStyle("-fx-border-color: red");
            Utilidades.mostrarDialogoSimple("Faltan notas de correción",
                    "Antes de envíar una correción debes llenar el campo notas",
                    Alert.AlertType.WARNING);
        }
    }

    private void mandarCorreccionesAnteproyecto() {
        Anteproyecto anteproyecto = new Anteproyecto();
        anteproyecto.setIdAnteproyecto(idAnteproyecto);
        anteproyecto.setNotas(taNotas.getText());
        anteproyecto.setIdEstado(Constantes.CORREGIR);
        int codigoRespuesta = AnteproyectoDAO.actualizarAnteproyectoComoBorrador(anteproyecto);
        System.out.println(codigoRespuesta);
        switch (codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No hay conexión con la base de datos. Intentelo más tarde",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "Verifique la información que proporciono",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Correcciones envíadas",
                        "Correcciones envíadas correctamente",
                        Alert.AlertType.INFORMATION);
                regresar();
                break;
        }
    }

    private void modificarAnteproyectoCargarInformacion() {
        cbLGAC.setDisable(true);
        cbModalidadTrabajoRecepcional.setDisable(true);
        cbNumeroEstudiantes.setDisable(true);
        Anteproyecto anteproyecto = new Anteproyecto();
        cargarInformacionLGAC(idCuerpoAcademico);
        anteproyecto.setIdCuerpoAcademico(idCuerpoAcademico);
        anteproyecto.setIdLAGC(idLGAC);
        anteproyecto.setIdTipoAnteproyecto(idTipoAnteproyecto);
        anteproyecto.setNumeroEstudiantes(numeroEstudiantes);

        siendoModificado = true;
        ocultarBotones();
        setEditableCamposTexto(true);
        cbCuerpoAcademico.setEditable(false);
        cargarInformacionComboBoxModificacion(anteproyecto);

        if (taNotas.getText() != null) {
            taNotas.setVisible(true);
            pnNotas.setVisible(true);
            btnEnviarAprobacionCorreccion.setVisible(true);
            btnGuardarBorradorCorreccion.setVisible(true);
        } else {
            btnEnviarAprobacion.setVisible(true);
            btnGuardarBorrador.setVisible(true);
        }
    }

    private void regresar() {
        switch (UsuarioSingleton.getInstancia().getUsuario().getIdTipoUsuario()) {
            case Constantes.PROFESOR:
            case Constantes.DIRECTOR:
                Utilidades.cambiarPane(apCrearAnteproyecto, "/sspger/vistas/FXMLListaAnteproyectosDelDirector.fxml");
                break;
            case Constantes.ENCARGADO_CA:
                Utilidades.cambiarPane(apCrearAnteproyecto, "/sspger/vistas/FXMLListaAnteproyectosPorValidar.fxml");
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
        validarCamposBorrador();
    }

    @FXML
    private void clicBtnModificarAnteproyecto(ActionEvent event) {
        modificarAnteproyectoCargarInformacion();
    }

    @FXML
    private void clicBtnAsignarEstudiante(ActionEvent event) {
        FXMLAsignarEstudianteController asignarEstudianteController = Utilidades.cambiarPaneObtenerControlador(
                apCrearAnteproyecto, "/sspger/vistas/FXMLAsignarEstudiante.fxml");
        asignarEstudianteController.cargarInformacionAsignacionEstudiantes(idAnteproyecto, cbNumeroEstudiantes.getValue());
    }

    @FXML
    private void clicBtnAprobarAnteproyecto(ActionEvent event) {
        aprobarAnteproyecto();
        regresar();
    }

    @FXML
    private void clicBtnEnviarCorrecciones(ActionEvent event) {
        validarCampoNotas();
    }

    @FXML
    private void clicBtnAñadirActividad(ActionEvent event) {
        FXMLAñadirActividadController añadirActividadController = Utilidades.cambiarPaneObtenerControlador(
                apCrearAnteproyecto, "/sspger/vistas/FXMLAñadirActividad.fxml");
        añadirActividadController.cargarAnteproyecto(idAnteproyecto);
    }

    @FXML
    private void clicBtnRegresar(ActionEvent event) {
        regresar();
    }

}
