package vue;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fenitra
 */
public class PanneauControles extends JPanel {
    private JButton boutonDijkstra;
    private JButton boutonAStar;
    private JButton boutonBFS;

    public PanneauControles(PanneauJeu panneauJeu) {
        // Configuration du panneau gauche
        this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        this.setBackground(new Color(15, 24, 40));
        this.setPreferredSize(new java.awt.Dimension(250, 0));
        this.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        // Titre de la section des algorithmes
        JLabel titreAlgorithmes = new JLabel("ALGORITHMES");
        titreAlgorithmes.setForeground(new Color(150, 170, 200));
        titreAlgorithmes.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreAlgorithmes.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        Color couleurDijkstra = new Color(25, 200, 230);
        Color couleurAStar = new Color(145, 100, 255);
        Color couleurBFS = new Color(255, 145, 55);
        Color couleurBordure = new Color(45, 60, 80);
        // Couleurs plus douces pour les noeuds explorés
        Color explorationDijkstra = new Color(18, 105, 120);
        Color explorationAStar = new Color(85, 60, 145);
        Color explorationBFS = new Color(145, 80, 30);

        // Bouton Dijkstra
        boutonDijkstra = new javax.swing.JButton("<html><span style='color:#19C8E6;'>●</span>&nbsp;&nbsp;Dijkstra</html>");
        boutonDijkstra.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        boutonDijkstra.setMaximumSize(new java.awt.Dimension(210, 38));
        boutonDijkstra.setBackground(new Color(25, 38, 58));
        boutonDijkstra.setForeground(Color.WHITE);
        boutonDijkstra.setFocusPainted(false);
        boutonDijkstra.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));

        // Bouton A*
        boutonAStar = new javax.swing.JButton("<html><span style='color:#9164FF;'>●</span>&nbsp;&nbsp;A*</html>");
        boutonAStar.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        boutonAStar.setMaximumSize(new java.awt.Dimension(210, 38));
        boutonAStar.setBackground(new Color(25, 38, 58));
        boutonAStar.setForeground(Color.WHITE);
        boutonAStar.setFocusPainted(false);
        boutonAStar.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));

        // Bouton BFS
        boutonBFS = new javax.swing.JButton("<html><span style='color:#FF9137;'>●</span>&nbsp;&nbsp;BFS</html>");
        boutonBFS.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        boutonBFS.setMaximumSize(new java.awt.Dimension(210, 38));
        boutonBFS.setBackground(new Color(25, 38, 58));
        boutonBFS.setForeground(Color.WHITE);
        boutonBFS.setFocusPainted(false);
        boutonBFS.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));

        // Dijkstra est sélectionné par défaut au lancement
        boutonDijkstra.setForeground(couleurDijkstra);
        boutonDijkstra.setBorder(BorderFactory.createLineBorder(couleurDijkstra, 2));
        panneauJeu.setCouleurExploration(explorationDijkstra);

        // Sélection de Dijkstra
        boutonDijkstra.addActionListener(e -> {
            boutonDijkstra.setForeground(couleurDijkstra);
            boutonAStar.setForeground(Color.WHITE);
            boutonBFS.setForeground(Color.WHITE);

            boutonDijkstra.setBorder(BorderFactory.createLineBorder(couleurDijkstra, 2));
            boutonAStar.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            boutonBFS.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            // Couleur des noeuds explorés par Dijkstra
            panneauJeu.setCouleurExploration(explorationDijkstra);
        });

        // Sélection de A*
        boutonAStar.addActionListener(e -> {
            boutonDijkstra.setForeground(Color.WHITE);
            boutonAStar.setForeground(couleurAStar);
            boutonBFS.setForeground(Color.WHITE);

            boutonDijkstra.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            boutonAStar.setBorder(BorderFactory.createLineBorder(couleurAStar, 2));
            boutonBFS.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            // Couleur des noeuds explorés par A*
            panneauJeu.setCouleurExploration(explorationAStar);
        });

        // Sélection de BFS
        boutonBFS.addActionListener(e -> {
            boutonDijkstra.setForeground(Color.WHITE);
            boutonAStar.setForeground(Color.WHITE);
            boutonBFS.setForeground(couleurBFS);

            boutonDijkstra.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            boutonAStar.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            boutonBFS.setBorder(BorderFactory.createLineBorder(couleurBFS, 2));
            // Couleur des noeuds explorés par BFS
            panneauJeu.setCouleurExploration(explorationBFS);
        });

        // Ajout des éléments
        this.add(titreAlgorithmes);
        this.add(javax.swing.Box.createVerticalStrut(15));
        this.add(boutonDijkstra);
        this.add(javax.swing.Box.createVerticalStrut(8));
        this.add(boutonAStar);
        this.add(javax.swing.Box.createVerticalStrut(8));
        this.add(boutonBFS);

        // Espace avant le mode d'exécution
        this.add(javax.swing.Box.createVerticalStrut(30));

        // Titre du mode d'exécution
        JLabel titreMode = new JLabel("MODE D'EXÉCUTION");
        titreMode.setForeground(new Color(150, 170, 200));
        titreMode.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreMode.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(titreMode);
        this.add(javax.swing.Box.createVerticalStrut(12));

        // Boutons pour choisir le mode d'exécution
        javax.swing.JRadioButton modeUnique = new javax.swing.JRadioButton("Individuel");
        javax.swing.JRadioButton modeComparaison = new javax.swing.JRadioButton("Comparaison simultanée");

        // Couleurs utilisées pour les deux modes
        Color fondMode = new Color(25, 38, 58);
        Color bleu = new Color(55, 125, 255);
        Color texteInactif = new Color(110, 135, 170);

        modeUnique.setBackground(fondMode);
        modeComparaison.setBackground(fondMode);

        modeUnique.setForeground(bleu);
        modeComparaison.setForeground(texteInactif);

        modeUnique.setFocusPainted(false);
        modeComparaison.setFocusPainted(false);

        // Bordure visible autour de chaque mode
        modeUnique.setBorderPainted(true);
        modeComparaison.setBorderPainted(true);

        modeUnique.setBorder(BorderFactory.createLineBorder(bleu, 1));
        modeComparaison.setBorder(BorderFactory.createLineBorder(new Color(45, 60, 80), 1));

        // Taille identique pour les deux boutons
        modeUnique.setPreferredSize(new java.awt.Dimension(220, 38));
        modeComparaison.setPreferredSize(new java.awt.Dimension(220, 38));

        modeUnique.setMaximumSize(new java.awt.Dimension(220, 38));
        modeComparaison.setMaximumSize(new java.awt.Dimension(220, 38));

        // Un seul mode peut être sélectionné à la fois
        javax.swing.ButtonGroup groupeMode = new javax.swing.ButtonGroup();
        groupeMode.add(modeUnique);
        groupeMode.add(modeComparaison);

        // Mode sélectionné au démarrage
        modeUnique.setSelected(true);

        // Change les couleurs selon le mode sélectionné
        modeUnique.addActionListener(e -> {
            modeUnique.setForeground(bleu);
            modeUnique.setBorder(BorderFactory.createLineBorder(bleu, 1));

            modeComparaison.setForeground(texteInactif);
            modeComparaison.setBorder(
                    BorderFactory.createLineBorder(new Color(45, 60, 80), 1)
            );
            // Permet de choisir un algorithme en mode individuel
            boutonDijkstra.setEnabled(true);
            boutonAStar.setEnabled(true);
            boutonBFS.setEnabled(true);
        });

        modeComparaison.addActionListener(e -> {
            modeComparaison.setForeground(bleu);
            modeComparaison.setBorder(BorderFactory.createLineBorder(bleu, 1));

            modeUnique.setForeground(texteInactif);
            modeUnique.setBorder(
                    BorderFactory.createLineBorder(new Color(45, 60, 80), 1)
            );
            // Aucun algorithme individuel ne peut être choisi en comparaison
            boutonDijkstra.setEnabled(false);
            boutonAStar.setEnabled(false);
            boutonBFS.setEnabled(false);

            // Retire la sélection visuelle des algorithmes
            boutonDijkstra.setForeground(Color.WHITE);
            boutonAStar.setForeground(Color.WHITE);
            boutonBFS.setForeground(Color.WHITE);
            boutonDijkstra.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            boutonAStar.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
            boutonBFS.setBorder(BorderFactory.createLineBorder(couleurBordure, 1));
        });

        this.add(modeUnique);
        this.add(javax.swing.Box.createVerticalStrut(5));
        this.add(modeComparaison);

        this.add(javax.swing.Box.createVerticalStrut(30));

        // Titre de la section contraintes
        JLabel titreContraintes = new JLabel("CONTRAINTES");
        titreContraintes.setForeground(new Color(150, 170, 200));
        titreContraintes.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreContraintes.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(titreContraintes);
        this.add(javax.swing.Box.createVerticalStrut(12));

        // Informations sur les contraintes utilisées dans le parcours
        JLabel sensUnique = new JLabel("➜  Sens unique");
        JLabel obstacles = new JLabel("■  Obstacles");

        sensUnique.setForeground(Color.WHITE);
        obstacles.setForeground(Color.WHITE);

        sensUnique.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        obstacles.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));

        sensUnique.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        obstacles.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(sensUnique);
        this.add(javax.swing.Box.createVerticalStrut(8));
        this.add(obstacles);

        this.add(javax.swing.Box.createVerticalStrut(30));

        // Titre de la section contrôles
        JLabel titreControles = new JLabel("CONTRÔLES");
        titreControles.setForeground(new Color(150, 170, 200));
        titreControles.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreControles.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(titreControles);
        this.add(javax.swing.Box.createVerticalStrut(12));

        // Boutons principaux de l'interface
        javax.swing.JButton boutonLancer = new javax.swing.JButton("▶  Lancer");
        javax.swing.JButton boutonReinitialiser = new javax.swing.JButton("↻  Réinitialiser");

        boutonLancer.setBackground(new Color(30, 110, 210));
        boutonLancer.setForeground(Color.WHITE);

        boutonReinitialiser.setBackground(new Color(25, 38, 58));
        boutonReinitialiser.setForeground(Color.WHITE);

        // Taille identique pour les deux boutons
        boutonLancer.setMaximumSize(new java.awt.Dimension(220, 38));
        boutonReinitialiser.setMaximumSize(new java.awt.Dimension(220, 38));

        boutonLancer.setFocusPainted(false);
        boutonReinitialiser.setFocusPainted(false);

        boutonLancer.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        boutonReinitialiser.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(boutonLancer);
        this.add(javax.swing.Box.createVerticalStrut(8));
        this.add(boutonReinitialiser);

        this.add(javax.swing.Box.createVerticalStrut(30));

        // Titre de la légende
        JLabel titreLegende = new JLabel("LÉGENDE");
        titreLegende.setForeground(new Color(150, 170, 200));
        titreLegende.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        titreLegende.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(titreLegende);
        this.add(javax.swing.Box.createVerticalStrut(12));

        // Éléments de la légende
        JLabel depart = new JLabel("■  Départ");
        JLabel arrivee = new JLabel("■  Arrivée");
        JLabel exploration = new JLabel("■  Nœuds explorés");
        JLabel chemin = new JLabel("■  Chemin le plus court");

        depart.setForeground(new Color(60, 190, 100));
        arrivee.setForeground(new Color(220, 70, 70));
        exploration.setForeground(new Color(15, 120, 135));
        chemin.setForeground(new Color(190, 145, 45));

        depart.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        arrivee.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        exploration.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        chemin.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        this.add(depart);
        this.add(javax.swing.Box.createVerticalStrut(6));
        this.add(arrivee);
        this.add(javax.swing.Box.createVerticalStrut(6));
        this.add(exploration);
        this.add(javax.swing.Box.createVerticalStrut(6));
        this.add(chemin);
    }
}
