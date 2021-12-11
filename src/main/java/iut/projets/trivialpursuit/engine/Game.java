package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.window.GameWindow;

import java.io.File;

/**
 * Représente un jeu
 */
public abstract class Game {

    private static final GameWindow window = new GameWindow();
    private static GameLoop gameLoop;
    private static String directory;

    /**
     * Construit un jeu
     * @param name Nom de la fenêtre
     * @param directory Chemin de sauvegarde des données utilisateur
     * @param iconPath Chemin vers l'icone du jeu à partir de resources
     */
    public Game(String name, String directory, String iconPath) {

        Game.directory = directory;
        File file = new File(directory);
        if (!file.exists())
            file.mkdir();

        gameLoop = new GameLoop(this);
        gameLoop.start();
        Settings.load();
        Game.getWindow().setTitle(name);
        Game.getWindow().setIconImage(Resources.getImage(iconPath));
    }

    /**
     * @return La fenêtre qui contient le jeu
     */
    public static GameWindow getWindow() {
        return window;
    }

    /**
     * @return L'instance de la classe qui gère la boucle du jeu
     */
    public static GameLoop getGameLoop() {
        return gameLoop;
    }

    /**
     * @return Le chemin du répertoire de sauvegarde du jeu
     */
    public static String getDirectory() {
        return directory;
    }

    /**
     * Première méthode lancée au démarrage du jeu
     */
    public abstract void start();

}
