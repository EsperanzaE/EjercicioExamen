package examen3evaluacion;

import java.io.Serializable;
import java.util.Arrays;

/*
Es la clase del registro de entrada. Tiene el mismo formato que ese registro
 */
public class EmpresaFacturas extends Empresa implements Serializable, Comparable {
    //atributos:
    private String codigoEnvio = "";// Código del envío: Cadena de 8 dígitos compuesto por las iniciales de la empresa y un número único por envío.
    private Concepto[] conceptos = new Concepto[4];//Hasta un máximo de 4 conceptos a facturar.
    private double importeTotalSinIva = 0.0;
    private double importeTotalConIva = 0.0;
    private String[] codFras = null;
    private double importeBonificacion = 0.0;//	Importe de la bonificación

    /**
     * Constructor
     * @param CIF
     * @param nombreEmpresa
     * @param fechaEnvio
     * @param codigoEnvio
     * @param conceptos
     */
    public EmpresaFacturas(String CIF, String nombreEmpresa, String fechaEnvio, String codigoEnvio,
                           Concepto[] conceptos) {
        super(CIF, nombreEmpresa, fechaEnvio);
        this.codigoEnvio = codigoEnvio;
        this.conceptos = conceptos;

        this.importeTotalConIva = calcImporteTotalConIva();
        this.importeTotalSinIva = calcImporteTotalSinIva();
        this.codFras = calcCodFras();
        this.importeBonificacion = calcImporteBonificacion();
    }

    public double calcImporteBonificacion() {
        double resultado = 0.0;

        if (this.importeTotalConIva < 1000.0) {
            resultado = 0;
        } else {
            if (this.importeTotalConIva >= 1000.0 && this.importeTotalConIva <= 3000.0) {
                resultado = this.importeTotalConIva * 0.1;
            } else {
                resultado = this.importeTotalConIva * 0.15;
            }
        }
        return resultado;
    }

    public String[] calcCodFras() {
        String[] codFras = new String[4];
        for (int i = 0; i < conceptos.length; i++) {
            codFras[i] = conceptos[i].getCodFra();
        }
        return codFras;
    }

    public double calcImporteTotalSinIva() {
        double suma = 0.0;
        for (int i = 0; i < this.conceptos.length; i++) {
            suma += this.conceptos[i].getImporteFraSinIva();

        }
        return suma;
    }

    public double calcImporteTotalConIva() {
        double suma = 0.0;
        for (int i = 0; i < this.conceptos.length; i++) {
            suma += this.conceptos[i].getImportefinalFra();

        }
        return suma;
    }

    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    public Concepto[] getConceptos() {
        return conceptos;
    }

    public double getImporteTotalSinIva() {
        return importeTotalSinIva;
    }

    public void setImporteTotalSinIva(double importeTotalSinIva) {
        this.importeTotalSinIva = importeTotalSinIva;
    }

    public double getImporteTotalConIva() {
        return importeTotalConIva;
    }

    public void setImporteTotalConIva(double importeTotalConIva) {
        this.importeTotalConIva = importeTotalConIva;
    }

    public String[] getCodFras() {
        return codFras;
    }

    public void setCodFras(String[] codFras) {
        this.codFras = codFras;
    }

    public double getImporteBonificacion() {
        return importeBonificacion;
    }

    /**
     * dos registros están duplicados, son iguales, si tienen el mismo CIF, fecha de envío y código de envío.
     *
     * @param
     * @return
     */
    @Override
    public boolean equals(Object object) {
        boolean resultado = false;
        if (object instanceof EmpresaFacturas) {
            EmpresaFacturas empresaFacturas = (EmpresaFacturas) object;
            if (this == empresaFacturas) {
                resultado = true;
            } else if (super.getCIF().equals(empresaFacturas.getCIF()) &&
                    super.getFechaEnvio().equals(empresaFacturas.getFechaEnvio()) &&
                    this.codigoEnvio.equals(empresaFacturas.codigoEnvio)) {
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * para que un set ordene por un criterio concreto hay que sobreescribir el método compareTo y que la clase
     * extienda Comparable. hemos dicho que dos factura son iguales si tienesi tienen el mismo CIF, fecha de envío
     * y código de envío.
     *
     * @param object
     * @return
     */
    @Override
    public int compareTo(Object object) {
        int resultado = 0;
        if (object instanceof EmpresaFacturas) {
            EmpresaFacturas obj = (EmpresaFacturas) object;
            if (this.getCIF().compareTo(obj.getCIF()) == 0 &&
                    this.getFechaEnvio().compareTo(obj.getFechaEnvio()) == 0 &&
                    this.codigoEnvio.compareTo(obj.codigoEnvio) == 0)
                resultado = 0;
            else {
                if (this.getCIF().compareTo(obj.getCIF()) != 0) {
                    resultado = this.getCIF().compareTo(obj.getCIF());
                } else {
                    if (this.getFechaEnvio().compareTo(obj.getFechaEnvio()) != 0) {
                        resultado = this.getFechaEnvio().compareTo(obj.getFechaEnvio());
                    } else {
                        resultado = this.codigoEnvio.compareTo(obj.codigoEnvio);
                    }
                }
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "EmpresaFacturas{" +
                "codigoEnvio='" + codigoEnvio + '\'' +
                ", conceptos=" + Arrays.toString(conceptos) +
                ", importeTotalSinIva=" + importeTotalSinIva +
                ", importeTotalConIva=" + importeTotalConIva +
                ", codFras=" + Arrays.toString(codFras) +
                ", importeBonificacion=" + importeBonificacion +
                '}';
    }


}
