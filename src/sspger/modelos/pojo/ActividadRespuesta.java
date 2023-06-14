
package sspger.modelos.pojo;

import java.util.ArrayList;


public class ActividadRespuesta {
    private int codigoRespuesta;
    private ArrayList<Actividad> actividades;

    public ActividadRespuesta() {
    }

    public ActividadRespuesta(int codigoRespuesta, ArrayList<Actividad> actividades) {
        this.codigoRespuesta = codigoRespuesta;
        this.actividades = actividades;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }
    
    
    
}
