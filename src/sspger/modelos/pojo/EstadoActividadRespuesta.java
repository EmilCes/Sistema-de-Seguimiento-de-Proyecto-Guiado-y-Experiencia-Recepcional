
package sspger.modelos.pojo;

import java.util.ArrayList;


public class EstadoActividadRespuesta {
    private ArrayList<EstadoActividad> estadosActividad;
    private int codigoRespuesta;

    public EstadoActividadRespuesta() {
    }

    public EstadoActividadRespuesta(ArrayList<EstadoActividad> estadosActividad, int codigoRespuesta) {
        this.estadosActividad = estadosActividad;
        this.codigoRespuesta = codigoRespuesta;
    }

    public ArrayList<EstadoActividad> getEstadosActividad() {
        return estadosActividad;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setEstadosActividad(ArrayList<EstadoActividad> estadosActividad) {
        this.estadosActividad = estadosActividad;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
}
