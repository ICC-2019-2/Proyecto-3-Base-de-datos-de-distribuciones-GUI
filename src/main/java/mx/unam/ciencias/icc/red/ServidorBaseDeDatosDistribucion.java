package mx.unam.ciencias.icc.red;

import java.io.IOException;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosDistribucion;
import mx.unam.ciencias.icc.CampoDistribucion;
import mx.unam.ciencias.icc.Distribucion;

/**
 * Clase para servidores de bases de datos de distribuciones.
 */
public class ServidorBaseDeDatosDistribucion
    extends ServidorBaseDeDatos<Distribucion> {

    /**
     * Construye un servidor de base de datos de distribuciones.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param archivo el archivo en el disco del cual cargar/guardar la base de
     *                datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatosDistribucion(int puerto, String archivo)
        throws IOException {
        super(puerto, archivo);
    }

    /**
     * Crea una base de datos de distribuciones.
     * @return una base de datos de distribuciones.
     */
    @Override public
    BaseDeDatos<Distribucion, CampoDistribucion> creaBaseDeDatos() {
        BaseDeDatosDistribucion bdd = new BaseDeDatosDistribucion();
        return bdd; 
    }
}
