package vue;

import javax.swing.JFrame;

/**
 *
 * @author fenit
 */
public class FenetreJeu extends JFrame {

    public FenetreJeu() {
        this.setTitle("Fing the path");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        PanneauJeu gamePanel = new PanneauJeu();
        this.add(gamePanel);

        this.pack();// ajuste la taille de la fenetre 
        this.setLocationRelativeTo(null);//centre de la fi=enetre
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new FenetreJeu();S
    }
}
