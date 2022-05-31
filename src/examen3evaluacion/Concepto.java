package examen3evaluacion;

public class Concepto {
    // Cada concepto estará formado por
    private String codFra= ""; //Código de factura: Cadena de 10 caracteres formada por números y letras.
    private Double importeFraSinIva=0.0;// Importe de la factura sin IVA. Número real
    private Double importefinalFra= 0.0;//Importe final de la factura. Número real

    public Concepto(String codFra, Double importeFraSinIva, Double importefinalFra) {
        this.codFra = codFra;
        this.importeFraSinIva = importeFraSinIva;
        this.importefinalFra = importefinalFra;
    }

    public void setNumfra(String codFra) {
        this.codFra = codFra;
    }

    public void setImporteFraSinIva(Double importeFraSinIva) {
        this.importeFraSinIva = importeFraSinIva;
    }

    public void setImportefinalFra(Double importefinalFra) {
        this.importefinalFra = importefinalFra;
    }

    public String getCodFra() {
        return codFra;
    }

    public Double getImporteFraSinIva() {
        return importeFraSinIva;
    }

    public Double getImportefinalFra() {
        return importefinalFra;
    }

    @Override
    public String toString() {
        return "Concepto{" +
                "codFra='" + codFra + '\'' +
                ", importeFraSinIva=" + importeFraSinIva +
                ", importefinalFra=" + importefinalFra +
                '}';
    }
}
