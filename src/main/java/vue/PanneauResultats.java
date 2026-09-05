package vue;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.MoteurJeu;

/**
 *
 * @author fenitra
 */
public class PanneauResultats extends JPanel {

    private JLabel dijkstraCout;
    private JLabel dijkstraLongueur;
    private JLabel dijkstraExplores;
    private JLabel dijkstraTemps;
    private JLabel aStarCout;
    private JLabel aStarLongueur;
    private JLabel aStarExplores;
    private JLabel aStarTemps;
    private JLabel bfsCout;
    private JLabel bfsLongueur;
    private JLabel bfsExplores;
    private JLabel bfsTemps;
    // Couleurs utilisées pour chaque algorithme
    private final Color couleurDijkstra = new Color(25, 200, 230);
    private final Color couleurAStar = new Color(145, 100, 255);
    private final Color couleurBFS = new Color(255, 145, 55);
    private JLabel comparaisonDijkstraCout;
    private JLabel comparaisonDijkstraNoeuds;
    private JLabel comparaisonDijkstraTemps;
    private JLabel comparaisonAStarCout;
    private JLabel comparaisonAStarNoeuds;
    private JLabel comparaisonAStarTemps;
    private JLabel comparaisonBFSCout;
    private JLabel comparaisonBFSNoeuds;
    private JLabel comparaisonBFSTemps;
    private MoteurJeu.Algorithme algorithmeActuel;

    public PanneauResultats() {
        // Configuration du panneau droit
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(15, 24, 40));
        this.setPreferredSize(new java.awt.Dimension(280, 0));
        this.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        // Titre de la section
        JLabel titreResultats = new JLabel("RÉSULTATS");
        titreResultats.setForeground(new Color(150, 170, 200));
        titreResultats.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreResultats.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(titreResultats);
        this.add(javax.swing.Box.createVerticalStrut(12));

        // Ligne de séparation sous le titre
        javax.swing.JSeparator separateur = new javax.swing.JSeparator();
        separateur.setForeground(new Color(45, 60, 80));
        separateur.setBackground(new Color(45, 60, 80));
        separateur.setMaximumSize(new java.awt.Dimension(240, 1));
        separateur.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(separateur);
        this.add(javax.swing.Box.createVerticalStrut(20));

        // Valeurs de Dijkstra
        dijkstraCout = new JLabel("-");
        dijkstraLongueur = new JLabel("-");
        dijkstraExplores = new JLabel("-");
        dijkstraTemps = new JLabel("-");

        // Valeurs de A*
        aStarCout = new JLabel("-");
        aStarLongueur = new JLabel("-");
        aStarExplores = new JLabel("-");
        aStarTemps = new JLabel("-");

        // Valeurs de BFS
        bfsCout = new JLabel("-");
        bfsLongueur = new JLabel("-");
        bfsExplores = new JLabel("-");
        bfsTemps = new JLabel("-");

        // Création des cartes de résultats
        JPanel carteDijkstra = creerCarte("●  Dijkstra", couleurDijkstra, dijkstraCout, dijkstraLongueur, dijkstraExplores, dijkstraTemps);
        JPanel carteAStar = creerCarte("●  A*", couleurAStar, aStarCout, aStarLongueur, aStarExplores, aStarTemps);
        JPanel carteBFS = creerCarte("●  BFS", couleurBFS, bfsCout, bfsLongueur, bfsExplores, bfsTemps);

        // Ajout des cartes dans le panneau droit
        this.add(carteDijkstra);
        this.add(javax.swing.Box.createVerticalStrut(8));
        this.add(carteAStar);
        this.add(javax.swing.Box.createVerticalStrut(8));
        this.add(carteBFS);

        this.add(javax.swing.Box.createVerticalStrut(15));

        // Titre de la comparaison
        JLabel titreComparaison = new JLabel("COMPARAISON");
        titreComparaison.setForeground(new Color(150, 170, 200));
        titreComparaison.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreComparaison.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(titreComparaison);
        this.add(javax.swing.Box.createVerticalStrut(8));

