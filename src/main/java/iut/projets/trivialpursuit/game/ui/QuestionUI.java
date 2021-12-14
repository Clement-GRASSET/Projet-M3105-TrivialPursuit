package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.questions.Question;

public class QuestionUI extends UIScreenContainer {

    private Runnable onDestroy;
    private boolean success, time_is_out, answered;
    private final double beginTime;
    private final UIScreenContainer questionContainer;
    UIText timeCount;

    public QuestionUI(Question question) {
        onDestroy = () -> {};
        success = false;
        time_is_out = false;
        answered = false;

        questionContainer = new UIScreenContainer();

        timeCount = new UIText();
        timeCount.setAnchor(Anchor.TOP_CENTER);
        timeCount.setAlignment(new Vector2D(0,0));
        timeCount.setTextAlign(Anchor.TOP_CENTER);
        timeCount.setPosition(new Vector2D(0, 5));
        timeCount.setFontSize(10);
        timeCount.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        questionContainer.addElement(timeCount);

        UIText questionText = new UIText();
        questionText.setText(question.getQuestion());
        questionText.setAnchor(Anchor.CENTER_CENTER);
        questionText.setAlignment(new Vector2D(0,0));
        questionText.setPosition(new Vector2D(0, -20));
        questionText.setTextAlign(Anchor.CENTER_CENTER);
        questionText.setFontSize(4);
        questionText.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        questionContainer.addElement(questionText);

        for (int i = 0; i < question.getAnswer().length; i++) {
            String answer = question.getAnswer()[i];
            UITextButton answerBtn = new UITextButton(answer);
            answerBtn.setAnchor(Anchor.CENTER_CENTER);
            answerBtn.setAlignment(new Vector2D(0,0));
            answerBtn.setPosition(new Vector2D(0,5 + i*10));
            answerBtn.setSize(new Vector2D(75, 7));
            answerBtn.getTextElement().setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            answerBtn.getTextElement().setFontSize(2);

            int questionIndex = i;
            answerBtn.onClick(() -> {
                answered = true;
                if (question.getRight() == questionIndex) {
                    success = true;
                    end("Bonne réponse !");
                } else {
                    success = false;
                    end("Mauvaise réponse !");
                }
            });

            questionContainer.addElement(answerBtn);
        }

        beginTime = System.nanoTime()/1000000000.0;
        addElement(questionContainer);
    }

    @Override
    public void update(double frameTime) {
        if (!time_is_out && !answered) {
            double time = System.nanoTime()/1000000000.0 - beginTime;
            int remaining = (int) Math.ceil(30 - time);
            timeCount.setText(String.valueOf(remaining));
            if (remaining <= 0) {
                time_is_out = true;
                success = false;
                end("Temps écoulé !");
            }
        }
    }

    /**
     * Définit la fonction à exécuter après que le joueur ait répondu où que le temps soit écoulé.
     * @param onDestroy Fonction à exécuter.
     */
    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

    private void end(String text) {
        if (success)
            Resources.getSound("/sounds/ui/button_answer_success.wav").play();
        else
            Resources.getSound("/sounds/ui/button_answer_fail.wav").play();

        removeElement(questionContainer);

        UIImage background = new UIImage();
        background.setSize(new Vector2D(500, 100));
        background.setOpacity(0);
        addElement(background);

        UIText UItext = new UIText();
        UItext.setAnchor(Anchor.CENTER_CENTER);
        UItext.setAlignment(new Vector2D(0,0));
        UItext.setTextAlign(Anchor.CENTER_CENTER);
        UItext.setText(text);
        UItext.setFontSize(1);
        UItext.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        addElement(UItext);

        Animation size_animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(13, 0.2),
                new Keyframe(13, 2),
                new Keyframe(200, 2.2),
        });
        size_animation.onUpdate(() -> {
            UItext.setFontSize(size_animation.getValue());
        });
        size_animation.onFinish(() -> {
            onDestroy.run();
        });
        size_animation.start(this);

        Animation opacity_animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, 0.2),
                new Keyframe(1, 2),
                new Keyframe(0, 2.2),
        });
        opacity_animation.onUpdate(() -> {
            background.setOpacity(Animation.interpolate(0, 0.7, opacity_animation.getValue()));
            UItext.setOpacity(opacity_animation.getValue());
        });
        opacity_animation.start(this);
    }

    public boolean getSuccess() {
        return success;
    }
}
