package sspger.modelos.pojo;

import java.util.ArrayList;


public class TipoAnteproyectoRespuesta {
    
    private int codigoRespuesta;
    private ArrayList<TipoAnteproyecto> tipoAnteproyectos;

    public TipoAnteproyectoRespuesta() {
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<TipoAnteproyecto> getTipoAnteproyectos() {
        return tipoAnteproyectos;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setTipoAnteproyectos(ArrayList<TipoAnteproyecto> tipoAnteproyectos) {
        this.tipoAnteproyectos = tipoAnteproyectos;
    }
    
}