        // Tableau de comparaison
        JPanel comparaison = new JPanel(new java.awt.GridLayout(4, 4, 5, 5));
        comparaison.setBackground(new Color(15, 24, 40));
        comparaison.setMaximumSize(new java.awt.Dimension(240, 90));
        comparaison.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Titres des colonnes
        comparaison.add(creerTexteComparaison("Algo", new Color(150, 170, 200)));
        comparaison.add(creerTexteComparaison("Coût", new Color(150, 170, 200)));
        comparaison.add(creerTexteComparaison("Nœuds", new Color(150, 170, 200)));
        comparaison.add(creerTexteComparaison("Temps", new Color(150, 170, 200)));

        // Ligne Dijkstra
        comparaisonDijkstraCout = creerTexteComparaison("-", Color.WHITE);
        comparaisonDijkstraNoeuds = creerTexteComparaison("-", Color.WHITE);
        comparaisonDijkstraTemps = creerTexteComparaison("-", Color.WHITE);
        comparaison.add(creerTexteComparaison("● Dijkstra", couleurDijkstra));
        comparaison.add(comparaisonDijkstraCout);
        comparaison.add(comparaisonDijkstraNoeuds);
        comparaison.add(comparaisonDijkstraTemps);

        // Ligne A*
        comparaisonAStarCout = creerTexteComparaison("-", Color.WHITE);
        comparaisonAStarNoeuds = creerTexteComparaison("-", Color.WHITE);
        comparaisonAStarTemps = creerTexteComparaison("-", Color.WHITE);
        comparaison.add(creerTexteComparaison("● A*", couleurAStar));
        comparaison.add(comparaisonAStarCout);
        comparaison.add(comparaisonAStarNoeuds);
        comparaison.add(comparaisonAStarTemps);

        // Ligne BFS
        comparaisonBFSCout = creerTexteComparaison("-", Color.WHITE);
        comparaisonBFSNoeuds = creerTexteComparaison("-", Color.WHITE);
        comparaisonBFSTemps = creerTexteComparaison("-", Color.WHITE);
        comparaison.add(creerTexteComparaison("● BFS", couleurBFS));
        comparaison.add(comparaisonBFSCout);
        comparaison.add(comparaisonBFSNoeuds);
        comparaison.add(comparaisonBFSTemps);

