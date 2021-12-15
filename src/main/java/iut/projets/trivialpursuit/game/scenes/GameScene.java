package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.Sound;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.core.Scene;
import iut.projets.trivialpursuit.engine.core.UIElement;
import iut.projets.trivialpursuit.engine.types.*;
import iut.projets.trivialpursuit.game.*;
import iut.projets.trivialpursuit.game.actors.*;
import iut.projets.trivialpursuit.game.xml.Profile;
import iut.projets.trivialpursuit.game.xml.Question;
import iut.projets.trivialpursuit.game.xml.QuestionsManager;
import iut.projets.trivialpursuit.game.ui.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Scène dans laquelle se trouve le jeu.
 */
public class GameScene extends Scene {

    private final DirectionalLight light;
    private final PointLight mouseLight;
    private final Sound music, music_thinking;
    private final List<Player> players;
    private final Map<Player, Pawn> pawns;
    private final Map<Player, PlayerScores> scores;
    private final GameBoard gameBoard;
    private final GameUI gameUI;
    private int playerIndex;
    private final Vector3D lightDefaultIntensity, lightQuestionIntensity;

    /**
     * Construit la scène du jeu
     * @param gameInfo Données du jeu à démarrer.
     */
    public GameScene(GameInfo gameInfo) {
        this.players = gameInfo.getPlayers();
        playerIndex = 0;
        pawns = new HashMap<>();
        scores = new HashMap<>();

        lightDefaultIntensity = new Vector3D(1.5, 1.5, 1.5);
        lightQuestionIntensity = new Vector3D(0.2, 0.2, 0.2);

        light = new DirectionalLight();
        light.setIntensity(lightDefaultIntensity);
        mouseLight = new PointLight();

        gameUI = new GameUI(players, scores);

        setBackgroundColor(new Color(20,20,20));

        this.gameBoard = (GameBoard) addActor(GameBoard.class);

        music = Resources.getSound("/sounds/musics/game.wav");
        music_thinking = Resources.getSound("/sounds/musics/game_thinking.wav");
    }

    @Override
    public void start() {
        addLight(light);
        light.setDirection(new Vector3D(1,1,-1));

        addLight(mouseLight);
        mouseLight.setPosition(new Vector2D(0,0));
        mouseLight.setIntensity(new Vector3D(0,0,0));
        mouseLight.setRadius(4);
        mouseLight.setHeight(1);

        music.setLoop(true, (60.0/172)*41);
        music.setVolume(1);
        music_thinking.setLoop(true, (60.0/172)*41);
        music_thinking.setVolume(0);
        music.play();
        music_thinking.play();

        for (int i = 0; i < players.size(); i++) {
            Pawn pawn = (Pawn) addActor(Pawn.class);
            double rotation = Rotation.deg( 180 - i * 360.0/players.size() ).getRad();
            pawn.setPosition(new Vector2D(Math.cos(rotation)*4.3, Math.sin(rotation)*4.3));
            pawn.setColor(players.get(i).getPawnColor());
            pawn.setCurrentCase(gameBoard.getCenter());
            pawn.setRenderOrder(i);
            pawns.put(players.get(i), pawn);

            scores.put(players.get(i), new PlayerScores());
        }

        UIManager.addElement(gameUI);
        UITextButton pauseButton = new UITextButton("Pause");
        pauseButton.setAnchor(UIElement.Anchor.TOP_RIGHT);
        pauseButton.setAlignment(new Vector2D(-1, 1));
        pauseButton.setRenderOrder(2);
        gameUI.addElement(pauseButton);
        pauseButton.onClick(() -> {
            Pause pauseUI = new Pause();
            gameUI.setFocusable(false);
            UIManager.addElement(pauseUI);
            pauseUI.onResume(() -> {
                UIManager.removeElement(pauseUI);
                gameUI.setFocusable(true);
            });
            pauseUI.onQuit(() -> {
                backToMenu(() -> {
                    UIManager.removeElement(gameUI);
                    UIManager.removeElement(pauseUI);
                });
            });
        });

        playIntroAnimation(() -> {
            newTurn();
        });
    }

    /**
     * Annonce le joueur suivant et démarre son tour.
     */
    private void newTurn() {
        Player player = players.get(playerIndex);
        setLightIntensity(mouseLight, Vector3D.multiply(new Vector3D(player.getPawnColor().getRGB()), 5), 0.5);

        NewTurnAnnouncement newTurnAnnouncement = new NewTurnAnnouncement(player);
        newTurnAnnouncement.onDestroy(() -> {
            gameUI.removeElement(newTurnAnnouncement);
            beginTurn();
        });

        moveCameraTo(pawns.get(player).getPosition(), 3, 0.8, null);
        gameUI.addElement(newTurnAnnouncement);
    }

