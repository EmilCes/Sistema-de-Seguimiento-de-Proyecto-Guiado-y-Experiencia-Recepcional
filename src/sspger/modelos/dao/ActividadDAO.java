<<<<<<< HEAD

package sspger.modelos.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
=======
package sspger.modelos.dao;

>>>>>>> main
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Actividad;
import sspger.utils.Constantes;

<<<<<<< HEAD

public class ActividadDAO {
    public static int guardarActividad(Actividad actividad) {
    int respuesta;
    Connection conexionBD = ConexionBD.abrirConexionBD();
    
    if (conexionBD != null) {
        try {
            String sentencia = "INSERT INTO Actividades (titulo, descripcion, fechaInicio, fechaFin, archivo, idAnteproyecto) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
            prepararSentencia.setString(1, actividad.getTitulo());
            prepararSentencia.setString(2, actividad.getDescripcion());
            prepararSentencia.setDate(3, java.sql.Date.valueOf(actividad.getFechaInicio()));
            prepararSentencia.setDate(4, java.sql.Date.valueOf(actividad.getFechaFIn()));
            
            prepararSentencia.setBytes(5, obtenerBytesArchivo(actividad.getArchivo()));
            prepararSentencia.setBytes(5, null); // Usar esta línea si no se va a almacenar el archivo en la base de datos.
            prepararSentencia.setInt(6, actividad.getIdAnteproyecto());
            
            int filasAfectadas = prepararSentencia.executeUpdate();
            
            respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
            
            conexionBD.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            respuesta = Constantes.ERROR_CONSULTA;
        }
    } else {
        respuesta = Constantes.ERROR_CONEXION;
    }
    
    return respuesta;
}
    
    private static byte[] obtenerBytesArchivo(File archivo) {
    byte[] bytesArchivo = null;
    try {
        FileInputStream fis = new FileInputStream(archivo);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.close();
        fis.close();
        bytesArchivo = bos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return bytesArchivo;
}

=======
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
>>>>>>> main

}
