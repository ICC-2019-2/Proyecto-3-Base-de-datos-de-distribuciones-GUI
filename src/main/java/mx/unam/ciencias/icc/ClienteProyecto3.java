package mx.unam.ciencias.icc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.fx.ControladorInterfazDistribucion;
import mx.unam.ciencias.icc.fx.ControladorTablaDistribucion;

/**
 * ClienteProyecto3: Parte del cliente para el Proyecto 3.
 */
public class ClienteProyecto3 extends Application {

    /* Vista de la interfaz estudiantes. */
    private static final String INTERFAZ_DISTRIBUCION_FXML =
        "fxml/interfaz-distribucion.fxml";
    /* Vista de la tabla de estudiantes. */
    private static final String TABLA_DISTRIBUCION_FXML =
        "fxml/tabla-distribucion.fxml";
    /* Ícono de la Facultad de Ciencias. */
    private static final String ICONO_CIENCIAS =
        "icons/ciencias.png";

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal. Literalmente así está definido.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        String url = cl.getResource(ICONO_CIENCIAS).toString();
        escenario.getIcons().add(new Image(url));
        escenario.setTitle("Administrador de Distribuciones");

        FXMLLoader cargador =
            new FXMLLoader(cl.getResource(TABLA_DISTRIBUCION_FXML));
        GridPane tablaDistribucion = (GridPane)cargador.load();
        ControladorTablaDistribucion controladorTablaDistribucion =
            cargador.getController();

        cargador = new FXMLLoader(cl.getResource(INTERFAZ_DISTRIBUCION_FXML));
        BorderPane interfazDistribucion = (BorderPane)cargador.load();
        interfazDistribucion.setCenter(tablaDistribucion);
        ControladorInterfazDistribucion controladorInterfazDistribucion =
            cargador.getController();
        controladorInterfazDistribucion.setEscenario(escenario);
        controladorInterfazDistribucion.setControladorTablaDistribucion(
            controladorTablaDistribucion);

        Scene escena = new Scene(interfazDistribucion);
        escenario.setScene(escena);
        escenario.setOnCloseRequest(e -> controladorInterfazDistribucion.salir(null));
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
