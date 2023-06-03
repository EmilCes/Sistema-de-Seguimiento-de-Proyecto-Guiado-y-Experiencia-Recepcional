package sspger.modelos.pojo;

import java.util.ArrayList;



public class CuerpoAcademicoRespuesta {

    private int codigoRespuesta;
    private ArrayList<CuerpoAcademico> cuerposAcademicos;

    public CuerpoAcademicoRespuesta() {
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<CuerpoAcademico> getCuerposAcademicos() {
        return cuerposAcademicos;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setCuerposAcademicos(ArrayList<CuerpoAcademico> cuerposAcademicos) {
        this.cuerposAcademicos = cuerposAcademicos;
    }
    
}
