package sspger.modelos.pojo;

import java.util.ArrayList;



public class LGACRespuesta {
    
    private int codigoRespuesta;
    private ArrayList<LGAC> lgac;

    public LGACRespuesta() {
    }

    public LGACRespuesta(int codigoRespuesta, ArrayList<LGAC> lgac) {
        this.codigoRespuesta = codigoRespuesta;
        this.lgac = lgac;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<LGAC> getLgac() {
        return lgac;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setLgac(ArrayList<LGAC> lgac) {
        this.lgac = lgac;
    }
    
}
