package sspger.modelos.pojo;


public class LGAC {

    private int idLGAC;
    private String nombreLGAC;
    private String descripcionLGAC;
    private int idCuerpoAcademico;

    public LGAC() {
    }

    public LGAC(int idLGAC, String nombreLGAC, String descripcionLGAC, int idCuerpoAcademico) {
        this.idLGAC = idLGAC;
        this.nombreLGAC = nombreLGAC;
        this.descripcionLGAC = descripcionLGAC;
        this.idCuerpoAcademico = idCuerpoAcademico;
    }

    public int getIdLGAC() {
        return idLGAC;
    }

    public String getNombreLGAC() {
        return nombreLGAC;
    }

    public String getDescripcionLGAC() {
        return descripcionLGAC;
    }

    public int getIdCuerpoAcademico() {
        return idCuerpoAcademico;
    }

    public void setIdLGAC(int idLGAC) {
        this.idLGAC = idLGAC;
    }

    public void setNombreLGAC(String nombreLGAC) {
        this.nombreLGAC = nombreLGAC;
    }

    public void setDescripcionLGAC(String descripcionLGAC) {
        this.descripcionLGAC = descripcionLGAC;
    }

    public void setIdCuerpoAcademico(int idCuerpoAcademico) {
        this.idCuerpoAcademico = idCuerpoAcademico;
    }

    @Override
    public String toString() {
        return nombreLGAC;
    }
      
}
