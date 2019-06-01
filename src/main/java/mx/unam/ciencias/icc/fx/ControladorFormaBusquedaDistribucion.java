package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoDistribucion;

/**
 * Clase para el controlador del contenido del diálogo para buscar distribuciones.
 */
public class ControladorFormaBusquedaDistribucion extends ControladorForma {

    /* El combo del campo. */
    @FXML private ComboBox<CampoDistribucion> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        String s = entradaValor.getText();
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un carácter";
            break;
        case VERSION:
            m = "Buscar por version necesita un número entre 1 y 99";
            break;
        case CALIFICACION :
            m = "Buscar por calificacion necesita un número entre 1 y 10";
            break;
        case DERIVA:
            m = "Buscar por deriva de necesita al menos un carácter";
            break;
        case GESTOR:
            m = "Buscar por gestor necesita al menos un carácter";
        }
        return new Tooltip(m);
    }

    /* Verifica el valor. */
    private boolean verificaValor(String s) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:        return verificaNombre(s);
        case VERSION:       return verificaVersion(s);
        case CALIFICACION:  return verificaCalificacion(s);
        case DERIVA:        return verificaDeriva(s);
        case GESTOR:        return verificaGestor(s);
        default:            return false;
        }
    }

    /* Verifica que el nombre a buscar sea válido. */
    private boolean verificaNombre(String n) {
        return n != null && !n.isEmpty();
    }

    /* Verifica que el número de version a buscar sea válido. */
    private boolean verificaVersion(String c) {
        if (c == null || c.isEmpty())
            return false;
        double version = -1.0;
        try {
            version = Double.parseDouble(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return version >= 1.0 && version < 99.9;
    }

    /* Verifica que la calificacion a buscar sea válido. */
    private boolean verificaCalificacion(String p) {
        if (p == null || p.isEmpty())
            return false;
        int calificacion = -1;
        try {
            calificacion = Integer.parseInt(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return calificacion >= 0.0 && calificacion <= 10.0;
    }

    /* Verifica que el derivaDe a buscar sea válida. */
    private boolean verificaDeriva(String d) {
        return d != null && !d.isEmpty();
    }

    /* Verifica que el gestor a buscar sea válida. */
    private boolean verificaGestor(String g) {
        return g != null && !g.isEmpty();
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoDistribucion getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:       return entradaValor.getText();
        case VERSION:      return Double.parseDouble(entradaValor.getText());
        case CALIFICACION: return Integer.parseInt(entradaValor.getText());
        case DERIVA:       return entradaValor.getText();
        case GESTOR:       return entradaValor.getText();
        default:           return entradaValor.getText(); // No debería ocurrir.
        }
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
