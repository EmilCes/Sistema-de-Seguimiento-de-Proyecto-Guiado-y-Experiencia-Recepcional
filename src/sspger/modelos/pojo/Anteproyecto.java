package sspger.modelos.pojo;



public class Anteproyecto {

    private int idAnteproyecto;
    private String nombreProyectoInvestigacion;
    private String nombreTrabajoRecepcional;
    private String requisitos;
    private String resultadosEsperados;
    private int numeroEstudiantes;
    private String fechaPublicacion;
    private String duracionAproximada;
    private String descripcionTrabajoRecepcional;
    private String descripcionProyectoInvestigacion;
    private String bibliografiaRecomendada;
    private String lineaInvestigacion;
    private String notas;
    private int idLAGC;
    private int idTipoAnteproyecto;
    private int idCuerpoAcademico;
    private int idProfesor;
    private int idEstado;

    public Anteproyecto() {
        
    }

    public Anteproyecto(int idAnteproyecto, String nombreProyectoInvestigacion, String nombreTrabajoRecepcional,
                        String requisitos, String resultadosEsperados, int numeroEstudiantes, String fechaPublicacion,
                        String duracionAproximada, String descripcionTrabajoRecepcional, String descripcionProyectoInvestigacion, 
                        String bibliografiaRecomendada, String lineaInvestigacion, String notas, int idLAGC, int idTipoAnteproyecto, 
                        int idCuerpoAcademico, int idProfesor, int idEstado) {
        this.idAnteproyecto = idAnteproyecto;
        this.nombreProyectoInvestigacion = nombreProyectoInvestigacion;
        this.nombreTrabajoRecepcional = nombreTrabajoRecepcional;
        this.requisitos = requisitos;
        this.resultadosEsperados = resultadosEsperados;
        this.numeroEstudiantes = numeroEstudiantes;
        this.fechaPublicacion = fechaPublicacion;
        this.duracionAproximada = duracionAproximada;
        this.descripcionTrabajoRecepcional = descripcionTrabajoRecepcional;
        this.descripcionProyectoInvestigacion = descripcionProyectoInvestigacion;
        this.bibliografiaRecomendada = bibliografiaRecomendada;
        this.lineaInvestigacion = lineaInvestigacion;
        this.notas = notas;
        this.idLAGC = idLAGC;
        this.idTipoAnteproyecto = idTipoAnteproyecto;
        this.idCuerpoAcademico = idCuerpoAcademico;
        this.idProfesor = idProfesor;
        this.idEstado = idEstado;
    }

    public int getIdAnteproyecto() {
        return idAnteproyecto;
    }

    public String getNombreProyectoInvestigacion() {
        return nombreProyectoInvestigacion;
    }

    public String getNombreTrabajoRecepcional() {
        return nombreTrabajoRecepcional;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public String getResultadosEsperados() {
        return resultadosEsperados;
    }

    public int getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getDuracionAproximada() {
        return duracionAproximada;
    }

    public String getDescripcionTrabajoRecepcional() {
        return descripcionTrabajoRecepcional;
    }

    public String getDescripcionProyectoInvestigacion() {
        return descripcionProyectoInvestigacion;
    }

    public String getBibliografiaRecomendada() {
        return bibliografiaRecomendada;
    }

    public String getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public String getNotas() {
        return notas;
    }

    public int getIdLAGC() {
        return idLAGC;
    }

    public int getIdTipoAnteproyecto() {
        return idTipoAnteproyecto;
    }

    public int getIdCuerpoAcademico() {
        return idCuerpoAcademico;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdAnteproyecto(int idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }

    public void setNombreProyectoInvestigacion(String nombreProyectoInvestigacion) {
        this.nombreProyectoInvestigacion = nombreProyectoInvestigacion;
    }

    public void setNombreTrabajoRecepcional(String nombreTrabajoRecepcional) {
        this.nombreTrabajoRecepcional = nombreTrabajoRecepcional;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public void setResultadosEsperados(String resultadosEsperados) {
        this.resultadosEsperados = resultadosEsperados;
    }

    public void setNumeroEstudiantes(int numeroEstudiantes) {
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setDuracionAproximada(String duracionAproximada) {
        this.duracionAproximada = duracionAproximada;
    }

    public void setDescripcionTrabajoRecepcional(String descripcionTrabajoRecepcional) {
        this.descripcionTrabajoRecepcional = descripcionTrabajoRecepcional;
    }

    public void setDescripcionProyectoInvestigacion(String descripcionProyectoInvestigacion) {
        this.descripcionProyectoInvestigacion = descripcionProyectoInvestigacion;
    }

    public void setBibliografiaRecomendada(String bibliografiaRecomendada) {
        this.bibliografiaRecomendada = bibliografiaRecomendada;
    }

    public void setLineaInvestigacion(String lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setIdLAGC(int idLAGC) {
        this.idLAGC = idLAGC;
    }

    public void setIdTipoAnteproyecto(int idTipoAnteproyecto) {
        this.idTipoAnteproyecto = idTipoAnteproyecto;
    }

    public void setIdCuerpoAcademico(int idCuerpoAcademico) {
        this.idCuerpoAcademico = idCuerpoAcademico;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }  
}
