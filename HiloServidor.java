
package pruebachat;

import java.net.*;
import java.io.*;

public class HiloServidor implements Runnable {
    /**
     *  Socket del PC Servidor
     */
    ServerSocket socketServidor = null;

    /**
     *  Numero del puerto del PC Servidor
     */
    int numPuertoServidor;

    /**
     *  numPuertoServidor  Numero del puerto del PC Servidor
     */
    public HiloServidor(int numPuertoServidor) {
        this.numPuertoServidor = numPuertoServidor;
        try {
            socketServidor = new ServerSocket(numPuertoServidor);
            if (socketServidor != null) {
                System.out
                        .println("Servidor: ServerSocket creado correctamente");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while (true) {
            try {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Servidor: Socket aceptado");

                // Lanza una nueva ventana de chat
                VentanaChat nuevaVentana = new VentanaChat(
                        numPuertoServidor);
                nuevaVentana.conexion = new Conex(socketCliente);
                if (nuevaVentana.conexion.correcto) {
                    System.out
                            .println("Servidor: Conexion creada correctamente");
                    nuevaVentana.conecta(socketCliente.getInetAddress()
                            .toString(), socketCliente.getPort());
                } else {
                    System.out.println("Servidor: Error al crear la conexion");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