    /**
     * Lance un tour du début dans annoncer de joueur.
     * Utile pour permettre à un joueur de rejouer.
     */
    private void beginTurn() {
        Player player = players.get(playerIndex);

        RandomNumberUI randomNumberUI = new RandomNumberUI();

        randomNumberUI.onDestroy(() -> {
            gameUI.removeElement(randomNumberUI);

            moveCameraTo(new Vector2D(0,0), 1, 0.3, () -> {

                List<Case> cases = gameBoard.getReachableCases(pawns.get(player).getCurrentCase(), randomNumberUI.getNumber());
                CaseSelectionUI caseSelectionUI = new CaseSelectionUI(cases);

                caseSelectionUI.onDestroy(() -> {
                    gameUI.removeElement(caseSelectionUI);

                    Case c = caseSelectionUI.getSelected();
                    pawns.get(player).setCurrentCase(c);
                    pawns.get(player).moveTo(c.getPosition());

                    String text;
                    Color color;
                    if (c.getType() == Case.CaseType.MULTI) {
                        text = (scores.get(player).isComplete()) ? "Finissez-en !" : "Rien à faire ici";
                        color = Color.WHITE;
                    } else if (c.getType() == Case.CaseType.ROLL_AGAIN) {
                        text = "Rejouez !";
                        color = Color.WHITE;
                    } else {
                        text = player.getProfile().getCategory(c.getColor()).getCategoryName();
                        color = c.getColor().getRGB();
                    }
                    CaseAnnouncement caseAnnouncement = new CaseAnnouncement(text, color);

                    caseAnnouncement.onDestroy(() ->  {
                        gameUI.removeElement(caseAnnouncement);

                        if (c.getType() == Case.CaseType.ROLL_AGAIN) {
                            beginTurn();
                        } else if (c.getType() == Case.CaseType.MULTI) {
                            if (scores.get(player).isComplete()) {
                                setLightIntensity(light, lightQuestionIntensity, 0.5);
                                switchMusic(music, music_thinking);

                                TrivialPursuitColor questionColor = scores.get(player).getMostFailedColor();

                                CaseLightMulti caseLight = (CaseLightMulti) addActor(CaseLightMulti.class);
                                caseLight.show();

                                moveCameraTo(c.getPosition(), 3, 0.5, () -> {
                                    Profile.Category questionCategory = player.getProfile().getCategory(questionColor);
                                    Question question = QuestionsManager.getRandomQuestion(questionCategory.getCategoryName(), questionCategory.getDifficulty());
                                    QuestionUI questionUI = new QuestionUI(question);
                                    questionUI.onDestroy(() -> {
                                        gameUI.removeElement(questionUI);
                                        setLightIntensity(light, lightDefaultIntensity, 0.5);
                                        caseLight.remove();
                                        switchMusic(music_thinking, music);
                                        setLightIntensity(light, lightDefaultIntensity, 0.5);
                                        if (questionUI.getSuccess()) {
                                            end(player);
                                        } else {
                                            scores.get(player).addAttempt(questionColor, false);
                                            playerIndex = Math.floorMod(playerIndex + 1, players.size());
                                            newTurn();
                                        }
                                    });
                                    gameUI.addElement(questionUI);
                                });
                            } else {
                                playerIndex = Math.floorMod(playerIndex + 1, players.size());
                                newTurn();
                            }
                        } else {

                            setLightIntensity(light, lightQuestionIntensity, 0.5);
                            CaseLightSingle caseLight = (CaseLightSingle) addActor(CaseLightSingle.class);
                            caseLight.setPosition(c.getPosition());
                            caseLight.setColor(c.getColor());
                            caseLight.show();
                            moveCameraTo(c.getPosition(), 3, 0.5, () -> {

                                switchMusic(music, music_thinking);

                                Profile.Category questionCategory = player.getProfile().getCategory(c.getColor());
                                Question question = QuestionsManager.getRandomQuestion(questionCategory.getCategoryName(), questionCategory.getDifficulty());
                                QuestionUI questionUI = new QuestionUI(question);
                                questionUI.onDestroy(() -> {
                                    gameUI.removeElement(questionUI);
                                    switchMusic(music_thinking, music);
                                    setLightIntensity(light, lightDefaultIntensity, 0.5);
                                    caseLight.remove();
                                    if (questionUI.getSuccess()) {
                                        if (c.isSpecial()) {
                                            scores.get(player).addAttempt(c.getColor(), true);
                                            pawns.get(player).addSlice(c.getColor());
                                            gameUI.updateScores();
                                        }
                                        beginTurn();
                                    } else {
                                        scores.get(player).addAttempt(c.getColor(), false);
                                        playerIndex = Math.floorMod(playerIndex + 1, players.size());
                                        newTurn();
                                    }
                                });
                                gameUI.addElement(questionUI);

                            });
                        }

                    });
                    gameUI.addElement(caseAnnouncement);

                });
                gameUI.addElement(caseSelectionUI);

            });

        });

        gameUI.addElement(randomNumberUI);
    }

