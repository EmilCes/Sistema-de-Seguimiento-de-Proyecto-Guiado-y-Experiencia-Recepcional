
package sspger.modelos.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Actividad;
import sspger.utils.Constantes;



public class ActividadDAO {

    public static int guardarActividad(Actividad actividad) {
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO Actividades (titulo, descripcion, fechaInicio, fechaFin, idAnteproyecto) " +
                                   " VALUES (?, ?, ?, ?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, actividad.getTitulo());
                prepararSentencia.setString(2, actividad.getDescripcion());
                prepararSentencia.setString(3, actividad.getFechaInicio());
                prepararSentencia.setString(4, actividad.getFechaFin());
                prepararSentencia.setInt(5, actividad.getIdAnteproyecto());
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
    
public static Actividad obtenerInformacionActividaPorIdActividad(int idActividad) {
    Actividad actividad = new Actividad();
    Connection conexion = ConexionBD.abrirConexionBD();
    
    if (conexion != null) {
        try {
            String consulta = "SELECT titulo, descripcion, fechaInicio, fechaFin FROM Actividad WHERE idActividad = ?";
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


    
    

}
