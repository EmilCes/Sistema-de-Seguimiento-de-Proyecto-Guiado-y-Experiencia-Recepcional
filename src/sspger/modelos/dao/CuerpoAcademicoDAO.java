package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.CuerpoAcademico;
import sspger.modelos.pojo.CuerpoAcademicoRespuesta;
import sspger.utils.Constantes;



public class CuerpoAcademicoDAO {
    
    public static CuerpoAcademicoRespuesta obtenerCuerposAcademicos(){
        CuerpoAcademicoRespuesta cuerpoAcademicoRespuesta = new CuerpoAcademicoRespuesta();
        cuerpoAcademicoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idCuerpoAcademico, nombre, descripcion, idProfesor FROM CuerposAcademicos";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet respuestaBaseDatos = prepararSentencia.executeQuery();
                ArrayList<CuerpoAcademico> cuerposAcademicosConsulta = new ArrayList();
                while(respuestaBaseDatos.next()){
                    CuerpoAcademico cuerpoAcademico = new CuerpoAcademico();
                    cuerpoAcademico.setIdCuerpoAcademico(respuestaBaseDatos.getInt("idCuerpoAcademico"));
                    cuerpoAcademico.setNombre(respuestaBaseDatos.getString("nombre"));
                    cuerpoAcademico.setDescripcion(respuestaBaseDatos.getString("descripcion"));
                    cuerpoAcademico.setIdProfesor(respuestaBaseDatos.getInt("idProfesor"));
                    cuerposAcademicosConsulta.add(cuerpoAcademico);
                }
                cuerpoAcademicoRespuesta.setCuerposAcademicos(cuerposAcademicosConsulta);
                conexionBD.close();
            } catch(SQLException e){
                cuerpoAcademicoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            cuerpoAcademicoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return cuerpoAcademicoRespuesta;
    }
    
}