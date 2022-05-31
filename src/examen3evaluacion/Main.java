package examen3evaluacion;

import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.Set;

public class Main {
    //Defino aquí las variables que van a usarse en todos los métodos del programa
    static final String FILE_INPUT = "facturasmezcladas.txt";//nombre del fichero de entrada, alojado en el directorio  de este proyecto
    static final String FILE_OUTPUT = "facturasparapagar.bin";//nombre del fichero de salida alojado en el mismo sitio
    static FileReader ficheroEntrada = null;
    static ObjectOutputStream ficheroSalida = null;
    static Set<EmpresaFacturas> setEmpresas = null;//el fichero de entrada lo voy incluir en un Set para eliminar
    //los duplicados fácilmente, solo tendré que definir convenientemente el método compareto()

    public static void main(String[] args) {
        Utilidades utilidades=new Utilidades();

        ficheroEntrada = Utilidades.crearFicheroEntrada(FILE_INPUT);//abro el fichero de entrada para poder proseguir

        if (ficheroEntrada != null) {//si existe el fichero de entrada sigo con el programa
            setEmpresas = Utilidades.procesarFichero(ficheroEntrada);//leo el fichero de entrada y lo meto en un TreeSet

            //imprimo el set para comprobar que es correcto
            System.out.println("el tamaño del TreeSet es "+ setEmpresas.size());
            for (EmpresaFacturas empresasFactura: setEmpresas) {
                System.out.println("imprimo el set linea a linea ");
                System.out.println(empresasFactura);
            }
            System.out.println();

            ficheroSalida = Utilidades.crearFicheroSalida(FILE_OUTPUT);//creo el fichero de salida, si no he tenido problemas
            //en la creación, sigo para adelante

            if (ficheroSalida != null) {//si he podido crear el fichero de salida sin problemas, sigo con el programa
                Utilidades.generarFicheroSalida(ficheroSalida, setEmpresas);//grabo en el fichero de salida el setEmpresas
                Utilidades.comprobarFicheroSalida();//voy a imprimir el fichero de salida para ver que su contenido es correcto,
                //para ello lo leeré con el mismo objeto que lo he creado
            }
        }
    }
}