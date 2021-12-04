package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
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
    //private boolean hover, wasHovering;

    public Case() {

        /*hover = false;
        wasHovering = false;*/

        linkedCases = new Case[] {};
        type = CaseType.MULTI;

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

    public TrivialPursuitColor getColor() {
        switch (type) {
            case BLUE:
            case SPECIAL_BLUE: return TrivialPursuitColor.BLUE;
            case GREEN:
            case SPECIAL_GREEN: return TrivialPursuitColor.GREEN;
            case ORANGE:
            case SPECIAL_ORANGE: return TrivialPursuitColor.ORANGE;
            case PINK:
            case SPECIAL_PINK: return TrivialPursuitColor.PINK;
            case PURPLE:
            case SPECIAL_PURPLE: return TrivialPursuitColor.PURPLE;
            case YELLOW:
            case SPECIAL_YELLOW: return TrivialPursuitColor.YELLOW;
            default: return null;
        }
    }
}
