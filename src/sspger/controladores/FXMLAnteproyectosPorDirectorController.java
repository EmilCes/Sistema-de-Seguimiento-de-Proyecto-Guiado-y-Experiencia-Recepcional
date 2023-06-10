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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.dao.ProfesorDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.modelos.pojo.Usuario;
import sspger.modelos.pojo.UsuarioRespuesta;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLAnteproyectosPorDirectorController implements Initializable {

    @FXML
    private AnchorPane apListaAnteproyectosPorProfesor;
    @FXML
    private VBox vbAnteproyectos;
    @FXML
    private ComboBox<Usuario> cbDirectores;

    private ObservableList<Usuario> directores;
    @FXML
    private ScrollPane spListaAnteproyectos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionDirectores();
        cbDirectores.valueProperty().addListener(new ChangeListener<Usuario>() {
            @Override
            public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
                if (newValue != null) {
                    int idProfesor = ProfesorDAO.obtenerProfesorPorIdUsuario(newValue.getIdUsuario()).getIdProfesor();
                    try {
                        buscarAnteproyectosPorDirector(idProfesor);
                    } catch (IOException ex) {
                        Utilidades.mostrarDialogoSimple("Error en la información",
                                "Hubo un error al intentar recuperar la información", Alert.AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void buscarAnteproyectosPorDirector(int idDirector) throws IOException {
        spListaAnteproyectos.setVisible(true);
        vbAnteproyectos.getChildren().clear();
        AnteproyectoRespuesta anteproyectoRespuesta = AnteproyectoDAO.obtenerAnteproyectosPorDirector(idDirector);
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
                        () -> Math.max(vbAnteproyectos.getHeight(), 490),
                        vbAnteproyectos.heightProperty()
                ));
                if (anteproyectos.size() > 0) {
                    for (Anteproyecto anteproyecto : anteproyectos) {
                        FXMLLoader cargadorAnteproyectoFragmento = new FXMLLoader(getClass().getResource("/sspger/vistas/FXMLAnteproyectoFragmento.fxml"));
                        Pane anteproyectoFragmento = cargadorAnteproyectoFragmento.load();
                        FXMLAnteproyectoFragmentoController anteproyectoFragmentoController = cargadorAnteproyectoFragmento.getController();
                        anteproyectoFragmentoController.cargarInformacionAnteproyecto(anteproyecto, apListaAnteproyectosPorProfesor);
                        vbAnteproyectos.getChildren().add(anteproyectoFragmento);
                    }
                } else {
                    spListaAnteproyectos.setVisible(false);
                }

        }
    }

    private void cargarInformacionDirectores() {
        directores = FXCollections.observableArrayList();
        UsuarioRespuesta directoresBD = UsuarioDAO.obtenerUsuarioPorTipoUsuario(Constantes.DIRECTOR);
        switch (directoresBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                directores.addAll(directoresBD.getUsuarios());
                cbDirectores.setItems(directores);
                break;
        }
    }

}
