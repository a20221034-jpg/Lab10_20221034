package pe.edu.pucp.iweb.dto;

public class ResumenSedeDTO {
    private String sedeNombre;
    private int totalEquipos;
    private int operativos;
    private int caidos;

    public String getSedeNombre() {
        return sedeNombre;
    }
    public void setSedeNombre(String sedeNombre) {
        this.sedeNombre = sedeNombre;
    }

    public int getTotalEquipos() {
        return totalEquipos;
    }
    public void setTotalEquipos(int totalEquipos) {
        this.totalEquipos = totalEquipos;
    }

    public int getOperativos() {
        return operativos;
    }
    public void setOperativos(int operativos) {
        this.operativos = operativos;
    }

    public int getCaidos() {
        return caidos;
    }
    public void setCaidos(int caidos) {
        this.caidos = caidos;
    }
}
