
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cuy extends Tamagotchi implements TamagotchiActions {

    public Cuy(String nombre) {
        super(nombre);
        ImageIcon imagen = buscarIcono("Cuy.png");
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
