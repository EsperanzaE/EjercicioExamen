package examen3evaluacion;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static examen3evaluacion.Main.FILE_OUTPUT;

public class Utilidades {
    /**
     * Creo el fichero de entrada y controlo los posibles retornos
     *
     * @param fileInput
     * @return objeto tipo FileReader para poder ser procesado posteriormente por un BufferedReader. So devuelvo
     * un null no se sigue con el programa porque tenemos un error en el fichero de entrada
     */

    public static FileReader crearFicheroEntrada(String fileInput) {

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(fileInput);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("no se ha encontrado el fichero de entrada");
            fileReader = null;
        } catch (IOException ioException) {
            System.out.println("error manejando fichero Object de entrada");
            fileReader = null;
        } catch (Exception exception) {
            System.out.println("error general");
            fileReader = null;
        }
        return fileReader;
    }

    /**
     * leemos el fichero de entrada y cada registro lo incorporamos a un treeSet que además de que no admite
     * duplicados, ordena los registros, para ello la clase EmpresasFacturas tiene implementado el método
     * compareTo en el que se indica que dos registros son iguales si tienen el mismo CIF, fecha de envío y código de
     * envío. A su vez, ese método establecerá el orden del fichero ascendente por CIF, fecha de envío y código de envío.
     * <p>
     * para falicitar la comprensión, las lecturas de cada campo las meto en variables y con estas instancio la clase
     * EmpresasFacturas y lo añado al TreeSet
     *
     * @param ficheroEntrada
     * @return una lista ordenada y sin duplicados con el mismo formato que el registro de entrada
     */
    public static Set<EmpresaFacturas> procesarFichero(FileReader ficheroEntrada) {
        Set<EmpresaFacturas> listaEmpresas = new TreeSet<>();
        EmpresaFacturas empresaFacturas = null;
        BufferedReader buffer = null;
        Scanner scanner = null;
        String registro = "";
        String CIF = "", nombreEmpresa = "", fechaEnvio = "", codigoEnvio = "";

        String numfra = ""; //Número de factura: Cadena de 11 caracteres formada por números y letras.
        Double importeFraSinIva = 0.0;// Importe de la factura sin IVA. Número real
        Double importefinalFra = 0.0;
        Concepto[] conceptos = new Concepto[4];

        try {
            buffer = new BufferedReader(ficheroEntrada);
            registro = buffer.readLine();
            while (registro != null) {
                scanner = new Scanner(registro);
                while (scanner.hasNext()) {
                    CIF = scanner.next();
                    nombreEmpresa = scanner.next();
                    fechaEnvio = scanner.next();
                    codigoEnvio = scanner.next();
                    for (int i = 0; i < 4 && scanner.hasNext(); i++) {
                        numfra = scanner.next();
                        importeFraSinIva = scanner.nextDouble();
                        importefinalFra = scanner.nextDouble();
                        conceptos[i] = new Concepto(numfra, importeFraSinIva, importefinalFra);
                    }

                    empresaFacturas = new EmpresaFacturas(CIF, nombreEmpresa, fechaEnvio, codigoEnvio, conceptos);

                    listaEmpresas.add(empresaFacturas);
                }
                registro = buffer.readLine();
            }

        } catch (IOException ioException) {
            System.out.println("error de entrada salida leyendo el fichero de entrada ");
            ;
        } finally {
            try {
                ficheroEntrada.close();
            } catch (IOException ioException) {
                System.out.println("error de entrada salida cerrando el fichero de entrada ");
                ;
            }
        }
        return listaEmpresas;
    }

    /**
     * con este método se crea el fichero de salida "facturasparapagar.bin" cuyo retorno es un objeto del tipo
     * ObjectOutputStream
     *
     * @param fileOutput
     * @return
     */
    public static ObjectOutputStream crearFicheroSalida(String fileOutput) {

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileOutput));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return objectOutputStream;
    }


    /**
     * Este método procesa el Set de empresas creado anteriormente, el cual ya no tiene registros duplicados
     * y por cada registro leído lo convierte en un objeto con los atributos que nos solicita el ejercicio. Este objeto
     * se grabará posteriormente en el fichero.
     * la clase con la que se grabará el fichero de salida se llama RegistroEmpresa
     *
     * @param ficheroSalida
     * @param conjuntoEmpresas
     */
    public static void generarFicheroSalida(ObjectOutputStream ficheroSalida,
                                            Set<EmpresaFacturas> conjuntoEmpresas) {
        RegistroEmpresas registroEmpresas = null;

        for (EmpresaFacturas empresaFacturas : conjuntoEmpresas) {
            //para cada elemento del set montamos un objeto del tipo RegistroEmpresas
            registroEmpresas = montarRegistro(empresaFacturas);
            try {
                ficheroSalida.writeObject(registroEmpresas);


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
        try {
            ficheroSalida.close();
        } catch (Exception exception) {
            System.out.println("error");
        }
    }

    /**
     * volcamos las propiedades de los objetos empresaFactiras del Set en un objeto RegistroEmpresas
     * @param empresaFacturas objeto/registro con el formato solicitado para el fichero de salida
     * @return
     */
    private static RegistroEmpresas montarRegistro(EmpresaFacturas empresaFacturas) {
        RegistroEmpresas registroEmpresa = new RegistroEmpresas(empresaFacturas.getCIF(), empresaFacturas.getNombreEmpresa(),
                empresaFacturas.getFechaEnvio(), empresaFacturas.getImporteTotalSinIva(), empresaFacturas.getImporteTotalConIva(),
                empresaFacturas.getImporteBonificacion(), empresaFacturas.getCodFras());
        return registroEmpresa;
    }

    /**
     * para comprobar que el resultado es correcto, se muestra por consola el fichero de salida leyendo registro
     * a registro
     */
    public static void comprobarFicheroSalida() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_OUTPUT));
            System.out.println((RegistroEmpresas) objectInputStream.readObject());

            while (true) {

                System.out.println((RegistroEmpresas) objectInputStream.readObject());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (EOFException eofException) {
            System.out.println("se ha llegado al final del fichero");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("error general en el fichero de salida");
        }
    }
}
