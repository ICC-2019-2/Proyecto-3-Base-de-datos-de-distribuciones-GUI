package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede cargarse y guardarse utilizando objetos de las clases {@link
 * BufferedReader} y {@link BufferedWriter} como entrada y salida
 * respectivamente, además de determinar si sus campos cazan valores
 * arbitrarios y actualizarse con los valores de otro estudiante.
 */
public class Distribucion implements Registro<Distribucion, CampoDistribucion> {

    /* Nombre del distribucion. */
    private StringProperty nombre;
    /* Version de la distribucion. */
    private DoubleProperty version;
    /* calificacion.*/
    private IntegerProperty calificacion;
    /* Deriva de otra distribucion.*/
    private StringProperty derivaDe;
    /*Gestor de paquetes*/
    private StringProperty gestor;


     /**                                                                                                                                                
     * Define el estado inicial de un distribucion.                                                                                                    
     * @param nombre el nombre del distribucion.                                                                                                       
     * @param version el número de la version de la distribucion.                                                                                      
     * @param calificacion la calificacion otorgada a la distribucion.                                                                                 
     * @param derivaDe de que otra distribucion deriva.                                                                                                
     * @param gestor el nombre del gestor de paquetes.                                                                                                 
     */
    public Distribucion(String nombre,
                      double version,
                      int    calificacion,
                      String derivaDe,
                      String gestor) {
        this.nombre = new SimpleStringProperty(nombre);
        this.version = new SimpleDoubleProperty(version);
        this.calificacion = new SimpleIntegerProperty(calificacion);
        this.derivaDe = new SimpleStringProperty(derivaDe);
        this.gestor = new SimpleStringProperty(gestor);
    }
    public Distribucion(){}

    /**
     * Regresa el nombre de la distribucion.
     * @return el nombre de la distribucion.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre de la distribucion.
     * @param nombre el nuevo nombre de la distribucion.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa el número de version de la distribucion.
     * @return el número de version de la distribucion.
     */
    public double getVersion() {
      return this.version.get();
    }

    /**
     * Define el número version de la distribucion.
     * @param version el nuevo número de version de la distribucion.
     */
    public void setVersion(double version) {
        this.version.set(version);
    }

    /**
     * Regresa la propiedad del número de version.
     * @return la propiedad del número de version.
     */
    public DoubleProperty versionProperty() {
        return this.version;
    }

    /**
     * Regresa la calificacion de la distribucion.
     * @return la calificacion de la distribucion.
     */
    public int getCalificacion() {
        return this.calificacion.get();
    }

    /**
     * Define la calificacion de la distribucion.
     * @param calificacion de la nueva distribucion.
     */
    public void setCalificacion(int calificacion) {
        this.calificacion.set(calificacion);
    }

    /**
     * Regresa la propiedad del calificacion.
     * @return la propiedad del calificacion.
     */
    public IntegerProperty calificacionProperty() {
        return this.calificacion;
    }

    /**
     * Regresa el derivaDe de la distribucion.
     * @return el derivaDe de la distribucion.
     */
    public String getDerivaDe() {
        return this.derivaDe.get();
    }

    /**
     * Define el derivaDe de la distribucion.
     * @param derivaDe el nuevo derivaDe de la distribucion.
     */
    public void setDerivaDe(String derivaDe) {
        this.derivaDe.set(derivaDe);
    }

    /**
     * Regresa la propiedad del derivaDe
     * @return la propiedad del derivaDe
     */
    public StringProperty derivaDeProperty() {
        return this.derivaDe;
    }

    /**
     * Regresa el gestor de la distribucion.
     * @return el gestor de la distribucion.
     */
    public String getGestor() {
        return this.gestor.get();
    }

    /**
     * Define el gestor de la distribucion.
     * @param gestor el nuevo gestor de la distribucion.
     */
    public void setGestor(String gestor) {
        this.gestor.set(gestor);
    }

    /**
     * Regresa la propiedad del gestor
     * @return la propiedad del gestor
     */
    public StringProperty gestorProperty() {
        return this.gestor;
    }

    /**
     * Regresa una representación en cadena de la distribucion.
     * @return una representación en cadena de la distribucion.
     */
     public String toString() {
        String cadena = String.format("Nombre               : %s\n" +
                                      "Version              : %2.2f\n" +
                                      "Calificacion         : %02d\n" +
                                      "Deriva de            : %s\n" +
                                      "Gestor de paquetes   : %s",
                                      this.getNombre(), 
                                      this.getVersion(), 
                                      this.getCalificacion(), 
                                      this.getDerivaDe(), 
                                      this.getGestor());
        return cadena;
      }

    /**
     * Nos dice si el objeto recibido es una distribucion igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que la distribucion se comparará.
     * @return <tt>true</tt> si el objeto es un distribucion con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <tt>false</tt> en otro caso.
     */
     @Override
      public boolean equals(Object objeto) {
          if(!(objeto instanceof Distribucion)) return false;
          Distribucion distribucion = (Distribucion) objeto;
          if(distribucion == null) 
            return false;
          if(this.getNombre().equals(distribucion.getNombre()) && 
            this.getVersion() == distribucion.getVersion() && 
            this.getCalificacion() == distribucion.getCalificacion() && 
            this.getDerivaDe().equals(distribucion.getDerivaDe()) &&
            this.getGestor().equals(distribucion.getGestor()))
             return true;
          return false;
      }