        this.add(comparaison);
    }

    // Crée une carte de résultats pour un algorithme
    private JPanel creerCarte(String nom, Color couleur, JLabel cout,
            JLabel longueur, JLabel explores, JLabel temps) {

        JPanel carte = new JPanel();
        carte.setLayout(new BoxLayout(carte, BoxLayout.Y_AXIS));
        carte.setBackground(new Color(20, 31, 48));
        carte.setBorder(BorderFactory.createLineBorder(couleur, 1));
        carte.setMaximumSize(new java.awt.Dimension(240, 125));
        carte.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        JLabel titre = new JLabel(nom);
        titre.setForeground(couleur);
        titre.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        titre.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 5));

        JPanel informations = new JPanel(new java.awt.GridLayout(4, 2, 5, 3));
        informations.setBackground(new Color(20, 31, 48));
        informations.setBorder(BorderFactory.createEmptyBorder(0, 5, 6, 5));

        ajouterInformation(informations, "Coût", cout);
        ajouterInformation(informations, "Longueur", longueur);
        ajouterInformation(informations, "Explorés", explores);
        ajouterInformation(informations, "Temps", temps);

        carte.add(titre);
        carte.add(informations);
        return carte;
    }

    // Ajoute une ligne avec le nom et la valeur d'un résultat
    private void ajouterInformation(JPanel panneau, String nom, JLabel valeur) {

        JLabel labelNom = new JLabel(nom);
        labelNom.setForeground(new Color(120, 145, 180));

        valeur.setForeground(Color.WHITE);
        valeur.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));

        panneau.add(labelNom);
        panneau.add(valeur);
    }

    // Crée un texte utilisé dans le tableau de comparaison
    private JLabel creerTexteComparaison(String texte, Color couleur) {
        JLabel label = new JLabel(texte);
        label.setForeground(couleur);
        label.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 11));
        return label;
    }

    // Met à jour le nombre de noeuds explorés 
    public void setNoeudsExplores(int noeuds) {
        if (algorithmeActuel == MoteurJeu.Algorithme.DIJKSTRA) {
            dijkstraExplores.setText(String.valueOf(noeuds));
            comparaisonDijkstraNoeuds.setText(String.valueOf(noeuds));
        } else if (algorithmeActuel == MoteurJeu.Algorithme.A_STAR) {
            aStarExplores.setText(String.valueOf(noeuds));
        } else if (algorithmeActuel == MoteurJeu.Algorithme.RECHERCHE_AVEUGLE) {
            bfsExplores.setText(String.valueOf(noeuds));
        }
    }

    // Met à jour la longueur du chemin trouvé 
    public void setLongueurChemin(int longueur) {
        if (algorithmeActuel == MoteurJeu.Algorithme.DIJKSTRA) {
            dijkstraLongueur.setText(longueur + " noeuds");
        } else if (algorithmeActuel == MoteurJeu.Algorithme.A_STAR) {
            aStarLongueur.setText(longueur + " noeuds");
        } else if (algorithmeActuel == MoteurJeu.Algorithme.RECHERCHE_AVEUGLE) {
            bfsLongueur.setText(longueur + " noeuds");
        }
    }

    // Met à jour le coût total du chemin trouvé 
    public void setCout(double cout) {
        if (algorithmeActuel == MoteurJeu.Algorithme.DIJKSTRA) {
            dijkstraCout.setText(String.valueOf(cout));
            comparaisonDijkstraCout.setText(String.valueOf(cout));
        } else if (algorithmeActuel == MoteurJeu.Algorithme.A_STAR) {
            aStarCout.setText(String.valueOf(cout));
        } else if (algorithmeActuel == MoteurJeu.Algorithme.RECHERCHE_AVEUGLE) {
            bfsCout.setText(String.valueOf(cout));
        }
    }

    // Met à jour le temps d'exécution 
    public void setTempsExecution(double temps) {
        String tempsTexte = String.format("%.2f ms", temps);
        if (algorithmeActuel == MoteurJeu.Algorithme.DIJKSTRA) {
            dijkstraTemps.setText(tempsTexte);
            comparaisonDijkstraTemps.setText(tempsTexte);
        } else if (algorithmeActuel == MoteurJeu.Algorithme.A_STAR) {
            aStarTemps.setText(tempsTexte);
        } else if (algorithmeActuel == MoteurJeu.Algorithme.RECHERCHE_AVEUGLE) {
            bfsTemps.setText(tempsTexte);
        }
    }

    public void setAlgorithmeActuel(MoteurJeu.Algorithme algorithmeActuel) {
        this.algorithmeActuel = algorithmeActuel;
    }

    // Remet tous les resultats affiches a leur valeur initiale
    public void reinitialiserResultats() {

        // Carte Dijkstra
        dijkstraCout.setText("-");
        dijkstraLongueur.setText("-");
        dijkstraExplores.setText("-");
        dijkstraTemps.setText("-");

        // Carte A*
        aStarCout.setText("-");
        aStarLongueur.setText("-");
        aStarExplores.setText("-");
        aStarTemps.setText("-");

        // Carte BFS
        bfsCout.setText("-");
        bfsLongueur.setText("-");
        bfsExplores.setText("-");
        bfsTemps.setText("-");

        // Tableau de comparaison Dijkstra
        comparaisonDijkstraCout.setText("-");
        comparaisonDijkstraNoeuds.setText("-");
        comparaisonDijkstraTemps.setText("-");

        // Tableau de comparaison A*
        comparaisonAStarCout.setText("-");
        comparaisonAStarNoeuds.setText("-");
        comparaisonAStarTemps.setText("-");

        // Tableau de comparaison BFS
        comparaisonBFSCout.setText("-");
        comparaisonBFSNoeuds.setText("-");
        comparaisonBFSTemps.setText("-");
    }
}
