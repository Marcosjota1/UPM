package es.pruebaMarcos.demo;

public class Alumno {
    private String usuario;
    private String correo;
    private String matricula;
    private String fechaNacimiento;

    // Constructor vacío
    public Alumno() {
    }

    // Constructor con todos los atributos
    public Alumno(String usuario, String correo, String matricula, String fechaNacimiento) {
        this.usuario = usuario;
        this.correo = correo;
        this.matricula = matricula;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}