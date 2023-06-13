
package sspger.modelos.pojo;


public class EstadoActividad {
    private int idEstado;
    private String descripcion;
    private int codigoRespuesta;

    public EstadoActividad() {
    }

    public EstadoActividad(int idEstado, String descripcion, int codigoRespuesta) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
        this.codigoRespuesta = codigoRespuesta;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
     @Override
    public String toString() {
        return descripcion;
    }
    
    
}
