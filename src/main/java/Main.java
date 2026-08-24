
import modele.ResultatRecherche;
import modele.Labyrinthe;
import modele.MoteurJeu;
import vue.FenetreJeu;
import vue.PanneauJeu;

public class Main {

    public static void main(String[] args) {

        // Création du labyrinthe
        Labyrinthe labyrinthe = new Labyrinthe();

        // Création du moteur
        MoteurJeu moteurJeu = new MoteurJeu(labyrinthe);

        // Création de la fenêtre
        FenetreJeu fenetre = new FenetreJeu(moteurJeu);

        // Récupération du panneau
        PanneauJeu panneauJeu = fenetre.getPanneauJeu();

        // Calcul avec Dijkstra
        ResultatRecherche resultat = moteurJeu.obtenirIndice();

        // Animation de la recherche
        panneauJeu.animerRecherche(
                resultat.getOrdreExploration(),
                resultat.getChemin(),
                resultat,
                fenetre
        );
    }
}
