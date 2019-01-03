
package pruebachat;


import java.awt.*;
import java.util.*;

// HiloLecturaStream.java

public class HiloLecturaStream implements Runnable {

    VentanaChat padre;

    private Date fecha;

    /**
     * 
     *    Objeto para el cual se creó la ventana
     */
    public HiloLecturaStream(VentanaChat padre) {
        this.padre = padre;
        System.out.println("HiloLecturaStream creado");
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while (true) {
            Mensj mensajeRecibido = padre.conexion.recibeMensaje();
            if (mensajeRecibido != null) {
                if (mensajeRecibido.comando.equals("Desconectar")) {
                    padre.areaDeTextoEnviado
                            .append("*** El remoto ha desconectado ***\n\n");
                    padre.desconecta();
                } else {
                    padre.areaDeTextoEnviado.setForeground(Color.red);
                    padre.areaDeTextoEnviado.append("Remoto: "
                            + mensajeRecibido.texto + "\n");
                    // fecha= new Date();
                    //   padre.etiquetafecha = new Label (fecha.toString());
                }
            }
        }
    }
}
