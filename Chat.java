
package pruebachat;
     
public class Chat {
    // Punto de entrada al programa
    public static void main(String args[]) {
        Thread threadServidor;
        int numPuertoServidor = 134;
            // Crea una nueva ventana para hacer chat
        
            new VentanaChat(numPuertoServidor);
           
            // Lanza el thread del servidor que atendera las llamadas entrantes
            
            threadServidor = new Thread(new HiloServidor(
                    numPuertoServidor));
            threadServidor.start();
        
    }
}
