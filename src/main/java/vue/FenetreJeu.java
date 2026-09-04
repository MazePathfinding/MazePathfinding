package vue;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.MoteurJeu;
import java.awt.Color;
import java.awt.GridBagLayout;

/**
 *
 * @author fenit
 */
public class FenetreJeu extends JFrame {

    private PanneauJeu panneauJeu;
    private PanneauResultats panneauResultats;

    public FenetreJeu(MoteurJeu moteurJeu) {

        this.setTitle("Find the path");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        // Utilisation d'un BorderLayout pour séparer le labyrinthe et les informations
        this.setLayout(new BorderLayout());

        // Création du panneau du labyrinthe
        panneauJeu = new PanneauJeu(moteurJeu);

        // Création du panneau gauche
        PanneauControles panneauGauche = new PanneauControles(panneauJeu, this);

        // Panneau central : contient le titre et le labyrinthe
        JPanel panneauCentre = new JPanel(new BorderLayout());
        panneauCentre.setBackground(new Color(9, 14, 23));

        // Titre du projet
        JLabel titre = new JLabel("Recherche du trajet le plus court entre deux points");

        titre.setForeground(new Color(150, 170, 200));
        titre.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));

        titre.setHorizontalAlignment(JLabel.CENTER);

        // Espace autour du titre
        titre.setBorder(BorderFactory.createEmptyBorder(55, 10, 30, 10));

        // Panneau qui garde le labyrinthe centré
        JPanel conteneurLabyrinthe = new JPanel(new GridBagLayout());
        conteneurLabyrinthe.setBackground(new Color(9, 14, 23));
        conteneurLabyrinthe.add(panneauJeu);

        // Placement du titre et du labyrinthe
        panneauCentre.add(titre, BorderLayout.NORTH);
        panneauCentre.add(conteneurLabyrinthe, BorderLayout.CENTER);

        // Création du panneau des résultats
        panneauResultats = new PanneauResultats();

        // Placement des trois grandes zones
        this.add(panneauGauche, BorderLayout.WEST);
        this.add(panneauCentre, BorderLayout.CENTER);
        this.add(panneauResultats, BorderLayout.EAST);

        // Calcule d'abord la taille nécessaire aux composants
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    // Retourne le panneau de jeu
    public PanneauJeu getPanneauJeu() {
        return panneauJeu;
    }

    public PanneauResultats getPanneauResultats() {
        return panneauResultats;
    }
    
    

    // Modifie le nombre de noeuds explorés
    public void setNoeudsExplores(int noeuds) {
        panneauResultats.setNoeudsExplores(noeuds);
    }

    // Modifie la longueur du chemin
    public void setLongueurChemin(int longueur) {
        panneauResultats.setLongueurChemin(longueur);
    }

    // Modifie le coût du chemin
    public void setCout(double cout) {
        panneauResultats.setCout(cout);
    }

    // Modifie le temps d'exécution
    public void setTempsExecution(double temps) {
        panneauResultats.setTempsExecution(temps);
    }
}
