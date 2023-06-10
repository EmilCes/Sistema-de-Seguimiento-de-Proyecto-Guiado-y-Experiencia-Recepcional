package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Profesor;
import sspger.utils.Constantes;

public class ProfesorDAO {
    
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
