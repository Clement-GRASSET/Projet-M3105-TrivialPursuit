package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.game.materials.BaseMaterial;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Plateau du jeu.
 */
public class GameBoard extends Actor {

    private Case c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
            c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
            c21, c22, c23, c24, c25, c26, c27, c28, c29, c30,
            c31, c32, c33, c34, c35, c36, c37, c38, c39, c40,
            c41, c42;

    /**
     * Construit un plateau.
     */
    public GameBoard() {
        super();
        setScale(new Vector2D(100, 100));
        Image color = Resources.getImage("/textures/game_board/game_board.png");
        Image normals = Resources.getImage("/textures/game_board/game_board_n.png");
        setMaterial(new BaseMaterial(color, normals));
    }

    @Override
    public void start() {
        c0 = addCase(Rotation.deg(0),0);
        c0.setScale(new Vector2D(17,17));

        double distance1 = 13.8;
        c1 = addCase(Rotation.deg(270), distance1);
        c2 = addCase(Rotation.deg(330), distance1);
        c3 = addCase(Rotation.deg(30), distance1);
        c4 = addCase(Rotation.deg(90), distance1);
        c5 = addCase(Rotation.deg(150), distance1);
        c6 = addCase(Rotation.deg(210), distance1);

        double distance2 = 23.4;
        c7 = addCase(Rotation.deg(270), distance2);
        c8 = addCase(Rotation.deg(330), distance2);
        c9 = addCase(Rotation.deg(30), distance2);
        c10 = addCase(Rotation.deg(90), distance2);
        c11 = addCase(Rotation.deg(150), distance2);
        c12 = addCase(Rotation.deg(210), distance2);

        double distance3 = 32.8;
        c13 = addCase(Rotation.deg(270), distance3);
        c14 = addCase(Rotation.deg(330), distance3);
        c15 = addCase(Rotation.deg(30), distance3);
        c16 = addCase(Rotation.deg(90), distance3);
        c17 = addCase(Rotation.deg(150), distance3);
        c18 = addCase(Rotation.deg(210), distance3);


        double distance4 = 42.3;

        c19 = addCase(Rotation.deg(270), distance4);
        c20 = addCase(Rotation.deg(285), distance4);
        c21 = addCase(Rotation.deg(300), distance4);
        c22 = addCase(Rotation.deg(315), distance4);

        c23 = addCase(Rotation.deg(330), distance4);
        c24 = addCase(Rotation.deg(345), distance4);
        c25 = addCase(Rotation.deg(0), distance4);
        c26 = addCase(Rotation.deg(15), distance4);

        c27 = addCase(Rotation.deg(30), distance4);
        c28 = addCase(Rotation.deg(45), distance4);
        c29 = addCase(Rotation.deg(60), distance4);
        c30 = addCase(Rotation.deg(75), distance4);

        c31 = addCase(Rotation.deg(90), distance4);
        c32 = addCase(Rotation.deg(105), distance4);
        c33 = addCase(Rotation.deg(120), distance4);
        c34 = addCase(Rotation.deg(135), distance4);

        c35 = addCase(Rotation.deg(150), distance4);
        c36 = addCase(Rotation.deg(165), distance4);
        c37 = addCase(Rotation.deg(180), distance4);
        c38 = addCase(Rotation.deg(195), distance4);

        c39 = addCase(Rotation.deg(210), distance4);
        c40 = addCase(Rotation.deg(225), distance4);
        c41 = addCase(Rotation.deg(240), distance4);
        c42 = addCase(Rotation.deg(255), distance4);

        c0.setLinkedCases(new Case[] {c1, c2, c3, c4, c5, c6});
        c1.setLinkedCases(new Case[] {c0, c7});
        c2.setLinkedCases(new Case[] {c0, c8});
        c3.setLinkedCases(new Case[] {c0, c9});
        c4.setLinkedCases(new Case[] {c0, c10});
        c5.setLinkedCases(new Case[] {c0, c11});
        c6.setLinkedCases(new Case[] {c0, c12});
        c7.setLinkedCases(new Case[] {c1, c13});
        c8.setLinkedCases(new Case[] {c2, c14});
        c9.setLinkedCases(new Case[] {c3, c15});
        c10.setLinkedCases(new Case[] {c4, c16});
        c11.setLinkedCases(new Case[] {c5, c17});
        c12.setLinkedCases(new Case[] {c6, c18});
        c13.setLinkedCases(new Case[] {c7, c19});
        c14.setLinkedCases(new Case[] {c8, c23});
        c15.setLinkedCases(new Case[] {c9, c27});
        c16.setLinkedCases(new Case[] {c10, c31});
        c17.setLinkedCases(new Case[] {c11, c35});
        c18.setLinkedCases(new Case[] {c12, c39});
        c19.setLinkedCases(new Case[] {c42, c20, c13});
        c20.setLinkedCases(new Case[] {c19, c21});
        c21.setLinkedCases(new Case[] {c20, c22});
        c22.setLinkedCases(new Case[] {c21, c23});
        c23.setLinkedCases(new Case[] {c22, c24, c14});
        c24.setLinkedCases(new Case[] {c23, c25});
        c25.setLinkedCases(new Case[] {c24, c26});
        c26.setLinkedCases(new Case[] {c25, c27});
        c27.setLinkedCases(new Case[] {c26, c28, c15});
        c28.setLinkedCases(new Case[] {c27, c29});
        c29.setLinkedCases(new Case[] {c28, c30});
        c30.setLinkedCases(new Case[] {c29, c31});
        c31.setLinkedCases(new Case[] {c30, c32, c16});
        c32.setLinkedCases(new Case[] {c31, c33});
        c33.setLinkedCases(new Case[] {c32, c34});
        c34.setLinkedCases(new Case[] {c33, c35});
        c35.setLinkedCases(new Case[] {c34, c36, c17});
        c36.setLinkedCases(new Case[] {c35, c37});
        c37.setLinkedCases(new Case[] {c36, c38});
        c38.setLinkedCases(new Case[] {c37, c39});
        c39.setLinkedCases(new Case[] {c38, c40, c18});
        c40.setLinkedCases(new Case[] {c39, c41});
        c41.setLinkedCases(new Case[] {c40, c42});
        c42.setLinkedCases(new Case[] {c41, c19});

        c0.setType(Case.CaseType.MULTI);
        c1.setType(Case.CaseType.YELLOW);
        c2.setType(Case.CaseType.ORANGE);
        c3.setType(Case.CaseType.PINK);
        c4.setType(Case.CaseType.PURPLE);
        c5.setType(Case.CaseType.BLUE);
        c6.setType(Case.CaseType.GREEN);
        c7.setType(Case.CaseType.BLUE);
        c8.setType(Case.CaseType.GREEN);
        c9.setType(Case.CaseType.YELLOW);
        c10.setType(Case.CaseType.ORANGE);
        c11.setType(Case.CaseType.PINK);
        c12.setType(Case.CaseType.PURPLE);
        c13.setType(Case.CaseType.PINK);
        c14.setType(Case.CaseType.PURPLE);
        c15.setType(Case.CaseType.BLUE);
        c16.setType(Case.CaseType.GREEN);
        c17.setType(Case.CaseType.YELLOW);
        c18.setType(Case.CaseType.ORANGE);
        c19.setType(Case.CaseType.SPECIAL_GREEN);
        c20.setType(Case.CaseType.ROLL_AGAIN);
        c21.setType(Case.CaseType.ORANGE);
        c22.setType(Case.CaseType.BLUE);
        c23.setType(Case.CaseType.SPECIAL_YELLOW);
        c24.setType(Case.CaseType.ROLL_AGAIN);
        c25.setType(Case.CaseType.PINK);
        c26.setType(Case.CaseType.GREEN);
        c27.setType(Case.CaseType.SPECIAL_ORANGE);
        c28.setType(Case.CaseType.ROLL_AGAIN);
        c29.setType(Case.CaseType.PURPLE);
        c30.setType(Case.CaseType.YELLOW);
        c31.setType(Case.CaseType.SPECIAL_PINK);
        c32.setType(Case.CaseType.ROLL_AGAIN);
        c33.setType(Case.CaseType.BLUE);
        c34.setType(Case.CaseType.ORANGE);
        c35.setType(Case.CaseType.SPECIAL_PURPLE);
        c36.setType(Case.CaseType.ROLL_AGAIN);
        c37.setType(Case.CaseType.GREEN);
        c38.setType(Case.CaseType.PINK);
        c39.setType(Case.CaseType.SPECIAL_BLUE);
        c40.setType(Case.CaseType.ROLL_AGAIN);
        c41.setType(Case.CaseType.YELLOW);
        c42.setType(Case.CaseType.PURPLE);
    }

