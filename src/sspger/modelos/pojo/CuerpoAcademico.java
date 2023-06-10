package sspger.modelos.pojo;


public class CuerpoAcademico {

    private int idCuerpoAcademico;
    private String nombre;
    private String descripcion;
    private int idProfesor;

    public CuerpoAcademico() {
    }

    public CuerpoAcademico(int idCuerpoAcademico, String nombre, String descripcion, int idProfesor) {
        this.idCuerpoAcademico = idCuerpoAcademico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProfesor = idProfesor;
    }

    public int getIdCuerpoAcademico() {
        return idCuerpoAcademico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdCuerpoAcademico(int idCuerpoAcademico) {
        this.idCuerpoAcademico = idCuerpoAcademico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
         if (obj == this) {
            return true;
        }

        if (!(obj instanceof CuerpoAcademico)) {
            return false;
        }
        
        CuerpoAcademico cuerpoAcademico = (CuerpoAcademico) obj;
        
        if(cuerpoAcademico.idCuerpoAcademico == this.idCuerpoAcademico){
            return true;
        }
        return false;
    }
    
    
}
