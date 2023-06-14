package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.EntregaActividad;
import sspger.utils.Constantes;


public class EntregaActividadDAO {
    
    public static int registrarIdActividad(int idActividad) {
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO EntregaActividad (idActividad) VALUES (?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idActividad);
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
            } catch (SQLException e) {
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION;
        }
        return respuesta;
    }
    
public static int guardarEntregaActividad(EntregaActividad entregaActividad) {
    int respuesta;
    Connection conexionBD = ConexionBD.abrirConexionBD();
    if (conexionBD != null) {
        try {
            String sentencia = "UPDATE EntregaActividad SET comentarios = ?, archivo = ? WHERE idActividad = ?";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
            prepararSentencia.setString(1, entregaActividad.getComentarios());
            prepararSentencia.setBytes(2, entregaActividad.getArchivo());
            prepararSentencia.setInt(3, entregaActividad.getIdActividad());

            int filasAfectadas = prepararSentencia.executeUpdate();
            respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
            
            // Actualizar el idEstado de la actividad
            if (respuesta == Constantes.OPERACION_EXITOSA) {
                String sentenciaActualizacion = "UPDATE Actividades SET idEstado = ? WHERE idActividad = ?";
                PreparedStatement prepararSentenciaActualizacion = conexionBD.prepareStatement(sentenciaActualizacion);
                prepararSentenciaActualizacion.setInt(1, entregaActividad.getIdEstado());
                prepararSentenciaActualizacion.setInt(2, entregaActividad.getIdActividad());
                
                int filasActualizadas = prepararSentenciaActualizacion.executeUpdate();
                if (filasActualizadas != 1) {
                    respuesta = Constantes.ERROR_CONSULTA;
                }
            }
            
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


    public static EntregaActividad cargarInfomacionEntregaPorIdActividad(int idActividad){
        EntregaActividad entregaActividad = new EntregaActividad();
        Connection conexion = ConexionBD.abrirConexionBD();

        if (conexion != null) {
            try {
                String consulta = "SELECT comentarios, archivo FROM EntregaActividad WHERE idActividad = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idActividad);
                ResultSet resultado = prepararSentencia.executeQuery();

                if (resultado.next()) {
                    entregaActividad.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                    entregaActividad.setComentarios(resultado.getString("comentarios"));
                    entregaActividad.setArchivo(resultado.getBytes("archivo"));

                } else {
                    entregaActividad.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
                }

                conexion.close();
            } catch (SQLException e) {
                entregaActividad.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            entregaActividad.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return entregaActividad;
    }
    
    
}
