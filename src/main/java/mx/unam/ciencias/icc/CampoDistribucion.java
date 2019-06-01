package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de una {@link Distribucion}.
 */
public enum CampoDistribucion {

    /** El nombre de la Distribucion. */
    NOMBRE,
    /** El número de version de la Distribucion. */
    VERSION,
    /** La calificacion de la Distribucion. */
    CALIFICACION,
    /** La distribucion de la que deriva. */
    DERIVA,
    /** El gestor que maneja. */
    GESTOR;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
     @Override public String toString() {
         switch(this){
          case NOMBRE: return "Nombre";
          case VERSION: return "# De version";
          case CALIFICACION: return "Calificacion";
          case DERIVA: return "Deriva de";
          case GESTOR: return "Gestor";
          default: throw new IllegalArgumentException();
        }
     }
}
