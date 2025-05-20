import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Tamagotchi{
    protected  String nombre;
    protected int hambre;
    protected int felicidad;
    protected int energia;
    protected JLabel sprite;
    protected JLabel fondoPersonalizado;

    public Tamagotchi(String nombre) {
        this.nombre = nombre;
        hambre = 100;
        felicidad = 100;
        energia = 100;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getEnergia() {
        return energia;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public int getHambre() {
        return hambre;
    }

    public String getNombre() {
        return nombre;
    }

    public JLabel getSprite() {
        return sprite;
    }


    public abstract void mostrarEstadoDeAnimo();

    
    public abstract void verEstadoGeneral();



    public ImageIcon buscarIcono(String ruta){
        String rutaImagen = ruta;
        java.net.URL imgURL = getClass().getResource(rutaImagen);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se encontró la imagen: " + rutaImagen);
            return new ImageIcon(); // Retorna un icono vacío en caso de error
        }
    }


}
