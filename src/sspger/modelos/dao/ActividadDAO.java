package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Actividad;
import sspger.modelos.pojo.ActividadRespuesta;
import sspger.utils.Constantes;

public class ActividadDAO {

    public static int guardarActividad(Actividad actividad) {
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO Actividades (titulo, descripcion, fechaInicio, fechaFin, idAnteproyecto, idEstado) "
                        + " VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, actividad.getTitulo());
                prepararSentencia.setString(2, actividad.getDescripcion());
                prepararSentencia.setString(3, actividad.getFechaInicio());
                prepararSentencia.setString(4, actividad.getFechaFin());
                prepararSentencia.setInt(5, actividad.getIdAnteproyecto());
                prepararSentencia.setInt(6, actividad.getIdEstadoActividad());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION;
        }
        return respuesta;
    }

    public static int modificarActividad(Actividad actividad) {
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "UPDATE Actividades SET titulo = ?, descripcion = ?, fechaInicio = ?, fechaFin = ? WHERE idActividad = ?";

                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, actividad.getTitulo());
                prepararSentencia.setString(2, actividad.getDescripcion());
                prepararSentencia.setString(3, actividad.getFechaInicio());
                prepararSentencia.setString(4, actividad.getFechaFin());
                prepararSentencia.setInt(5, actividad.getIdActividad());

                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                respuesta = Constantes.ERROR_CONSULTA;
            } finally {
                try {
                    if (conexionBD != null) {
                        conexionBD.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION;
        }
        return respuesta;
    }

    public static Actividad obtenerInformacionActividaPorIdActividad(int idActividad) {
        Actividad actividad = new Actividad();
        Connection conexion = ConexionBD.abrirConexionBD();

        if (conexion != null) {
            try {
                String consulta = "SELECT titulo, descripcion, fechaInicio, fechaFin FROM Actividades WHERE idActividad = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idActividad);
                ResultSet resultado = prepararSentencia.executeQuery();

                if (resultado.next()) {
                    actividad.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                    actividad.setTitulo(resultado.getString("titulo"));
                    actividad.setDescripcion(resultado.getString("descripcion"));

                    // Conversión de tipo de Date a String
                    Date fechaInicio = resultado.getDate("fechaInicio");
                    String fechaInicioString = fechaInicio.toString();
                    actividad.setFechaInicio(fechaInicioString);

                    Date fechaFin = resultado.getDate("fechaFin");
                    String fechaFinString = fechaFin.toString();
                    actividad.setFechaFin(fechaFinString);
                } else {
                    actividad.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
                }

                conexion.close();
            } catch (SQLException e) {
                actividad.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            actividad.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }

        return actividad;
    }
    

    return actividad;
}


public static ActividadRespuesta obtenerActividadesPorIdEstadoYIdAnteproyecto(int idEstado, int idAnteproyecto){
    ActividadRespuesta actividadRespuesta = new ActividadRespuesta();
    actividadRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
    Connection conexion = ConexionBD.abrirConexionBD();
    if(conexion != null){
        try{
            String sentencia = "SELECT a.idActividad, titulo, descripcion, fechaInicio, fechaFin, idAnteproyecto, idEstado, ea.calificacion FROM Actividades a "
                    + "INNER JOIN EntregaActividad ea ON ea.idActividad = a.idActividad WHERE idEstado = ? AND idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idEstado);
                prepararSentencia.setInt(2, idAnteproyecto);
                ResultSet respuestaBaseDatos = prepararSentencia.executeQuery();
                ArrayList<Actividad> actividadConsulta = new ArrayList();
                while(respuestaBaseDatos.next()){
                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(respuestaBaseDatos.getInt("idActividad"));
                    actividad.setTitulo(respuestaBaseDatos.getString("titulo"));
                    actividad.setDescripcion(respuestaBaseDatos.getString("descripcion"));
                    actividad.setFechaInicio(respuestaBaseDatos.getString("fechaInicio"));
                    actividad.setFechaFin(respuestaBaseDatos.getString("fechaFin"));
                    actividad.setIdAnteproyecto(respuestaBaseDatos.getInt("idAnteproyecto"));
                    actividad.setIdEstadoActividad(respuestaBaseDatos.getInt("idEstado"));
                    actividad.setCalificacion(respuestaBaseDatos.getFloat("calificacion"));
                    actividadConsulta.add(actividad);
                }
                actividadRespuesta.setActividades(actividadConsulta);
                conexion.close();
            } catch(SQLException e){
                System.out.println(e.getMessage());
                actividadRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            actividadRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return actividadRespuesta;
    }
    
    
    public static Actividad obtenerUltimaActividad() {
        Actividad actividad = new Actividad();
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT idActividad FROM Actividades ORDER BY idActividad DESC LIMIT 1";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                actividad.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if (resultado.next()) {
                    actividad.setIdActividad(resultado.getInt("idActividad"));
                }
                conexion.close();
            } catch (SQLException e) {
                actividad.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            actividad.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return actividad;
    }

}
