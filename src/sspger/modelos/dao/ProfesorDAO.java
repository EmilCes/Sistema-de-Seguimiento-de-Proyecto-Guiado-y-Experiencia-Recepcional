package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Profesor;
import sspger.utils.Constantes;

public class ProfesorDAO {
    
    public static int registrarProfesor(int idUsuario){
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO Profesores (idUsuario) VALUES (?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idUsuario);
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
        
    public static int modificarCuerpoAcademico(int idUsuario, int idCuerpoAcademico){
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "UPDATE Profesores SET idCuerpoAcademico = ? WHERE idUsuario = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idCuerpoAcademico);
                prepararSentencia.setInt(2, idUsuario);
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
    
    
    public static Profesor obtenerProfesorPorIdUsuario(int idUsuario){
        Profesor profesorRespuesta = new Profesor();
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion != null){
            try{
                String consulta = "SELECT idProfesor, idUsuario, idCuerpoAcademico FROM Profesores WHERE idUsuario = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idUsuario);
                ResultSet resultado = prepararSentencia.executeQuery();
                profesorRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if(resultado.next()){
                    profesorRespuesta.setIdProfesor(resultado.getInt("idProfesor"));
                    profesorRespuesta.setIdUsuario(resultado.getInt("idUsuario"));
                    profesorRespuesta.setIdCuerpoAcademico(resultado.getInt("idCuerpoAcademico"));
                }
                conexion.close();
            } catch(SQLException e){
                profesorRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            profesorRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return profesorRespuesta;
    }
    
}
