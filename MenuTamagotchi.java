
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuTamagotchi extends JFrame {
    public final int XSIZE = 854;
    public final int YSIZE = 480;
    private JButton opcionCrearTamagotchi = new JButton("Crear Tamagotchi");
    private JButton opcionSalir = new JButton("Salir");
    private JButton opcionMostrarInstrucciones = new JButton("Instrucciones");
    private JPanel panelInferior = new JPanel();
    private String nombreTamagotchi;


    public MenuTamagotchi() {
        // Agregar las opciones al menú
        setTitle("Tamagotchi - Version 1.0");
        setSize(XSIZE, YSIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0xEA899A));
        setResizable(false);
        setLocationRelativeTo(null);



        //Se busca y coloca la imagen como logo del menu
        ImageIcon imagenCentral = buscarImagenMenu("LogoTamagotchi.png");
        Image img = imagenCentral.getImage().getScaledInstance(700, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(img);
        JLabel etiquetaImagen = new JLabel(imagenRedimensionada);

        ImageIcon imagenAnimales = buscarImagenMenu("ImagenCuadrilla.png");
        Image img2 = imagenAnimales.getImage().getScaledInstance(450, 270, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada2 = new ImageIcon(img2);
        JLabel etiquetaImagen2 = new JLabel(imagenRedimensionada2);

        opcionCrearTamagotchi.setBackground(new Color(0xFF0080)); 
        opcionCrearTamagotchi.setForeground(Color.WHITE); // Texto blanco
        opcionCrearTamagotchi.setFocusPainted(false); 


        opcionMostrarInstrucciones.setBackground(new Color(0xE39C93)); 
        opcionMostrarInstrucciones.setForeground(Color.WHITE); // Texto blanco
        opcionMostrarInstrucciones.setFocusPainted(false); // Quita el borde de enfoque

        // Botón Salir - Rojo
        opcionSalir.setBackground(new Color(0xFEC1A5)); 
        opcionSalir.setForeground(Color.WHITE);
        opcionSalir.setFocusPainted(false);

        opcionMostrarInstrucciones.setOpaque(true);
        opcionCrearTamagotchi.setOpaque(true);
        opcionSalir.setOpaque(true);

        //Se crea un panel para colocar las opciones del menu para comenzar el juego
        panelInferior.setBackground(new Color(0xFFF5E6));
        panelInferior.add(opcionCrearTamagotchi);
        panelInferior.add(opcionMostrarInstrucciones);
        panelInferior.add(opcionSalir);


        opcionCrearTamagotchi();
        opcionSalir();
        opcionMostrarInstrucciones();

        add(panelInferior, BorderLayout.SOUTH);
        add(etiquetaImagen, BorderLayout.NORTH);
        add(etiquetaImagen2, BorderLayout.CENTER);

        setVisible(true);
    }

    

    //Busca la imagen de el menu en la carpeta
    public ImageIcon buscarImagenMenu(String ruta){
        String rutaImagen = ruta;
        java.net.URL imgURL = getClass().getResource(rutaImagen);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se encontró la imagen: " + rutaImagen);
            return new ImageIcon(); // Retorna un icono vacío en caso de error
        }
    }

    public void opcionCrearTamagotchi() {
        opcionCrearTamagotchi.addActionListener(e -> {
        String[] opciones = {"Muñeca", "Perro", "Cuy", "Gato", "Cancelar"};

        // Crear el diálogo personalizado
        JDialog dialogo = new JDialog((Frame) null, "Seleccion Tamagotchis", true);
        dialogo.setLayout(new BorderLayout());

        // Icono personalizado
        ImageIcon iconoOptionPane = buscarImagenMenu("IconoSeleccionTamagotchi.png");
        Image img = iconoOptionPane.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon icono = new ImageIcon(img);

        // Mensaje
        JLabel mensaje = new JLabel("Elige tu Tamagotchi!!", icono, JLabel.LEFT);
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
            boton.addActionListener(a -> {
                dialogo.dispose(); // cerrar el diálogo
                if (opcion != 4) { // si no es "Cancelar"
                    JDialog ingresarNombre = new JDialog((Frame) null, "Ingresar Nombre", true);
                    ingresarNombre.setBackground(new Color(0xB2DAFA));
                    ingresarNombre.setLayout(new BorderLayout());

                    JButton aceptar = new JButton("Aceptar");
                    aceptar.setBackground(Color.BLACK);
                    aceptar.setFocusable(false);
                    aceptar.setForeground(Color.WHITE);
                    

                    //Seleccion de nombre para el tamagotchi en una textbox
                    JTextField textField = new JTextField("Ingresa el nombre de tu Tamagotchi");
                    textField.setFont(new Font("Noto Sans", Font.BOLD, 24));
                    textField.setBackground(new Color(0x80CEE1));
                    textField.setForeground(Color.black);

                    aceptar.addActionListener(ac->{
                        if(ac.getSource() == aceptar){
                            if(!(textField.getText().equals("") || textField.getText()==null || 
                            textField.getText().equals("Ingresa el nombre de tu Tamagotchi"))){
                                nombreTamagotchi = textField.getText();
                                ingresarNombre.dispose();
                                setVisible(false);
                                new InstanciaTamagotchi(nombreTamagotchi, opcion);
                            }
                            else{
                                JOptionPane.showMessageDialog(this, "No se le coloco un nombre apropiado al Tamagotchi");
                            }

                        }
                    
                    });

                    ingresarNombre.add(aceptar,BorderLayout.WEST);
                    ingresarNombre.add(textField,BorderLayout.CENTER);
                    
                    ingresarNombre.pack();
                    ingresarNombre.setLocationRelativeTo(null); // centrar en pantalla
                    ingresarNombre.setVisible(true);

                }
            });
            panelBotones.add(boton);
        }

        dialogo.add(panelBotones, BorderLayout.CENTER);
        dialogo.pack();
        dialogo.setLocationRelativeTo(null); // centrar en pantalla
        dialogo.setVisible(true);
    });
}


    public void opcionMostrarInstrucciones(){
        opcionMostrarInstrucciones.addActionListener(e->{
            // Mostrar instrucciones en un cuadro de texto con scroll
        JPanel panelCreditos = new JPanel(new BorderLayout(10, 10));
        panelCreditos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Instrucciones de Cuidado", SwingConstants.CENTER);
        titulo.setFont(new Font("Noto Sans", Font.BOLD, 24));
        titulo.setForeground(new Color(206, 32, 116));
        panelCreditos.add(titulo, BorderLayout.NORTH);

        JTextArea contenido = new JTextArea(
                "Pasos para el cuidado de tu Tamagotchi:\n" +
                        "\t• Constante Alimentacion:\n" +
                        "\tEs necesario alimentar de forma constante a tu Tamagotchi\n\tpara que no muera de inanicion.\n\n" +
                        "\t• Juega con él:\n" +
                        "\tJugar con tu Tamagotchi le indicara lo mucho que lo quieres.\n\tHaz feliz a tu Tamagotchi!!.\n\n" +
                        "\t• :\n" +
                        "\t© Todos los derechos reservados"
        );
        contenido.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        contenido.setEditable(false);
        contenido.setBackground(new Color(240, 240, 240));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCreditos.add(new JScrollPane(contenido), BorderLayout.CENTER);

        Font fuenteBotones = new Font("Noto Sans", Font.BOLD, 14);
        Color colorBoton = new Color(220, 220, 220);

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(o -> ((Window) SwingUtilities.getWindowAncestor((Component) o.getSource())).dispose());
        cerrar.setFont(fuenteBotones);
        cerrar.setBackground(colorBoton);
        cerrar.setFocusPainted(false);

        JPanel panelBoton = new JPanel();
        panelBoton.add(cerrar);
        panelCreditos.add(panelBoton, BorderLayout.SOUTH);

        JDialog instrucciones = new JDialog();
        instrucciones.setTitle("Instrucciones de cuidado.");
        instrucciones.setModal(true);
        instrucciones.setResizable(true);
        instrucciones.setContentPane(panelCreditos);
        instrucciones.pack();
        instrucciones.setLocationRelativeTo(null);
        instrucciones.setVisible(true);
            
        });
    }
    public void opcionSalir(){
        opcionSalir.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        new MenuTamagotchi();
    }
}
