package iut.projets.trivialpursuit.game.xml;

import iut.projets.trivialpursuit.game.TrivialPursuitColor;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente le profil d'un joueur.
 */
public class Profile {

    /**
     * Représente une catégorie associée à une difficulté contenue dans un profil.
     */
    public static class Category {
        private String categoryName;
        private String difficulty;

        /**
         * Renvoie le nom de la catégorie.
         * @return Nom de la catégorie.
         */
        public String getCategoryName() {
            return categoryName;
        }

        /**
         * Renvoie le nom de la difficulté.
         * @return Nom de la difficulté.
         */
        public String getDifficulty() {
            return difficulty;
        }
    }

    private String name;
    private final Map<TrivialPursuitColor, Category> categories;

    /**
     * Construit un profil.
     */
    Profile() {
        categories = new HashMap<>();
        categories.put(TrivialPursuitColor.BLUE, new Category());
        categories.put(TrivialPursuitColor.GREEN, new Category());
        categories.put(TrivialPursuitColor.PURPLE, new Category());
        categories.put(TrivialPursuitColor.PINK, new Category());
        categories.put(TrivialPursuitColor.ORANGE, new Category());
        categories.put(TrivialPursuitColor.YELLOW, new Category());
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie le nom du profil.
     * @return Nom du profil.
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie la catégorie associée à une couleur.
     * @param color Couleur de la catégorie.
     * @return Catégorie associée à une couleur.
     */
    public Category getCategory(TrivialPursuitColor color) {
        return categories.get(color);
    }

    /**
     * Ajoute ou redéfinit une catégorie.
     * @param color Couleur de la catégorie.
     * @param categoryName Nom de la catégorie.
     * @param difficulty Nom de la difficulté
     */
    public void setCategory(TrivialPursuitColor color, String categoryName, String difficulty) {
        categories.get(color).categoryName = categoryName;
        categories.get(color).difficulty = difficulty;
    }
}
