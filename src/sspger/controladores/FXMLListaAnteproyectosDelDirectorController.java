package sspger.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.dao.EstadoAnteproyectoDAO;
import sspger.modelos.dao.ProfesorDAO;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.modelos.pojo.EstadoAnteproyecto;
import sspger.modelos.pojo.EstadoAnteproyectoRespuesta;
import sspger.utils.Constantes;
import sspger.utils.UsuarioSingleton;
import sspger.utils.Utilidades;

public class FXMLListaAnteproyectosDelDirectorController implements Initializable {

    @FXML
    private AnchorPane apListaAnteproyectos;
    @FXML
    private ScrollPane spListaAnteproyectos;
    @FXML
    private VBox vbAnteproyectos;
    @FXML
    private Pane pnAnteproyectosNoDisponibles;
    @FXML
    private ComboBox<EstadoAnteproyecto> cbEstadoAnteproyecto;

    private ObservableList<EstadoAnteproyecto> estadosAnteproyecto;
    private int idProfesor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionEstadosAnteproyecto();
        int idUsuario = UsuarioSingleton.getInstancia().getUsuario().getIdUsuario();
        idProfesor = ProfesorDAO.obtenerProfesorPorIdUsuario(idUsuario).getIdProfesor();
        buscarAnteproyectosPorEstado(Constantes.ASIGNADO);
        cbEstadoAnteproyecto.valueProperty().addListener(new ChangeListener<EstadoAnteproyecto>() {
            @Override
            public void changed(ObservableValue<? extends EstadoAnteproyecto> observable, EstadoAnteproyecto oldValue, EstadoAnteproyecto newValue) {
                if (newValue != null) {
                    buscarAnteproyectosPorEstado(newValue.getIdEstado());
                }
            }

        });

    }

    private void buscarAnteproyectosPorDirectorYEstado(int idDirector, int idEstado) throws IOException {
        spListaAnteproyectos.setVisible(true);
        pnAnteproyectosNoDisponibles.setVisible(false);
        vbAnteproyectos.getChildren().clear();
        AnteproyectoRespuesta anteproyectoRespuesta = AnteproyectoDAO.obtenerAnteproyectosPorDirectorYEstado(idDirector, idEstado);
        switch (anteproyectoRespuesta.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "No se pudo conectar con la base de datos. "
                        + "Intente de nuevo o hágalo más tarde", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                ArrayList<Anteproyecto> anteproyectos = anteproyectoRespuesta.getAnteproyectos();
                ObservableList<Anteproyecto> observableAnteproyectos = FXCollections.observableArrayList(anteproyectos);
                vbAnteproyectos.prefHeightProperty().bind(Bindings.size(observableAnteproyectos).multiply(150));
                vbAnteproyectos.minHeightProperty().bind(Bindings.createDoubleBinding(
                        () -> Math.max(vbAnteproyectos.getHeight(), 450),
                        vbAnteproyectos.heightProperty()
                ));
                if (anteproyectos.size() > 0) {
                    mostrarAnteproyectos(anteproyectos, idEstado);
                } else {
                    spListaAnteproyectos.setVisible(false);
                    pnAnteproyectosNoDisponibles.setVisible(true);
                }
                break;
        }
    }

    private void mostrarAnteproyectos(ArrayList<Anteproyecto> anteproyectos, int idEstado) {
        try {
            if (idEstado == Constantes.ASIGNADO) {
                for (Anteproyecto anteproyecto : anteproyectos) {
                    FXMLLoader cargadorAnteproyectoFragmento = new FXMLLoader(getClass().getResource("/sspger/vistas/FXMLAnteproyectoFragmento.fxml"));
                    Pane anteproyectoFragmento;
                    anteproyectoFragmento = cargadorAnteproyectoFragmento.load();
                    FXMLAnteproyectoFragmentoController anteproyectoFragmentoController = cargadorAnteproyectoFragmento.getController();
                    anteproyectoFragmentoController.cargarInformacionAnteproyecto(anteproyecto, apListaAnteproyectos);
                    vbAnteproyectos.getChildren().add(anteproyectoFragmento);

                }
            } else{
                for (Anteproyecto anteproyecto : anteproyectos) {
                    FXMLLoader cargadorAnteproyectoFragmento = new FXMLLoader(getClass().getResource("/sspger/vistas/FXMLAnteproyectoDisponibleFragmento.fxml"));
                    Pane anteproyectoFragmento;
                    anteproyectoFragmento = cargadorAnteproyectoFragmento.load();
                    FXMLAnteproyectoDisponibleFragmentoController anteproyectoDisponibleFragmentoController = cargadorAnteproyectoFragmento.getController();
                    anteproyectoDisponibleFragmentoController.cargarInformacionAnteproyecto(anteproyecto, apListaAnteproyectos);
                    vbAnteproyectos.getChildren().add(anteproyectoFragmento);

                }
            }

        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Error en la información",
                    "Hubo un error al intentar recuperar la información", Alert.AlertType.ERROR);
        }
    }

    private void buscarAnteproyectosPorEstado(int idEstado) {
        try {
            buscarAnteproyectosPorDirectorYEstado(idProfesor, idEstado);
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Error en la información",
                    "Hubo un error al intentar recuperar la información", Alert.AlertType.ERROR);
        }
    }

    private void cargarInformacionEstadosAnteproyecto() {
        estadosAnteproyecto = FXCollections.observableArrayList();
        EstadoAnteproyectoRespuesta estadoAnteproyectoBD = EstadoAnteproyectoDAO.obtenerEstadosAnteproyecto();
        switch (estadoAnteproyectoBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                estadosAnteproyecto.addAll(estadoAnteproyectoBD.getEstadosAnteproyecto());
                cbEstadoAnteproyecto.setItems(estadosAnteproyecto);
        }
    }

}
