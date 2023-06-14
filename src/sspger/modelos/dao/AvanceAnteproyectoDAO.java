package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.AvanceAnteproyecto;
import sspger.utils.Constantes;

public class AvanceAnteproyectoDAO {

    public static String obtenerNombreAnteproyectoPorIdAnteproyecto(int idAnteproyecto) {
        String nombreAnteproyecto = "";
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT nombreTrabajoRecepcional FROM Anteproyecto WHERE idAnteproyecto = ?;";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                if (resultado.next()) {
                    nombreAnteproyecto = resultado.getString("nombreTrabajoRecepcional");
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nombreAnteproyecto;
    }

    public static String obtenerNombreProfesorPorIdAnteproyecto(int idAnteproyecto) {
        String nombreProfesor = "";
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT u.nombre, u.apellidoPaterno, u.apellidoMaterno FROM Usuarios u "
                        + "INNER JOIN Profesores p ON u.idUsuario = p.idUsuario "
                        + "INNER JOIN Anteproyecto a ON p.idProfesor = a.idProfesor "
                        + "WHERE a.idAnteproyecto = ?;";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                if (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    String apellidoPaterno = resultado.getString("apellidoPaterno");
                    String apellidoMaterno = resultado.getString("apellidoMaterno");
                    nombreProfesor = nombre + " " + apellidoPaterno + " " + apellidoMaterno;
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nombreProfesor;
    }

    public static int obtenerCantidadActividadesPorAnteproyecto(int idAnteproyecto) {
        int cantidadActividades = 0;
        Connection conexion = ConexionBD.abrirConexionBD();

        if (conexion != null) {
            try {
                String consulta = "SELECT COUNT(*) AS cantidad FROM Actividades WHERE idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();

                if (resultado.next()) {
                    cantidadActividades = resultado.getInt("cantidad");
                }

                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cantidadActividades;
    }

    public static ArrayList<String> obtenerNombresCompletosPorIdAnteproyecto(int idAnteproyecto) {
        ArrayList<String> nombresCompletos = new ArrayList<>();
        Connection conexion = ConexionBD.abrirConexionBD();

        if (conexion != null) {
            try {
                String consulta = "SELECT CONCAT(nombre, ' ', apellidoPaterno, ' ', apellidoMaterno) AS nombre_completo "
                        + "FROM Usuarios "
                        + "WHERE idUsuario IN (SELECT idUsuario FROM Alumnos WHERE idAnteproyecto = ?)";

                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAnteproyecto);

                ResultSet resultado = prepararSentencia.executeQuery();

                while (resultado.next()) {
                    String nombreCompleto = resultado.getString("nombre_completo");
                    nombresCompletos.add(nombreCompleto);
                }

                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nombresCompletos;
    }
  
  public static float obtenerPromedioCalificacionesPorIdAnteproyecto(int idAnteproyecto) {
      float promedio = 0;
      Connection conexion = ConexionBD.abrirConexionBD();

        if (conexion != null) {
            try {
                String consulta = "SELECT AVG(e.calificacion) AS promedio " +
                  "FROM EntregaActividad e " +
                  "JOIN Actividades a ON e.idActividad = a.idActividad " +
                  "WHERE a.idAnteproyecto = ?";

                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAnteproyecto);

                ResultSet resultado = prepararSentencia.executeQuery();

                if (resultado.next()) {
                    promedio = resultado.getInt("promedio");
                }

                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return promedio;
  }
}


