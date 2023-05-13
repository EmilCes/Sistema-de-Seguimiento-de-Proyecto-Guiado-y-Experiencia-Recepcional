package sspger.modelos.pojo;



public class Anteproyecto {

    private String nombreProyectoInvestigacion;
    private String nombreTrabajoRecepcional;
    private String requisitos;
    private String resultadosEsperados;
    private String fechaPublicacion;
    private String decripcionTrabajoRecepcional;
    private String descripcionProyectoInvestigacion;
    private String bibliografiaRecomendada;
    private String lineaInvestigacion;
    private String notas;
    private String nombreLGAC;
    private String tipoAnteproyecto;
    private String cuerpoAcademico;
    private String profesor;
    private String estado;
    private int numeroEstudiantes;
    private String duracionAproximada;
    private int idLGAC;
    private int idTipoAnteproyecto;
    private int idCuerpoAcademico;
    private int idProfesor; //Hace referencia al director
    private int idEstado;
    //private firmas

    public Anteproyecto() {
    }

    public Anteproyecto(String nombreProyectoInvestigacion, String nombreTrabajoRecepcional, 
            String requisitos, String resultadosEsperados, String fechaPublicacion, 
            String decripcionTrabajoRecepcional, String descripcionProyectoInvestigacion, 
            String bibliografiaRecomendada, String lineaInvestigacion, String notas, String nombreLGAC, 
            String tipoAnteproyecto, String cuerpoAcademico, String profesor, String estado, 
            String duracionAproximada, int numeroEstudiantes, int idLGAC, int idTipoAnteproyecto, 
            int idCuerpoAcademico, int idProfesor, int idEstado) {
        
        this.nombreProyectoInvestigacion = nombreProyectoInvestigacion;
        this.nombreTrabajoRecepcional = nombreTrabajoRecepcional;
        this.requisitos = requisitos;
        this.resultadosEsperados = resultadosEsperados;
        this.fechaPublicacion = fechaPublicacion;
        this.decripcionTrabajoRecepcional = decripcionTrabajoRecepcional;
        this.descripcionProyectoInvestigacion = descripcionProyectoInvestigacion;
        this.bibliografiaRecomendada = bibliografiaRecomendada;
        this.lineaInvestigacion = lineaInvestigacion;
        this.notas = notas;
        this.nombreLGAC = nombreLGAC;
        this.tipoAnteproyecto = tipoAnteproyecto;
        this.cuerpoAcademico = cuerpoAcademico;
        this.profesor = profesor;
        this.estado = estado;
        this.numeroEstudiantes = numeroEstudiantes;
        this.duracionAproximada = duracionAproximada;
        this.idLGAC = idLGAC;
        this.idTipoAnteproyecto = idTipoAnteproyecto;
        this.idCuerpoAcademico = idCuerpoAcademico;
        this.idProfesor = idProfesor;
        this.idEstado = idEstado;
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

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getDecripcionTrabajoRecepcional() {
        return decripcionTrabajoRecepcional;
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

    public String getNombreLGAC() {
        return nombreLGAC;
    }

    public String getTipoAnteproyecto() {
        return tipoAnteproyecto;
    }

    public String getCuerpoAcademico() {
        return cuerpoAcademico;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getEstado() {
        return estado;
    }

    public int getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public String getDuracionAproximada() {
        return duracionAproximada;
    }

    public int getIdLGAC() {
        return idLGAC;
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

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setDecripcionTrabajoRecepcional(String decripcionTrabajoRecepcional) {
        this.decripcionTrabajoRecepcional = decripcionTrabajoRecepcional;
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

    public void setNombreLGAC(String nombreLGAC) {
        this.nombreLGAC = nombreLGAC;
    }

    public void setTipoAnteproyecto(String tipoAnteproyecto) {
        this.tipoAnteproyecto = tipoAnteproyecto;
    }

    public void setCuerpoAcademico(String cuerpoAcademico) {
        this.cuerpoAcademico = cuerpoAcademico;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumeroEstudiantes(int numeroEstudiantes) {
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public void setDuracionAproximada(String duracionAproximada) {
        this.duracionAproximada = duracionAproximada;
    }

    public void setIdLGAC(int idLGAC) {
        this.idLGAC = idLGAC;
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
