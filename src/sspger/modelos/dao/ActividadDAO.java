
package sspger.modelos.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
