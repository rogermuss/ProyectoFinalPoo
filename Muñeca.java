import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Muñeca extends Tamagotchi implements TamagotchiActions{

    public Muñeca(String nombre) {
        super(nombre);
        ImageIcon imagen1 = buscarIcono("Muñeca.png");
        Image img1 = imagen1.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada1 = new ImageIcon(img1);
        sprite = new JLabel(imagenRedimensionada1);

        /* 
        ImageIcon imagen2 = buscarIcono(".png");
        Image img2 = imagen2.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada2 = new ImageIcon(img2);
        fondoPersonalizado = new JLabel(imagenRedimensionada2);
        */


    }

    @Override
    public void alimentar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alimentar'");
    }

    @Override
    public void jugar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jugar'");
    }

    @Override
    public void dormir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dormir'");
    }

    @Override
    public void mostrarEstadoDeAnimo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarEstadoDeAnimo'");
    }

    @Override
    public void verEstadoGeneral() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verEstadoGeneral'");
    }
    
}
