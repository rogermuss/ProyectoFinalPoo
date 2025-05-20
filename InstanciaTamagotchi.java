
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private boolean vivo = true;
    private String razonDeMuerte;


    private Tamagotchi tamagotchi;

    public InstanciaTamagotchi(String nombre, int opcionTamagotchi)  {
        setTitle("Tamagotchi - Version 1.0");
        setSize(XSIZE, YSIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0xEA895F));
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


        //Nombre TAMAGOTCHI
        JLabel labelTamagotchi = new JLabel(nombre);
        labelTamagotchi.setFont(new Font("Noto Sans", Font.BOLD, 32));
        labelTamagotchi.setForeground(Color.white);
        labelTamagotchi.setHorizontalAlignment(SwingConstants.CENTER);


        add(panelAcciones,BorderLayout.SOUTH);
        add(labelTamagotchi,BorderLayout.NORTH);
        add(tamagotchi.getSprite(), BorderLayout.CENTER);
        add(nivelesDeNecesidad, BorderLayout.WEST);

        reducirBarraDeHambre();
        reducirBarraDeEnergia();
        reducirBarraDeFelicidad();
        comprobacionEstadoTamagotchi();

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
                        Thread.sleep(15000); // Espera 10 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tamagotchi.setFelicidad(tamagotchi.getFelicidad() - 2);

                    // Actualiza la barra en el hilo de la interfaz
                    SwingUtilities.invokeLater(() -> {
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
                    Thread.sleep(1000);
                    System.out.println(vivo);
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




    public static void main(String[] args) {
        new InstanciaTamagotchi("Dashita", 0);
    }
    
}
