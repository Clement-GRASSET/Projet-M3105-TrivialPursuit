package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.materials.BaseMaterial;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Case extends Actor {

    public enum CaseType {
        BLUE,
        GREEN,
        ORANGE,
        PINK,
        PURPLE,
        YELLOW,
        SPECIAL_BLUE,
        SPECIAL_GREEN,
        SPECIAL_ORANGE,
        SPECIAL_PINK,
        SPECIAL_PURPLE,
        SPECIAL_YELLOW,
        MULTI,
        ROLL_AGAIN,
    }

    private Case [] linkedCases;
    private CaseType type;

    //private final Image defaultImage, hoverImage;
    private boolean hover, wasHovering;

    public Case() {

        hover = false;
        wasHovering = false;

        linkedCases = new Case[] {};
        type = CaseType.MULTI;

        /*setScale(new Vector2D(8,8));

        Graphics2D g;

        defaultImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) defaultImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLUE);
        g.fillOval(0,0,100,100);
        g.dispose();

        hoverImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) hoverImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.CYAN);
        g.fillOval(0,0,100,100);
        g.dispose();

        Image normals = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) normals.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(127, 127, 255));
        g.fillOval(0,0,100,100);
        g.dispose();

        setMaterial(new BaseMaterial(defaultImage, normals));*/
        setRenderOrder(-5);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {
        /*Vector2D mouse = getScene().getMousePositionInScene();
        hover = Vector2D.distance(getPosition(), mouse) < getScale().getX()/2;
        if (hover) {
            System.out.println(type.name());
            *//*for (Case c : linkedCases)
                ((BaseMaterial) c.getMaterial()).setColor(hoverImage);*//*
        } else if (!hover && wasHovering) {
            *//*for (Case c : linkedCases)
                ((BaseMaterial) c.getMaterial()).setColor(defaultImage);*//*
        }
        wasHovering = hover;*/
    }

    public Case[] getLinkedCases() {
        return linkedCases;
    }

    public void setLinkedCases(Case[] linkedCases) {
        this.linkedCases = linkedCases;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public CaseType getType() {
        return type;
    }
}
