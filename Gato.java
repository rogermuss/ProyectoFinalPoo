import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Gato extends Tamagotchi implements TamagotchiActions{

    public Gato(String nombre) {
        super(nombre);
        ImageIcon imagen = buscarIcono("Sources/Gato.png");
        Image img = imagen.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(img);
        spriteNormal = new JLabel(imagenRedimensionada);

        /* 
        ImageIcon imagen2 = buscarIcono(".png");
        Image img2 = imagen2.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada2 = new ImageIcon(img2);
        fondoPersonalizado = new JLabel(imagenRedimensionada2);
        */

        ImageIcon imagen3 = buscarIcono("Sources/GatoDormido.jpeg");
        Image img3 = imagen3.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada3 = new ImageIcon(img3);
        spriteDormido = new JLabel(imagenRedimensionada3);
    }

    @Override
    public String alimentar() {
        return "(=^-ω-^=) ";
        
    }

    @Override
    public String jugar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jugar'");
    }

    @Override
    public String dormir() {
        return "zZzZz";

    }

    @Override
    public String mostrarEstadoDeAnimo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarEstadoDeAnimo'");
    }

    
    
}
