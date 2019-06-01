package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mx.unam.ciencias.icc.Distribucion;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * distribuciones.
 */
public class ControladorFormaDistribucion extends ControladorForma {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el número de version. */
    @FXML private EntradaVerificable entradaVersion;
    /* La entrada verificable para la calificacion. */
    @FXML private EntradaVerificable entradaCalificacion;
    /* La entrada verificable para derivaDe. */
    @FXML private EntradaVerificable entradaDerivaDe;
    /* La entrada verificable para gestor. */
    @FXML private EntradaVerificable entradaGestor;

    /* El valor del nombre. */
    private String nombre;
    /* El valor del número de version. */
    private double version;
    /* El valor de la calificacion. */
    private int calificacion;
    /* El valor del derivaDe. */
    private String derivaDe;
    /* El valor del gestor. */
    private String gestor;

    /* El estudiante creado o editado. */
    private Distribucion distribucion;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaVersion.setVerificador(c -> verificaVersion(c));
        entradaCalificacion.setVerificador(p -> verificaCalificacion(p));
        entradaDerivaDe.setVerificador(n -> verificaDeriva(n));
        entradaGestor.setVerificador(n -> verificaGestor(n));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaDistribucion());
        entradaVersion.textProperty().addListener(
            (o, v, n) -> verificaDistribucion());
        entradaCalificacion.textProperty().addListener(
            (o, v, n) -> verificaDistribucion());
        entradaDerivaDe.textProperty().addListener(
            (o, v, n) -> verificaDistribucion());
        entradaGestor.textProperty().addListener(
            (o, v, n) -> verificaDistribucion());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaDistribucion();
        aceptado = true;
        escenario.close();
    }

    /**
     * Define la distribucion del diálogo.
     * @param distribucion la nueva distribucion del diálogo.
     */
    public void setDistribucion(Distribucion distribucion) {
        this.distribucion = distribucion;
        if (distribucion == null)
            return;
        this.distribucion = new Distribucion(null, 0.0, 0, null, null);
        this.distribucion.actualiza(distribucion);
        entradaNombre.setText(distribucion.getNombre());
        String v = String.format("%2.2f", distribucion.getVersion());
        entradaVersion.setText(v);
        String c = String.format("%02d", distribucion.getCalificacion());
        entradaCalificacion.setText(c);
        entradaDerivaDe.setText(distribucion.getDerivaDe());
        entradaGestor.setText(distribucion.getGestor());
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaDistribucion() {
        boolean n = entradaNombre.esValida();
        boolean v = entradaVersion.esValida();
        boolean c = entradaCalificacion.esValida();
        boolean d = entradaDerivaDe.esValida();
        boolean g = entradaGestor.esValida();
        botonAceptar.setDisable(!n || !v || !c || !d || !g);
    }

    /* Verifica que el nombre sea válido. */
    private boolean verificaNombre(String n) {
        if (n == null || n.isEmpty())
            return false;
        nombre = n;
        return true;
    }

    /* Verifica que el número de version sea válido. */
    private boolean verificaVersion(String v) {
        if (v == null || v.isEmpty())
            return false;
        try {
            version = Double.parseDouble(v);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return version >= 1 && version < 99;
    }

    /* Verifica que la calificacion sea válida. */
    private boolean verificaCalificacion(String c) {
        if (c == null || c.isEmpty())
            return false;
        try {
            calificacion = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return calificacion >= 0.0 && calificacion <= 10.0;
    }

    /* Verifica que el deriva de sea válido. */
    private boolean verificaDeriva(String d) {
        if (d == null || d.isEmpty())
            return false;
        derivaDe = d;
        return true;
    }

    /* Verifica que el gestor sea válido. */
    private boolean verificaGestor(String g) {
        if (g == null || g.isEmpty())
            return false;
        gestor = g;
        return true;
    }

    /* Actualiza a la distribucion, o la crea si no existe. */
    private void actualizaDistribucion() {
        if (distribucion != null) {
            distribucion.setNombre(nombre);
            distribucion.setVersion(version);
            distribucion.setCalificacion(calificacion);
            distribucion.setDerivaDe(derivaDe);
            distribucion.setGestor(gestor);
        } else {
            distribucion = new Distribucion(nombre, version, calificacion, derivaDe, gestor);
        }
    }

    /**
     * Regresa la distribucion del diálogo.
     * @return la distribucion del diálogo.
     */
    public Distribucion getDistribucion() {
        return distribucion;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }
}
