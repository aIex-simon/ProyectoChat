/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebachat;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class VentanaChat implements ActionListener {
   
    Conex conexion = null;

    /**
     *  <code>threadLector</code>
     */
    Thread threadLector; 

    /**
     *  <code>dirIPLocal</code>
     */
    String dirIPLocal;

    /**
     *   Numero puerto Servidor
     */
    int numPuertoServidor;

    // Declaracion de componentes de la ventana principal
    /**
     *  Frame en donde se ubicarán los paneles
     */
    public Frame f;

    /**
     *   Componente de la ventana principal
     */
    public MenuBar barraMenu;

    /**
     *   Opcion dentro de un MenuBar
     */
    /**
     *   Opcion dentro de un MenuBar
     */
    public MenuItem opcionMenuConectar, opcionMenuDesconectar; 

    /**
     *  Opcion dentro de un MenuBar
     */
    /**
     *  Opcion dentro de un MenuBar
     */
    /**
     *  <Opcion dentro de un MenuBar
     */
    public MenuItem opcionMenuNuevaVentana, opcionMenuCerrarVentana,
            opcionMenuSalir;

    /**
     *  Menu para introducir comandos
     */
    public Menu menuComandos;

    public JTextArea areaDeTextoEnviado;

    public Label etiquetaTextoEnviado, etiquetaNuevaLinea, etiquetaEstado;

    /**
     *  Campo en donde se introduce el texto a enviar
     */
    public TextField nuevaLinea;

    /**
    
     * Paneles dentro de los cuales se agregaran Label,TextArea,
     */
    public Panel panel1, panel2, panel4;

    /// public Panel panel3;
    // Declaracion de componentes de la ventana de dialogo para nueva conexion
    /**
     *  <code>ventNuevaConex</code>
     */
    public Dialog ventNuevaConex = null;

    /**
     * Panel 1 que se agragará en un frame para 
     * crear la ventana de diálogo
     */
    public Panel pan1;

    /**
     *  Panel 2 que se agragará en un frame para 
     * crear la ventana de diálogo
     */
    public Panel pan2;

    /**
     *  Campo para introducir el IP al que se quiere conectar para establecer la comunicación
     * 
     */
    public TextField campodirIP;

    /**
     *  Campo para introducir el puerto en donde el servidor esta a la respuesta de las peticiones remotas
     */
    public TextField campoPuerto;

    /**
     *  Etiqueta que señala al campodirIP
     */
    public Label etiquetadirIP;

    /**
     * Etiqueta que señala al campoPuerto
     */
    public Label etiquetaPuerto;

    /**
     * Etiqueta que sirve para indicar el ultimo mensaje recibido
          */
    //public Label etiquetafecha;

    /**
     * Se asocia al actionlistener para crear una nueva ventana
     */
    public Button botonAceptar;

    /**
     *  Se asocia al actionlistener para crear una nueva ventana
     */
    public Button botonCancelar;
    private Object miChat;

    // Constructor
    /**
     * @param numPuertoServidor Puerto del servidor
     *  por el cual se conectarán los PCS remotos
     * 
     */
    
  
    public VentanaChat(int numPuertoServidor) {
        this.numPuertoServidor = numPuertoServidor;
        try {
            dirIPLocal = java.net.InetAddress.getLocalHost().toString();
        } catch (java.net.UnknownHostException e) {
            System.out.println(e);
        }

        // Creacion de componentes de la ventana principal
        f = new Frame("ProyecChat  v0.5    [IP local: " + dirIPLocal
                + " / ServerPort: " + numPuertoServidor + "]");
        f.setFont(new Font("Tahoma", Font.PLAIN, 14));
        f.setBackground(Color.lightGray);
        panel1 = new Panel();
  
        
        panel2 = new Panel();

        //     panel3 = new Panel();
        panel4 = new Panel();
        barraMenu = new MenuBar();
        opcionMenuNuevaVentana = new MenuItem("Nueva ventana");
        opcionMenuConectar = new MenuItem("Conectar");
        opcionMenuDesconectar = new MenuItem("Desconectar");
        opcionMenuCerrarVentana = new MenuItem("Cerrar ventana actual");
        opcionMenuSalir = new MenuItem("Salir [cierra TODAS las ventanas !]");
        menuComandos = new Menu("Comandos");

        etiquetaTextoEnviado = new Label("    Conversacion ");
        
         final ImageIcon miIcono = new ImageIcon ("D:\\Screenshot\\ImagenChat.jpg");
         areaDeTextoEnviado = new JTextArea(30, 30){
         Image image = miIcono.getImage();
         Image grayImage = GrayFilter.createDisabledImage(image);
         {
         setOpaque(false);
         }
         public void paint(Graphics g){
             g.drawImage(grayImage, 0,0, this );
             super.paint(g);
             
                 }
         };
         
        Toolkit IconoChat = Toolkit.getDefaultToolkit();
        Image miIcon =IconoChat.getImage("D:\\Screenshot\\LogoChat.png");
        f.setIconImage(miIcon);

       // etiquetafecha =new Label (" dfd ");
        etiquetaNuevaLinea = new Label("Escribe aqui..");
        etiquetaEstado = new Label("- DESCONECTADO -");
        etiquetaEstado.setForeground(Color.red);
        etiquetaEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
        nuevaLinea = new TextField(40);

        // Estructuracion de componentes de la ventana principal
        areaDeTextoEnviado.setEditable(false);

        opcionMenuDesconectar.setEnabled(false);
        f.setLayout(new BorderLayout());
        panel1.setLayout(new GridLayout(2, 1));
        panel2.setLayout(new BorderLayout());
        //     panel3.setLayout(new BorderLayout());
        panel2.add("North", etiquetaTextoEnviado);
        panel2.add("Center", areaDeTextoEnviado);
        //     panel3.add("North",etiquetafecha);
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.add(etiquetaNuevaLinea);
        panel4.add(nuevaLinea);
        panel1.add(panel2);
        //   panel1.add(panel3);

        menuComandos.add(opcionMenuNuevaVentana);
        menuComandos.add(opcionMenuCerrarVentana);
        menuComandos.addSeparator();
        menuComandos.add(opcionMenuConectar);
        menuComandos.add(opcionMenuDesconectar);
        menuComandos.addSeparator();
        menuComandos.add(opcionMenuSalir);
        barraMenu.add(menuComandos);
        f.add("North", etiquetaEstado);
        f.add("Center", panel2); /*
                                  * ojo si va panel1, este esta separado en 2, y
                                  * se verá solo la parte superior
                                  * correspondiente a panel2 que se añadió,
                                  * falta el panel3 que no se pudo concluir
                                  */

        f.add("South", panel4);
        f.setMenuBar(barraMenu);
        f.setBounds(200, 20, 500, 500);
        f.setResizable(false); //Se pone en false para evitar que la ventana sea cambiada de tamaño

        // Creacion de componentes de la ventana de dialogo
        pan1 = new Panel();
        pan2 = new Panel();
        campodirIP = new TextField(15);
        campoPuerto = new TextField("3000", 5);
        etiquetadirIP = new Label("Direccion IP");
        etiquetaPuerto = new Label("Numero de puerto");
        botonAceptar = new Button("Aceptar");
        botonCancelar = new Button("Cancelar");

        // Estructuracion de componentes de la ventana de dialogo
        ventNuevaConex = new Dialog(f, "Conectar con otro terminal", true);
        ventNuevaConex.setLayout(new BorderLayout());
        pan1.setLayout(new GridLayout(2, 2));
        pan1.add(etiquetadirIP);
        pan1.add(campodirIP);
        pan1.add(etiquetaPuerto);
        pan1.add(campoPuerto);
        pan2.add(botonAceptar);
        pan2.add(botonCancelar);
        ventNuevaConex.add("Center", pan1);
        ventNuevaConex.add("South", pan2);
        ventNuevaConex.setBounds(300, 300, 270, 100);
        ventNuevaConex.setResizable(false);

        // Observadores de los componentes para capturar los eventos
        opcionMenuNuevaVentana.addActionListener(this);
        opcionMenuConectar.addActionListener(this);
        opcionMenuDesconectar.addActionListener(this);
        opcionMenuCerrarVentana.addActionListener(this);
        opcionMenuSalir.addActionListener(this);
        nuevaLinea.addActionListener(this);
        botonAceptar.addActionListener(this);
        botonCancelar.addActionListener(this);

        // Muestra la ventana principal
        f.setVisible(true);
    }

    // Metodos del interfaz ActionListener
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent evento) {
        Object objetoOrigenDelEvento = evento.getSource();
        String cadena = evento.getActionCommand();

        // Nueva ventana
        if (objetoOrigenDelEvento == opcionMenuNuevaVentana) {
            new VentanaChat(numPuertoServidor);
        }

        // Conectar
        if (objetoOrigenDelEvento == opcionMenuConectar) {
            ventNuevaConex.setVisible(true);
        }

        // Desconectar
        if (objetoOrigenDelEvento == opcionMenuDesconectar) {
            conexion.enviaMensaje(new Mensj("Desconectar",
                    "*** Me desconecto. Adios. ***"));
            desconecta();
            areaDeTextoEnviado.setForeground(Color.blue);
            areaDeTextoEnviado.append(" *** Te has desconectado ***\n\n");
        }

        // Cerrar ventana
        if (objetoOrigenDelEvento == opcionMenuCerrarVentana) {
            f.setVisible(false);
            f = null;
            ventNuevaConex = null;
            //Thread.currentThread().suspend();
        }

        // Salir
        if (objetoOrigenDelEvento == opcionMenuSalir) {
            System.exit(0);
        }

        // Aceptar
        if (objetoOrigenDelEvento == botonAceptar) {
            int numPuertoRemoto = 0;
            String dirIPRemoto = campodirIP.getText();
            try {
                numPuertoRemoto = Integer.valueOf(campoPuerto.getText()).intValue();
                
                conexion = new Conex(dirIPRemoto, numPuertoRemoto);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            if ((conexion != null) & (conexion.correcto)) {
                ventNuevaConex.setVisible(false);
                conecta(dirIPRemoto, numPuertoRemoto);
                System.out
                        .println(" Conexion establecida correctamente");
            } else {
                System.out
                        .println("Conexion fallida. Intentalo de nuevo");
            }
        }

        // Cancelar
        if (objetoOrigenDelEvento == botonCancelar) {
            ventNuevaConex.setVisible(false);
        }

        // Nueva Linea de texto
        if (objetoOrigenDelEvento == nuevaLinea) {
            nuevaLinea.setText("");
            if (conexion != null) {
                areaDeTextoEnviado.setForeground(Color.blue);
                areaDeTextoEnviado.append("Server:  " + cadena + "\n");
                conexion.enviaMensaje(new Mensj("", cadena));
                // etiquetafecha=(date());
            }
        }
    }

    /**
     *  ipRemoto IP del PC remoto
     *  numPRemoto Numero de Puerto de Pc Remoto
     */
    public void conecta(String ipRemoto, int numPRemoto) {
        opcionMenuConectar.setEnabled(false);
        opcionMenuDesconectar.setEnabled(true);
        opcionMenuCerrarVentana.setEnabled(false);
        opcionMenuSalir.setEnabled(false);
        etiquetaEstado.setText("CONECTADO con IP " + ipRemoto + " [puerto "+ numPRemoto + "]");
        areaDeTextoEnviado.append(" *** Te has conectado ***\n");
        conexion.enviaMensaje(new Mensj("", "Conexion establecida"));

        // Lanza el thread para leer los datos de entrada
        threadLector = new Thread(new HiloLecturaStream(this));
        threadLector.start();
    }

    
    public void desconecta() {
        opcionMenuConectar.setEnabled(true);
        opcionMenuDesconectar.setEnabled(false);
        opcionMenuCerrarVentana.setEnabled(true);
        opcionMenuSalir.setEnabled(true);
        etiquetaEstado.setText("- DESCONECTADO -");

        // Para el thread lector de datos de entrada
        threadLector.stop(); //Este método está fuera de uso, pero luego de parar el hilo, se hace null para quitar la referancia, así la máquina podrá eliminar el hilo.
        threadLector = null;
        conexion = null;
    }
}