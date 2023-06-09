package sspger.modelos.pojo;

import java.util.ArrayList;


public class EstadoAnteproyectoRespuesta {
  
    private int codigoRespuesta;
    private ArrayList<EstadoAnteproyecto> estadosAnteproyecto;

    public EstadoAnteproyectoRespuesta() {
    }

    public EstadoAnteproyectoRespuesta(int codigoRespuesta, ArrayList<EstadoAnteproyecto> estadosAnteproyecto) {
        this.codigoRespuesta = codigoRespuesta;
        this.estadosAnteproyecto = estadosAnteproyecto;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<EstadoAnteproyecto> getEstadosAnteproyecto() {
        return estadosAnteproyecto;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setEstadosAnteproyecto(ArrayList<EstadoAnteproyecto> estadosAnteproyecto) {
        this.estadosAnteproyecto = estadosAnteproyecto;
    }
        
}
