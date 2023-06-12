
package sspger.modelos.pojo;


public class AvanceAnteproyecto {
    private String nombreAnteproyecto;
    private float promedio;
    private int totalActividades;
    private int codigoRespuesta;

    public AvanceAnteproyecto() {
    }

    public AvanceAnteproyecto(String nombreAnteproyecto, float promedio, int totalActividades, int codigoRespuesta) {
        this.nombreAnteproyecto = nombreAnteproyecto;
        this.promedio = promedio;
        this.totalActividades = totalActividades;
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getNombreAnteproyecto() {
        return nombreAnteproyecto;
    }

    public float getPromedio() {
        return promedio;
    }

    public int getTotalActividades() {
        return totalActividades;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setNombreAnteproyecto(String nombreAnteproyecto) {
        this.nombreAnteproyecto = nombreAnteproyecto;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public void setTotalActividades(int totalActividades) {
        this.totalActividades = totalActividades;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    
}
