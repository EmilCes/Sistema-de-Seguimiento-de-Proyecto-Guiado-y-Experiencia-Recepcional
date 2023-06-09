
package sspger.modelos.pojo;
import java.io.File;
import java.time.LocalDate;


public class Actividad {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFIn;
    private File archivo;
    private int idAnteproyecto;
    private int codigoRespuesta;
    
    public Actividad() {
    }

    public Actividad(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFIn, File archivo, int idAnteproyecto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFIn = fechaFIn;
        this.archivo = archivo;
        this.idAnteproyecto = idAnteproyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFIn() {
        return fechaFIn;
    }

    public File getArchivo() {
        return archivo;
    }

    public int getIdAnteproyecto() {
        return idAnteproyecto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFIn(LocalDate fechaFIn) {
        this.fechaFIn = fechaFIn;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public void setIdAnteproyecto(int idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
    
    
}
