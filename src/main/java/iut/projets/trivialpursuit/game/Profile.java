package iut.projets.trivialpursuit.game;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private class Category {
        private String categoryName;
        private String difficulty;
    }

    private String name;
    private final Map<TrivialPursuitColor, Category> categories;

    public Profile() {
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

    public String getName() {
        return name;
    }

    public void setCategory(TrivialPursuitColor color, String categoryName, String difficulty) {
        categories.get(color).categoryName = categoryName;
        categories.get(color).difficulty = difficulty;
    }
}
