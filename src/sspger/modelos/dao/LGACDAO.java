package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.LGAC;
import sspger.modelos.pojo.LGACRespuesta;
import sspger.utils.Constantes;



public class LGACDAO {
    
    public static LGACRespuesta obtenerLGACPorCuerpoAcademicos(int idCuerpoAcademico){
        LGACRespuesta lgacRespuesta = new LGACRespuesta();
        lgacRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idLGAC, nombreLGAC, descripcionLGAC, idCuerpoAcademico FROM LGAC WHERE idCuerpoAcademico = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idCuerpoAcademico);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<LGAC> lgacConsulta = new ArrayList();
                while(resultado.next()){
                    LGAC lgac = new LGAC();
                    lgac.setIdLGAC(resultado.getInt("idLGAC"));
                    lgac.setNombreLGAC(resultado.getString("nombreLGAC"));
                    lgac.setDescripcionLGAC(resultado.getString("descripcionLGAC"));
                    lgac.setIdCuerpoAcademico(resultado.getInt("idCuerpoAcademico"));
                    lgacConsulta.add(lgac);
                }
                lgacRespuesta.setLgac(lgacConsulta);
            } catch (SQLException e) {
                lgacRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            lgacRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return lgacRespuesta;
    }
}