    @Override
    public void update(double frameTime) {

    }

    /**
     * Ajoute une case au plateau
     * @param angle Angle de la case avec le centre du plateau pour centre de rotation.
     * @param distance Distance de la case par rapport au centre.
     * @return Case créée.
     */
    private Case addCase(Rotation angle, double distance) {
        Actor caseActor = getScene().addActor(Case.class);
        double x = Math.cos(angle.getRad()) * distance;
        double y = Math.sin(angle.getRad()) * distance;
        caseActor.setPosition(new Vector2D(x, y));
        return (Case) caseActor;
    }

    /**
     * Renvoie la case au centre du plateau.
     * @return Case au centre du plateau.
     */
    public Case getCenter() {
        return c0;
    }

    /**
     * Renvoie toutes les cases atteignables à partir d'une case et en un certain nombre de coups.
     * @param start Case de départ.
     * @param steps Nombre de coups.
     * @return Liste de cases atteignables.
     */
    public List<Case> getReachableCases(Case start, int steps) {
        List<List<Case>> chemins = new ArrayList<>();
        List<Case> base = new ArrayList<>();
        base.add(start);
        chemins.add(base);

        for (int i = 0; i < steps; i++) {

            List<List<Case>> nouveaux_chemins = new ArrayList<>();
            List<Case> cases_adjacentes = new ArrayList<>();

            for (List<Case> chemin : chemins) {

                Case derniere_case = chemin.get(chemin.size()-1);
                for (Case case_adjacente : derniere_case.getLinkedCases()) {
                    if (!chemin.contains(case_adjacente) && !cases_adjacentes.contains(case_adjacente)) {
                        cases_adjacentes.add(case_adjacente);
                        List<Case> nouveau_chemin = new ArrayList<>();
                        nouveau_chemin.addAll(chemin);
                        nouveau_chemin.add(case_adjacente);
                        nouveaux_chemins.add(nouveau_chemin);
                    }
                }
            }
            chemins.clear();
            chemins.addAll(nouveaux_chemins);
        }

        List<Case> list = new ArrayList<>();
        for (List<Case> chemin : chemins) {
            list.add(chemin.get(chemin.size()-1));
        }

        return list;
    }
}
