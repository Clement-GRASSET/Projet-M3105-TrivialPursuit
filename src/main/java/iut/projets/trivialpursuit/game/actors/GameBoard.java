package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.game.materials.BaseMaterial;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class GameBoard extends Actor {

    private Case c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
            c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
            c21, c22, c23, c24, c25, c26, c27, c28, c29, c30,
            c31, c32, c33, c34, c35, c36, c37, c38, c39, c40,
            c41, c42;

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

    }

    @Override
    public void update(double frameTime) {

    }

    private Case addCase(Rotation angle, double distance) {
        Actor caseAcor = getScene().addActor(Case.class);
        double x = Math.cos(angle.getRad()) * distance;
        double y = Math.sin(angle.getRad()) * distance;
        caseAcor.setPosition(new Vector2D(x, y));
        return (Case) caseAcor;
    }

    public Case getCenter() {
        return c0;
    }

    public List<Case> getReachableCases(Case start, int steps) {
        List<Case> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        return list;
    }
}
