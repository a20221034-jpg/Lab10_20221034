package pe.edu.pucp.iweb.beans;

public class EquipoRed {
    private int idEquipo;
    private String hostname;
    private String tipo;
    private String ipGestion;
    private String estado;
    private String fechaAlta; // Estamos tomando el formato yyyy-MM-dd
    private int idSede;

    public int getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIpGestion() {
        return ipGestion;
    }
    public void setIpGestion(String ipGestion) {
        this.ipGestion = ipGestion;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getIdSede() {
        return idSede;
    }
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
