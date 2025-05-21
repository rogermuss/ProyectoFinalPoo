import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Muñeca extends Tamagotchi implements TamagotchiActions{

    public Muñeca(String nombre) {
        super(nombre);
        ImageIcon imagen1 = buscarIcono("Sources/Muñeca.png");
        Image img1 = imagen1.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada1 = new ImageIcon(img1);
        spriteNormal = new JLabel(imagenRedimensionada1);

        ImageIcon imagen2 = buscarIcono("Sources/FondoMuñeca.png");
        Image img2 = imagen2.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada2 = new ImageIcon(img2);
        fondoPersonalizado = new JLabel(imagenRedimensionada2);


        ImageIcon imagen3 = buscarIcono("Sources/MuñecaDormida.png");
        Image img3 = imagen3.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada3 = new ImageIcon(img3);
        spriteDormido = new JLabel(imagenRedimensionada3);
    }

    @Override
    public String alimentar() {
        return "Delicioso  o(≧▽≦)o";
    }

    @Override
    public String jugar() {
        return "⸜(｡˃ ᵕ ˂ )⸝♡";
                        
    }

    @Override
    public String dormir() {
            return "zZzZz";

        }

    public String mostrarEstadoDeAnimo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarEstadoDeAnimo'");
    }

    
    
}
