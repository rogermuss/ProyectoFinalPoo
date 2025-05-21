
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class InstanciaTamagotchi extends JFrame{
    public final int XSIZE = 854;
    public final int YSIZE = 480;
    public final int MUÑECA = 0;
    public final int PERRO = 1;
    public final int CUY = 2;
    public final int GATO = 3;

    private JProgressBar barraAlimentacion = new JProgressBar();
    private JProgressBar barraFelicidad = new JProgressBar();
    private JProgressBar barraEnergia = new JProgressBar();
    private JButton opcionAlimentar = new JButton("Alimentar");
    private JButton opcionJugar = new JButton("Jugar");
    private JButton opcionDormir = new JButton("Dormir");
    private JLabel labelNombreTamagotchi;
    private JLabel labelTamagotchi;
    private boolean vivo = true;
    private String razonDeMuerte;
    private Thread hiloComida;
    private Thread hiloDormir;
    private Thread hiloClick;
    private JLabel alimento = new JLabel();
    private JLayeredPane layeredPane;



    private Tamagotchi tamagotchi;

    public InstanciaTamagotchi(String nombre, int opcionTamagotchi)  {
        setTitle("Tamagotchi - Version 1.0");
        setSize(XSIZE, YSIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0x83C0DF));
        setResizable(false);
        setLocationRelativeTo(null);

        switch (opcionTamagotchi) {
            case 0 -> {
                tamagotchi = new Muñeca(nombre);
            }
            case 1 -> {
                tamagotchi = new Perro(nombre);
            }
            case 2 -> {
                tamagotchi = new Cuy(nombre);
            }
            case 3 -> {
                tamagotchi = new Gato(nombre);
            }
        }
        //Barras de necesidades Tamagotchis
        JPanel nivelesDeNecesidad = new JPanel();
        nivelesDeNecesidad.setBackground(new Color(0xF3FFE3));
        nivelesDeNecesidad.setLayout(new GridLayout(3,1,10,10));

        barraAlimentacion.setString("Hambre "+tamagotchi.getHambre());
        barraAlimentacion.setStringPainted(true);
        barraAlimentacion.setFont(new Font("MV Boli", Font.BOLD, 15));
        barraAlimentacion.setForeground(new Color(0xA18262));

        barraEnergia.setString("Energia "+ tamagotchi.getEnergia());
        barraEnergia.setStringPainted(true);
        barraEnergia.setFont(new Font("MV Boli", Font.BOLD, 15));
        barraEnergia.setForeground(new Color(0x3b83bd));



        barraFelicidad.setString("Felicidad "+tamagotchi.getFelicidad());
        barraFelicidad.setStringPainted(true);
        barraFelicidad.setFont(new Font("MV Boli", Font.BOLD, 15));
        barraFelicidad.setForeground(new Color(0xE5BE01));


        nivelesDeNecesidad.add(barraAlimentacion);
        nivelesDeNecesidad.add(barraFelicidad);
        nivelesDeNecesidad.add(barraEnergia);

        //Contenedor de botones de accion.
        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(new Color(0xD3AB9E));

        //Implementacion Botones de accion
        opcionAlimentar.setBackground(new Color(0xFEC1A5)); 
        opcionAlimentar.setForeground(Color.WHITE); // Texto blanco
        opcionAlimentar.setFocusPainted(false); 


        opcionDormir.setBackground(new Color(0xFEC1A5)); 
        opcionDormir.setForeground(Color.WHITE); // Texto blanco
        opcionDormir.setFocusPainted(false); // Quita el borde de enfoque

        // Botón Salir - Rojo
        opcionJugar.setBackground(new Color(0xFEC1A5)); 
        opcionJugar.setForeground(Color.WHITE);
        opcionJugar.setFocusPainted(false);

        opcionDormir.setOpaque(true);
        opcionAlimentar.setOpaque(true);
        opcionJugar.setOpaque(true);
        
        panelAcciones.add(opcionAlimentar);
        panelAcciones.add(opcionJugar);
        panelAcciones.add(opcionDormir);
        //Implementar Fondo personalizado para cada tamagotchi

        //Panel estado de animo y nombre
        JPanel panelNorte = new JPanel(new FlowLayout());
        panelNorte.setBackground(new Color(0x83C0DF));

        //Nombre TAMAGOTCHI
        labelNombreTamagotchi = new JLabel(nombre);
        labelNombreTamagotchi.setFont(new Font("Noto Sans", Font.BOLD, 32));
        labelNombreTamagotchi.setForeground(Color.white);
        labelNombreTamagotchi.setHorizontalAlignment(SwingConstants.CENTER);


        //Boton Estado de animo
        JButton opcionMostrarEstado = new JButton("Estado de Animo");
        opcionMostrarEstado.setBackground(new Color(0xb988b8)); 
        opcionMostrarEstado.setForeground(Color.WHITE);
        opcionMostrarEstado.setFocusPainted(false);
        opcionMostrarEstado.setOpaque(true);

        panelNorte.add(labelNombreTamagotchi);
        panelNorte.add(opcionMostrarEstado);

        // Crear un JLayeredPane para manejar capas
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(XSIZE, YSIZE));

        // Configurar el fondo para que ocupe todo el espacio
        JLabel fondoPersonalizado = tamagotchi.getFondoPersonalizado();
        fondoPersonalizado.setBounds(0, 0, XSIZE-150, YSIZE-100);
        layeredPane.add(fondoPersonalizado, Integer.valueOf(0)); // Capa más baja (fondo)

        // Configurar el sprite del tamagotchi
        labelTamagotchi = tamagotchi.getSpriteNormal();
        labelTamagotchi.setBounds(100, 100, 
                         labelTamagotchi.getPreferredSize().width, 
                         labelTamagotchi.getPreferredSize().height);
        layeredPane.add(labelTamagotchi, Integer.valueOf(1)); // Capa sobre el fondo

        // Añadir el layeredPane al centro del frame
        add(layeredPane, BorderLayout.CENTER);

        labelTamagotchi = tamagotchi.getSpriteNormal();

        add(panelAcciones,BorderLayout.SOUTH);
        add(nivelesDeNecesidad, BorderLayout.WEST);
        add(panelNorte,BorderLayout.NORTH);

        reducirBarraDeHambre();
        reducirBarraDeEnergia();
        reducirBarraDeFelicidad();
        comprobacionEstadoTamagotchi();
        opcionAlimentar();
        opcionDormir();
        opcionMostrarEstado();
        eventoClickTamagotchi();

        setVisible(true);



    }



    public void reducirBarraDeHambre() {
        Thread hiloHambre = new Thread(() -> {
            while (vivo) {
                if (tamagotchi.getHambre() > 0) {
                    try {
                        Thread.sleep(9000); // Espera 10 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tamagotchi.setHambre(tamagotchi.getHambre() - 5);

                    // Actualiza la barra en el hilo de la interfaz
                    SwingUtilities.invokeLater(() -> {
                        barraAlimentacion.setValue(tamagotchi.getHambre());
                        barraAlimentacion.setString("Hambre "+tamagotchi.getHambre());
                    });
                }
                else{
                    vivo = false;
                    razonDeMuerte = "MURIO DE INANICION";
                }
            }
        });

        hiloHambre.start(); // Inicia el hilo
    }

    public void reducirBarraDeFelicidad(){
            Thread hiloFelicidad = new Thread(() -> {
            while (vivo) {
                if (tamagotchi.getFelicidad() > 0) {
                    try {
                        Thread.sleep(12000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tamagotchi.setFelicidad(tamagotchi.getFelicidad() - 2);

                    // Actualiza la barra en el hilo de la interfaz
                    SwingUtilities.invokeLater(() -> {
                        barraFelicidad.setValue(tamagotchi.getFelicidad());
                        barraFelicidad.setString("Felicidad "+tamagotchi.getFelicidad());
                    });
                }
                else{
                    vivo = false;
                    razonDeMuerte = "MURIO DE TRISTEZA";
                }
            }
        });

        hiloFelicidad.start(); // Inicia el hilo
    }

    public void reducirBarraDeEnergia(){
        Thread hiloEnergia = new Thread(() -> {
            while (vivo) {
                if (tamagotchi.getEnergia() > 0) {
                    try {
                        Thread.sleep(10000); // Espera 10 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tamagotchi.setEnergia(tamagotchi.getEnergia() - 2);

                    // Actualiza la barra en el hilo de la interfaz
                    SwingUtilities.invokeLater(() -> {
                        barraEnergia.setValue(tamagotchi.getEnergia());
                        barraEnergia.setString("Energia "+ tamagotchi.getEnergia());
                
                    });
                }
                else{
                    vivo = false;
                    razonDeMuerte = "MURIO DE FATIGA";
                }
            }
        });

        hiloEnergia.start(); // Inicia el hilo
    }


    public void comprobacionEstadoTamagotchi(){
        Thread hiloComprobacionEstado = new Thread(()-> {
            while(vivo){
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
                 // Cuando vivo es false, mostrar mensaje en la interfaz
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Tu Tamagotchi ha muerto :(\n" + razonDeMuerte);
                    this.dispose(); // Cierra la ventana actual
                    new MenuTamagotchi(); 
                });
        });
        hiloComprobacionEstado.start();
    }


    public void opcionAlimentar(){
    opcionAlimentar.addActionListener(e -> {
        if(opcionDormir.isEnabled()){
        String[] opciones = {"Flan", "Pastel de Fresa", "Pay de Limon", "Ramen", "Rollo de Fresa", "Pollo Horneado"};

        // Crear el diálogo personalizado
        JDialog dialogo = new JDialog((Frame) null, "Seleccion Alimentos", true);
        dialogo.setLayout(new BorderLayout());

        // Mensaje
        JLabel mensaje = new JLabel("Elige el alimento para tu mascota: ", JLabel.LEFT);
        mensaje.setIconTextGap(30);
        mensaje.setFont(new Font("Brush Script MT", Font.PLAIN, 40));
        mensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialogo.add(mensaje, BorderLayout.NORTH);
        dialogo.getContentPane().setBackground(new Color(0xFFF5E6)); // color claro pastel, por ejemplo;

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, opciones.length, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 20));
        panelBotones.setBackground(new Color(0xFFF5E6));

        for (int i = 0; i < opciones.length; i++) {
            JButton boton = new JButton(opciones[i]);
            boton.setBackground(new Color(0xFF6961));
            boton.setFocusPainted(false);
            boton.setForeground(Color.WHITE);
            int opcion = i; // capturar índice
            boton.addActionListener(o -> {
                dialogo.dispose();
                alimento.setText(tamagotchi.alimentar());
                alimento.setFont(new Font("Monospaced", Font.BOLD, 20));
                alimento.setVerticalTextPosition(JLabel.NORTH);
                alimento.setHorizontalTextPosition(JLabel.CENTER);

                // Luego intentamos establecer el ícono de comida
                ImageIcon iconoComida = tamagotchi.buscarIcono("Sources/Comida" + (opcion + 1) + ".gif");
                
                if (iconoComida.getIconWidth() > 0) {  // Verificamos que el ícono se cargó correctamente
                    alimento.setIcon(iconoComida);
                } else {
                    System.err.println("No se pudo cargar el ícono: Comida" + (opcion + 1) + ".gif");
                }
                
                //URGENTEEEEEEEEEEEEEEE
                /*Hace falta agregar como afecta cada alimento a cada tamagochi
                 *en cuanto a energia, hambre y felicidad*/
                
                // Actualizar la barra de alimentación
                tamagotchi.setHambre(tamagotchi.getHambre() + 10); // Aumentar el hambre (ajusta según necesites)
                if (tamagotchi.getHambre() > 100) {
                    tamagotchi.setHambre(100); // Limitar a 100
                }
                SwingUtilities.invokeLater(() -> {
                    barraAlimentacion.setValue(tamagotchi.getHambre());
                    barraAlimentacion.setString("Hambre " + tamagotchi.getHambre());
                });
                
                // Posicionar y añadir el alimento al layeredPane
                alimento.setBounds(450, 110, 
                    alimento.getPreferredSize().width, 
                    alimento.getPreferredSize().height);
                  
                layeredPane.add(alimento, Integer.valueOf(2)); // Capa superior a tamagotchi
                revalidate();
                repaint();

                hiloComida = new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                        // Remover el alimento en el hilo de Swing
                        SwingUtilities.invokeLater(() -> {
                        layeredPane.remove(alimento);
                        revalidate();
                        repaint();
                    });
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
                hiloComida.start(); // Iniciar el hilo
            });
            panelBotones.add(boton);
        }
        
        dialogo.add(panelBotones, BorderLayout.CENTER);
        dialogo.pack();
        dialogo.setLocationRelativeTo(null); // centrar en pantalla
        dialogo.setVisible(true);
        }
    });
}

    public void opcionDormir(){
        //Agregar la imagen de la mascota ahora durmiendo con letras de zzz en su cabeza, la accion durara 15 segundos
        opcionDormir.addActionListener(d ->{
            opcionDormir.setEnabled(false);

            layeredPane.remove(labelTamagotchi);
            labelTamagotchi = tamagotchi.getSpriteDormido();
            labelTamagotchi.setBounds(110, 75, 
                         labelTamagotchi.getPreferredSize().width, 
                         labelTamagotchi.getPreferredSize().height);
            layeredPane.add(labelTamagotchi, Integer.valueOf(1));
            revalidate();
            repaint();

            hiloDormir = new Thread(()->{
                for (int i = 0; i < 4; i++) {
                    try {
                        Thread.sleep(3750);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tamagotchi.setEnergia(tamagotchi.getEnergia()+10);
                    if(tamagotchi.getEnergia() > 100){
                        tamagotchi.setEnergia(100);
                    }
                    SwingUtilities.invokeLater(() -> {
                        barraEnergia.setValue(tamagotchi.getEnergia());
                        barraEnergia.setString("Energia " + tamagotchi.getEnergia());
                    });

                }
                opcionDormir.setEnabled(true);
                layeredPane.remove(labelTamagotchi);
                labelTamagotchi = tamagotchi.getSpriteNormal();
                labelTamagotchi.setBounds(100, 100, 
                         labelTamagotchi.getPreferredSize().width, 
                         labelTamagotchi.getPreferredSize().height);
                layeredPane.add(labelTamagotchi, Integer.valueOf(1));
                revalidate();
                repaint();
            });
            hiloDormir.start();

            
        });

    }

    public void opcionJugar(){
        //Dicha opcion implementara jugar con un gif de una pelota, un gif de 
        //tomar te y un gif extra para la implementacion de dicha accion

        //Tomar te dara mas a la muñeca, la pelota dara mas al gato y al perro y la ultima accion sera destinada al cuy.
        //A su vez implementar Strings a cada mascota.

        //Se implementaran las acciones unicas al jugar con la mascota, tambien se pueden implementar cada cierto tiempo 
        //dependiendo del estado de la mascota, pero es compicado
    }

    public void opcionMostrarEstado(){
        //Pendiente de revisar, se implementara dependiendo del nivel de felicidad, energia y hambre.
        //Sera un cuadro mas de opcion abrible y cerrable, sera una ventana emergente que te indique el estado del tamagotchi.
    }

    public void eventoClickTamagotchi(){
        labelTamagotchi.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Ejemplo: aumentar la felicidad al hacer clic
                tamagotchi.setFelicidad(tamagotchi.getFelicidad() + 1);
                if (tamagotchi.getFelicidad() > 100) {
                tamagotchi.setFelicidad(100);
                }

                ImageIcon gifEstrella = tamagotchi.buscarIcono("Sources/EventoClick.gif");
                JLabel labelEstrella = new JLabel(gifEstrella);
                labelEstrella.setBounds(450, 110, 
                    labelEstrella.getPreferredSize().width, 
                    labelEstrella.getPreferredSize().height);

                // Actualizar la barra de felicidad
                SwingUtilities.invokeLater(() -> {
                    layeredPane.add(labelEstrella,Integer.valueOf(3));
                    barraFelicidad.setValue(tamagotchi.getFelicidad());
                    barraFelicidad.setString("Felicidad " + tamagotchi.getFelicidad());
                    revalidate();
                    repaint();
                 });



                

                 hiloClick = new Thread(()->{
                    try {
                        Thread.sleep(2000);
                        SwingUtilities.invokeLater(() -> {
                        layeredPane.remove(labelEstrella);
                        revalidate();
                        repaint();
                        });
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                 });
                 hiloClick.start();
        
            }
        });
    }


    public static void main(String[] args) {
        new InstanciaTamagotchi("Dashita", 0);
    }
    
}