     /**
      * Guarda la distribucion en la salida recibida.
      * @param out la salida dónde hay que guardar a la distribucion.
      * @throws IOException si un error de entrada/salida ocurre.
      */
     @Override public void guarda(BufferedWriter out) throws IOException {
         out.write(String.format("%s\t%2.2f\t%02d\t%s\t%s\n",
                   this.getNombre(),
                   this.getVersion(),
                   this.getCalificacion(),
                   this.getDerivaDe(),
                   this.getGestor()));
     }

     /**
      * Carga la distribucion de la entrada recibida.
      * @param in la entrada de dónde hay que cargar la distribucion.
      * @return <tt>true</tt> si el método carga una distribucion válida,
      *         <tt>false</tt> en otro caso.
      * @throws IOException si un error de entrada/salida ocurre, o si la entrada
      *         recibida no contiene una distribucion.
      */
     @Override public boolean carga(BufferedReader in) throws IOException {
         String l = in.readLine();
         if(l == null) return false;
         l = l.trim();
         if(l.equals(""))return false;
         String [] t = l.split("\t");
         if(t.length != 5) throw new IOException("Numero de campos inválido");
         setNombre(t[0]);
         try{
           setVersion(Double.parseDouble(t[1]));
           setCalificacion(Integer.parseInt(t[2]));
           setDerivaDe(t[3]);
           setGestor(t[4]);
         }catch(NumberFormatException nfe){
           throw new IOException("Datos inválidos");
         }
         return true;
     }

     /**
      * Nos dice si la distribucion caza el valor dado en el campo especificado.
      * @param campo el campo que hay que cazar.
      * @param valor el valor con el que debe cazar el campo del registro.
      * @return <tt>true</tt> si:
      *         <ul>
      *           <li><tt>campo</tt> es {@link CampoDistribucion#NOMBRE} y
      *              <tt>valor</tt> es instancia de {@link String} y es una
      *              subcadena del nombre de la distribucion.</li>
      *           <li><tt>campo</tt> es {@link CampoDistribucion#VERSION} y
      *              <tt>valor</tt> es instancia de {@link Double} y su
      *              valor doble es mayor o igual a la version de la
      *              distribucion.</li>
      *           <li><tt>campo</tt> es {@link CampoDistribucion#CALIFICACION} y
      *              <tt>valor</tt> es instancia de {@link Integer} y su
      *              valor doble es mayor o igual a la calificacion de la
      *              distribucion.</li>
      *           <li><tt>campo</tt> es {@link CampoDistribucion#DERIVA} y
      *              <tt>valor</tt> es instancia de {@link String} y es
      *              una subcadena del deriva de de la distribucion.</li>
      *           <li><tt>campo</tt> es {@link CampoDistribucion#GESTOR} y
      *              <tt>valor</tt> es instancia de {@link String} y es
      *              una subcadena del gestor de de la distribucion.</li>
      *         </ul>
      *         <tt>false</tt> en otro caso.
      * @throws IllegalArgumentException si el campo no es instancia de
      *         {@link CampoDistribucion}.
      */
     @Override public boolean caza(CampoDistribucion campo, Object valor) {
         if (!(campo instanceof CampoDistribucion))
             throw new IllegalArgumentException("El campo debe ser " +
                                                "CampoDistribucion");
         CampoDistribucion c = (CampoDistribucion)campo;
         switch(c){
          case NOMBRE: 
            return cazaNombre(valor);
          case VERSION: 
            return cazaVersion(valor);
          case CALIFICACION: 
            return cazaCalificacion(valor);
          case DERIVA: 
            return cazaDeriva(valor);
          case GESTOR: 
            return cazaGestor(valor);
          default: 
            return false;
        }
     }

     private boolean cazaNombre(Object o){
       if(!(o instanceof String))return false;
       String v = (String) o;
       if(v.isEmpty()) return false;
       return getNombre().indexOf(v) !=-1;
     }

     private boolean cazaVersion(Object o){
      if(!(o instanceof Double))
        return false;
        Double v = (Double)o;
        return getVersion() >= v.doubleValue();
     }

     private boolean cazaCalificacion(Object o){
      if(!(o instanceof Integer))
        return false;
      Integer v = (Integer)o;
      return getCalificacion() >= v.intValue();
     }

     private boolean cazaDeriva(Object o){
      if(!(o instanceof String))
        return false;
      String v = (String)o;
      if(v.isEmpty())
        return false;
      return getDerivaDe().indexOf(v)!=-1;
     }

     private boolean cazaGestor(Object o){
      if(!(o instanceof String))
        return false;
      String v = (String)o;
      if(v.isEmpty())
        return false;
      return getGestor().indexOf(v)!=-1;
     }

    /**
     * Actualiza los valores de la distribucion con los del registro recibido.
     * @param distribucion la distribucion con la cual actualizar los valores.
     */
    @Override public void actualiza(Distribucion distribucion) {
        setNombre(distribucion.getNombre());
        setVersion(distribucion.getVersion());
        setCalificacion(distribucion.getCalificacion());
        setDerivaDe(distribucion.getDerivaDe());
        setGestor(distribucion.getGestor());
    }
}
