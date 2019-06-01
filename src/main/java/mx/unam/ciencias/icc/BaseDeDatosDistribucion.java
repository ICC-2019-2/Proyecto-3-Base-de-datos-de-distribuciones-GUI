package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de distribuciones de Linux.
 */
public class BaseDeDatosDistribucion extends BaseDeDatos<Distribucion, CampoDistribucion> {
    /**
     * Crea una distribucion en blanco.
     * @return una distribucion en blanco.
     */
     @Override public Distribucion creaRegistro() {
         return new Distribucion(null, 0, 0, null, null);
     }
}
