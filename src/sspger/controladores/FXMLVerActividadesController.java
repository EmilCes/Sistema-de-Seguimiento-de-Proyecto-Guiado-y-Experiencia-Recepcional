

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
import sspger.modelos.dao.ActividadDAO;
import sspger.modelos.dao.AvanceAnteproyectoDAO;
import sspger.modelos.dao.EstadoActividadDAO;
import sspger.modelos.pojo.Actividad;
import sspger.modelos.pojo.ActividadRespuesta;
import sspger.modelos.pojo.EstadoActividad;
import sspger.modelos.pojo.EstadoActividadRespuesta;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;


public class FXMLVerActividadesController implements Initializable {

    @FXML
    private AnchorPane apVerActividades;
    @FXML
    private ScrollPane spListaActividades;
    @FXML
    private VBox vbActividades;
    @FXML
    private ComboBox<EstadoActividad> cbEstadosActividades;
    
    private ObservableList<EstadoActividad> estadosActividades;
    private int idAnteproyecto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbActividades.setSpacing(10);
        spListaActividades.setVisible(true);
        configurarCbEstados();
        buscarActividadesPorIdEstado(Constantes.SIN_ENTREGA);
        cbEstadosActividades.valueProperty().addListener(new ChangeListener<EstadoActividad>() {
            @Override
            public void changed(ObservableValue<? extends EstadoActividad> observable, EstadoActividad oldValue, EstadoActividad newValue) {
                if (newValue != null) {
                    buscarActividadesPorIdEstado(newValue.getIdEstado());
                }
            }

        });
    }    
    
    private void configurarCbEstados(){
        estadosActividades = FXCollections.observableArrayList();
        EstadoActividadRespuesta estadoActividadRespuesta = EstadoActividadDAO.obtenerEstadosActividad();
        switch (estadoActividadRespuesta.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                estadosActividades.addAll(estadoActividadRespuesta.getEstadosActividad());
                cbEstadosActividades.setItems(estadosActividades);
        }
    }
    
    
    private void buscarActividadesPorIdEstado(int idEstado){
        spListaActividades.setVisible(true);
        vbActividades.getChildren().clear();
        ActividadRespuesta actividadesRespuesta = ActividadDAO.obtenerActividadesPorIdEstadoYIdAnteproyecto(idEstado, idAnteproyecto);
        switch(actividadesRespuesta.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "No se pudo conectar con la base de datos. "
                        + "Intente de nuevo o hágalo más tarde", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                ArrayList<Actividad> actividades = actividadesRespuesta.getActividades();
                ObservableList<Actividad> observableActividad = FXCollections.observableArrayList(actividades);
                vbActividades.prefHeightProperty().bind(Bindings.size(observableActividad).multiply(150));
                vbActividades.minHeightProperty().bind(Bindings.createDoubleBinding(
                        () -> Math.max(vbActividades.getHeight(), 450),
                        vbActividades.heightProperty()
                ));
                if(actividades.size() > 0){
                    mostrarActividades(actividades);
                } else{
                    spListaActividades.setVisible(false);
                }
                break;
        }
    }
    
    private void mostrarActividades(ArrayList<Actividad> actividades){
        try{
            for(Actividad actividad : actividades){
                System.out.println(actividad.getTitulo());
                FXMLLoader cargadorActividadFragmento = new FXMLLoader(getClass().getResource("/sspger/vistas/FXMLActividadFragmento.fxml"));
                Pane actividadFragmento;
                actividadFragmento = cargadorActividadFragmento.load();
                FXMLActividadFragmentoController actividadFragmentoController = cargadorActividadFragmento.getController();
                actividadFragmentoController.configurarFragmentoActividad(actividad, apVerActividades);
                vbActividades.getChildren().add(actividadFragmento);
            }
        } catch(IOException ex){
            Utilidades.mostrarDialogoSimple("Error en la información",
                    "Hubo un error al intentar recuperar la información", Alert.AlertType.ERROR);
        }
    }
    
    public void setIdAnteproyecto(int idAnteproyecto){
        System.out.println(idAnteproyecto);
        this.idAnteproyecto = idAnteproyecto;
    }
    
    
}
