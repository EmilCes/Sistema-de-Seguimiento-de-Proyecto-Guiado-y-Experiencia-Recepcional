package sspger.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sspger.SSPGER;

public class Utilidades {

    public static void mostrarDialogoSimple(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
    }

    public static Scene inicializarEscena(String ruta) {
        Scene escena = null;
        try {
            Parent vista = FXMLLoader.load(SSPGER.class.getResource(ruta));
            escena = new Scene(vista);
        } catch (IOException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return escena;

    }

    public static void centrarEscenario(Stage escenario) {
        Rectangle2D limitesPantalla = Screen.getPrimary().getVisualBounds();
        escenario.setX((limitesPantalla.getWidth() - escenario.getWidth()) / 2);
        escenario.setY((limitesPantalla.getHeight() - escenario.getHeight()) / 2);
    }

    public static void cambiarPane(AnchorPane escenario, String path) {
        escenario.getChildren().clear();
        try {
            FXMLLoader crearAnteproyectoLoader;
            crearAnteproyectoLoader = new FXMLLoader(Utilidades.class.getResource(path));
            AnchorPane crearAnteproyectoPane = crearAnteproyectoLoader.load();
            escenario.getChildren().add(crearAnteproyectoPane);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static <T> T cambiarPaneObtenerControlador(AnchorPane escenario, String path) {
    escenario.getChildren().clear();
    try {
        FXMLLoader loader = new FXMLLoader(Utilidades.class.getResource(path));
        AnchorPane nuevoPane = loader.load();
        escenario.getChildren().add(nuevoPane);
        return loader.getController();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
        return null;
    }
}


}
