package mx.unam.ciencias.icc.fx;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import mx.unam.ciencias.icc.Distribucion;
import mx.unam.ciencias.icc.Lista;

/**
 * Clase para el controlador de la tabla para mostrar la base de datos.
 */
public class ControladorTablaDistribucion {

    /* La tabla. */
    @FXML private TableView<Distribucion> tabla;

    /* La columna del nombre. */
    @FXML private TableColumn<Distribucion, String> columnaNombre;
    /* La columna del número de version. */
    @FXML private TableColumn<Distribucion, Number> columnaVersion;
    /* La columna de la calificacion. */
    @FXML private TableColumn<Distribucion, Number> columnaCalificacion;
    /* La columna del derivaDe. */
    @FXML private TableColumn<Distribucion, String> columnaDeriva;
    /* La columna del gestor. */
    @FXML private TableColumn<Distribucion, String> columnaGestor;

    /* El modelo de la selección. */
    TableView.TableViewSelectionModel<Distribucion> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Lista de escuchas de selección. */
    private Lista<EscuchaSeleccion> escuchas;
    /* Los renglones en la tabla. */
    private ObservableList<Distribucion> renglones;

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        renglones = tabla.getItems();
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        ListChangeListener<TablePosition> lcl = c -> cambioEnSeleccion();
        seleccion.addListener(lcl);
        columnaNombre.setCellValueFactory(c -> c.getValue().nombreProperty());
        columnaVersion.setCellValueFactory(c -> c.getValue().versionProperty());
        columnaCalificacion.setCellValueFactory(c -> c.getValue().calificacionProperty());
        columnaDeriva.setCellValueFactory(c -> c.getValue().derivaDeProperty());
        columnaGestor.setCellValueFactory(c -> c.getValue().gestorProperty());
        escuchas = new Lista<EscuchaSeleccion>();
    }

    /* Notifica a los escuchas que hubo un cambio en la selección. */
    private void cambioEnSeleccion() {
        for (EscuchaSeleccion escucha : escuchas)
            escucha.renglonesSeleccionados(seleccion.size());
    }

    /**
     * Limpia la tabla.
     */
    public void limpiaTabla() {
        renglones.clear();
    }

    /**
     * Agrega un renglón a la tabla.
     * @param distribucion el renglón a agregar.
     */
    public void agregaRenglon(Distribucion distribucion) {
        renglones.add(distribucion);
        tabla.sort();
    }

    /**
     * Elimina un renglón de la tabla.
     * @param distribucion el renglón a eliminar.
     */
    public void eliminaRenglon(Distribucion distribucion) {
        renglones.remove(distribucion);
        tabla.sort();
    }

    /**
     * Selecciona renglones de la tabla.
     * @param distribuciones los renglones a seleccionar.
     */
    public void seleccionaRenglones(Lista<Distribucion> distribuciones) {
        modeloSeleccion.clearSelection();
        for (Distribucion distribucion : distribuciones)
            modeloSeleccion.select(distribucion);
    }

    /**
     * Regresa una lista con los registros seleccionados en la tabla.
     * @return una lista con los registros seleccionados en la tabla.
     */
    public Lista<Distribucion> getSeleccion() {
        Lista<Distribucion> seleccionados = new Lista<Distribucion>();
        for (TablePosition tp : seleccion) {
            int r = tp.getRow();
            seleccionados.agregaFinal(renglones.get(r));
        }
        return seleccionados;
    }

    /**
     * Regresa la distribucion seleccionada en la tabla.
     * @return la distribucion seleccionada en la tabla.
     */
    public Distribucion getRenglonSeleccionado() {
        int r = seleccion.get(0).getRow();
        return renglones.get(r);
    }

    /**
     * Agrega un escucha de selección.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscuchaSeleccion(EscuchaSeleccion escucha) {
        escuchas.agregaFinal(escucha);
    }

    /**
     * Fuerza un reordenamiento de la tabla.
     */
    public void reordena() {
        tabla.sort();
    }

    /**
     * Enfoca la tabla.
     */
    public void enfocaTabla() {
        tabla.requestFocus();
    }
}