    /**
     * Arrête le jeu et annonce le gagnant.
     * @param player Joueur gagnant.
     */
    private void end(Player player) {
        ResultsUI resultsUI = new ResultsUI(player);
        resultsUI.onBackToMenuClicked(() -> {
            backToMenu(() -> {
                UIManager.removeElement(resultsUI);
                UIManager.removeElement(gameUI);
            });
        });
        UIManager.addElement(resultsUI);
    }

    /**
     * Retourne au menu principal.
     * @param onLoadingShow Fonction à exécuter quand l'écran de chargement a fini de s'afficher et qu'il occupe tout l'écran.
     */
    private void backToMenu(Runnable onLoadingShow) {
        music.stop();
        music_thinking.stop();
        GameLoadingScreen loadingScreen = new GameLoadingScreen();
        loadingScreen.onConstructAnimationFinished(() -> {
            if (onLoadingShow != null)
                onLoadingShow.run();
            SceneManager.setActiveScene(new MainMenuScene());
            loadingScreen.remove();
        });
        UIManager.addElement(loadingScreen);
    }

    /**
     * Déplace la caméra.
     * @param position Nouvelle position.
     * @param zoom Nouveau zoom.
     * @param speed Vitesse de transition.
     * @param then Fonction à exécuter ensuite.
     */
    private void moveCameraTo(Vector2D position, double zoom, double speed, Runnable then) {
        Vector2D cameraPosition = getCamera().getPosition();
        double cameraZoom = getCamera().getZoom();

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, speed)
        });
        animation.onUpdate(() -> {
            getCamera().setPosition(new Vector2D(
                    Animation.interpolate(cameraPosition.getX(), position.getX(), animation.getValue()),
                    Animation.interpolate(cameraPosition.getY(), position.getY(), animation.getValue())
            ));
            getCamera().setZoom( Animation.interpolate(cameraZoom, zoom, animation.getValue()) );
        });
        if (then != null)
            animation.onFinish(then);
        animation.start(this);
    }

    /**
     * Redéfinit l'intensité de la lumière.
     * @param light Lumière à modifier.
     * @param intensity Nouvelle intensité.
     * @param animationDuration Durée de la transition.
     */
    private void setLightIntensity(Light light, Vector3D intensity, double animationDuration) {
        Vector3D baseIntensity = light.getIntensity();
        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, animationDuration),
        });
        animation.onUpdate(() -> {
            light.setIntensity(new Vector3D(
                    Animation.interpolate(baseIntensity.getX(), intensity.getX(), animation.getValue()),
                    Animation.interpolate(baseIntensity.getY(), intensity.getY(), animation.getValue()),
                    Animation.interpolate(baseIntensity.getZ(), intensity.getZ(), animation.getValue())
            ));
        });
        animation.start(this);
    }

    /**
     * Joue l'animation d'introduction.
     * Zoome sur le plateau et monte la lumière au-dessus du plateau.
     * @param then Fonction à exécuter ensuite.
     */
    private void playIntroAnimation(Runnable then) {
        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, 4)
        });
        animation.onUpdate(() -> {
            getCamera().setZoom( Animation.interpolate(0.7, 3, animation.getValue()) );
            light.setDirection(new Vector3D(
                    1,
                    1,
                    1/Animation.interpolate(-20, -0.8, animation.getValue())
            ));
        });
        animation.onFinish(then);
        animation.start(this);
    }

    /**
     * Alterne entre deux musiques en changeant progressivement le volume.
     * @param current Musique actuelle.
     * @param target Musique cible.
     */
    public void switchMusic(Sound current, Sound target) {
        double current_volume = current.getVolume();
        double target_volume = target.getVolume();
        Animation animation = new Animation(new Keyframe[]{
                new Keyframe(0, 0),
                new Keyframe(1, 1)
        });
        animation.onUpdate(() -> {
            float value = (float) animation.getValue();
            target.setVolume((float) Animation.interpolate(target_volume, 1, value));
            current.setVolume((float) Animation.interpolate(current_volume, 0, value));
        });
        animation.onFinish(() -> {
            target.setVolume(1);
            current.setVolume(0);
        });
        animation.start(this);
    }

    @Override
    public void update(double frameTime) {
        mouseLight.setPosition(getMousePositionInScene());
    }
}
