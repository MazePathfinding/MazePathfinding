package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.MoteurJeu;

/**
 *
 * @author fenit
 */
public class FenetreJeu extends JFrame {

    private PanneauJeu panneauJeu;

    // Informations sur l'algorithme
    private JLabel labelAlgorithme;
    private JLabel labelNoeuds;
    private JLabel labelLongueur;
    private JLabel labelCout;
    private JLabel labelTemps;

    public FenetreJeu(MoteurJeu moteurJeu) {

        this.setTitle("Find the path");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Utilisation d'un BorderLayout pour séparer
        // le labyrinthe et les informations
        this.setLayout(new BorderLayout());

        // Création du panneau du labyrinthe
        panneauJeu = new PanneauJeu(moteurJeu);

        // Ajout du labyrinthe au centre
        this.add(panneauJeu, BorderLayout.CENTER);

        // Création du panneau des informations
        JPanel panneauInfos = new JPanel();

        panneauInfos.setLayout(new GridLayout(2, 3, 15, 5));

        // Ajout d'une bordure autour du panneau
        panneauInfos.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        // Valeurs par défaut
        labelAlgorithme = new JLabel("Algorithme : -");
        labelNoeuds = new JLabel("Noeuds explores : 0");
        labelLongueur = new JLabel("Longueur : 0");
        labelCout = new JLabel("Cout : 0");
        labelTemps = new JLabel("Temps : 0 ms");

        // Ajout des informations dans le panneau
        panneauInfos.add(labelAlgorithme);
        panneauInfos.add(labelNoeuds);
        panneauInfos.add(labelLongueur);
        panneauInfos.add(labelCout);
        panneauInfos.add(labelTemps);

        // Ajout du panneau d'informations sous le labyrinthe
        this.add(panneauInfos, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Retourne le panneau de jeu
    public PanneauJeu getPanneauJeu() {
        return panneauJeu;
    }

    // Modifie le nom de l'algorithme affiché
    public void setAlgorithme(String algorithme) {
        labelAlgorithme.setText("Algorithme : " + algorithme);
    }

    // Modifie le nombre de noeuds explorés
    public void setNoeudsExplores(int noeuds) {
        labelNoeuds.setText("Noeuds explores : " + noeuds);
    }

    // Modifie la longueur du chemin
    public void setLongueurChemin(int longueur) {
        labelLongueur.setText("Longueur : " + longueur);
    }

    // Modifie le coût du chemin
    public void setCout(double cout) {
        labelCout.setText("Cout : " + cout);
    }

    // Modifie le temps d'exécution
    public void setTempsExecution(double temps) {
        labelTemps.setText("Temps : " + temps + " ms");
    }
}
