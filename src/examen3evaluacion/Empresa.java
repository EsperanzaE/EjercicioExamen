package examen3evaluacion;

import java.io.Serializable;

abstract class Empresa implements Serializable {
    //atributos
    private String CIF=""; //CIF de la empresa farmacéutica. Alfanumérico de 9 dígitos
    private String nombreEmpresa = "";// Nombre de la empresa farmacéutica. Cadena de longitud variable
    private String fechaEnvio= ""; //Fecha del envío. Cadena de 8 dígitos (AAAAMMDD)

    @Override
    public String toString() {
        return "Empresa{" +
                "CIF='" + CIF + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", fechaEnvio='" + fechaEnvio + '\'' +
                '}';
    }

    public Empresa(String CIF, String nombreEmpresa, String fechaEnvio) {
        this.CIF = CIF;
        this.nombreEmpresa = nombreEmpresa;
        this.fechaEnvio = fechaEnvio;
    }

    public String getCIF() {
        return CIF;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }
}
