package examen3evaluacion;

import java.io.Serializable;
import java.util.Arrays;

public class RegistroEmpresas extends Empresa implements Serializable {
    //atributos:

    private double importeTotalSinIva = 0.0;
    private double importeTotalConIva = 0.0;
    private double importeBonificacion = 0.0;//	Importe de la bonificación
    private String[] codFras = null;

    public RegistroEmpresas(String CIF, String nombreEmpresa, String fechaEnvio, double importeTotalSinIva,
                            double importeTotalConIva, double importeBonificacion, String[] codFras ) {
        super(CIF, nombreEmpresa, fechaEnvio);

        this.importeTotalSinIva = importeTotalSinIva;
        this.importeTotalConIva = importeTotalConIva;
        this.codFras = codFras;
        this.importeBonificacion = importeBonificacion;
    }

    @Override
    public String toString() {
        return "RegistroEmpresas{" + super.toString()+
                "importeTotalSinIva=" + importeTotalSinIva +
                ", importeTotalConIva=" + importeTotalConIva +
                ", importeBonificacion=" + importeBonificacion +
                ", codFras=" + Arrays.toString(codFras) +
                '}';
    }


}
