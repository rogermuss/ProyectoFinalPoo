import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Perro extends Tamagotchi implements TamagotchiActions{
    private String rutaIcono;
    public Perro(String nombre) {
        super(nombre);
        ImageIcon imagen = buscarIcono("Perro.png");
        Image img = imagen.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(img);
        sprite = new JLabel(imagenRedimensionada);

        /* 
        ImageIcon imagen2 = buscarIcono(".png");
        Image img2 = imagen2.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada2 = new ImageIcon(img2);
        fondoPersonalizado = new JLabel(imagenRedimensionada2);
        */
    }
    @Override
    public void mostrarEstadoDeAnimo() {
        
    }


    @Override
    public void verEstadoGeneral() {
    }
    @Override
    public void alimentar() {
    }
    @Override
    public void jugar() {
        
    }
    @Override
    public void dormir() {
        
    }
    
    
}
