import javax.swing.SwingUtilities;
import modele.Labyrinthe;
import modele.MoteurJeu;
import modele.ResultatRecherche;
import vue.FenetreJeu;
import vue.PanneauJeu;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Création du labyrinthe et du moteur
            Labyrinthe labyrinthe = new Labyrinthe();
            MoteurJeu moteurJeu = new MoteurJeu(labyrinthe);

            // Ouverture directe de l'interface principale
            FenetreJeu fenetre = new FenetreJeu(moteurJeu);
            PanneauJeu panneauJeu = fenetre.getPanneauJeu();

            // Lance actuellement la recherche Dijkstra
            ResultatRecherche resultat = moteurJeu.obtenirIndice();

            // Affiche l'animation de la recherche
            panneauJeu.animerRecherche(resultat.getOrdreExploration(),resultat.getChemin(),resultat,fenetre);
        });
    }
}