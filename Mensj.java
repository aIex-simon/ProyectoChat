
package pruebachat;


public class Mensj implements java.io.Serializable {

    String comando;
    String texto;

    boolean correcto;

    /**
     * comando Comando asociaso al texto del Mensaje de Chat
     * texto Información del mensaje
     */
    public Mensj(String comando, String texto) {
        this.comando = comando;
        this.texto = texto;
    }
}
