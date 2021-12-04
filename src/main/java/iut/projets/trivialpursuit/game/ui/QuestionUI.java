package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.UIButton;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;
import iut.projets.trivialpursuit.engine.basetypes.UIText;
import iut.projets.trivialpursuit.game.questions.Question;

public class QuestionUI extends UIScreenContainer {

    private Runnable onDestroy;
    private boolean success, time_is_out;
    private double beginTime;
    private final UIScreenContainer questionContainer;
    UIText timeCount;

    public QuestionUI(Question question) {
        onDestroy = () -> {};
        success = false;
        time_is_out = false;
        questionContainer = new UIScreenContainer();

        timeCount = new UIText();
        timeCount.setAnchor(Anchor.TOP_CENTER);
        timeCount.setAlignment(new Vector2D(0,0));
        timeCount.setTextAlign(Anchor.TOP_CENTER);
        timeCount.setPosition(new Vector2D(0, 5));
        timeCount.setFontSize(10);
        questionContainer.addElement(timeCount);

        UIText questionText = new UIText();
        questionText.setText(question.getQuestion());
        questionText.setAnchor(Anchor.CENTER_CENTER);
        questionText.setAlignment(new Vector2D(0,0));
        questionText.setPosition(new Vector2D(0, -20));
        questionText.setTextAlign(Anchor.CENTER_CENTER);
        questionText.setFontSize(6);
        questionContainer.addElement(questionText);

        for (int i = 0; i < question.getAnswer().length; i++) {
            String answer = question.getAnswer()[i];
            UIButton answerBtn = new UIButton(answer);
            answerBtn.setAnchor(Anchor.CENTER_CENTER);
            answerBtn.setAlignment(new Vector2D(0,0));
            answerBtn.setPosition(new Vector2D(0,5 + i*10));
            answerBtn.setSize(new Vector2D(70, 8));

            int questionIndex = i;
            answerBtn.onClick(() -> {
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
        if (!time_is_out) {
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

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

    private void end(String text) {
        removeElement(questionContainer);

        UIText UItext = new UIText();
        UItext.setAnchor(Anchor.CENTER_CENTER);
        UItext.setAlignment(new Vector2D(0,0));
        UItext.setTextAlign(Anchor.CENTER_CENTER);
        UItext.setText(text);
        UItext.setFontSize(1);
        addElement(UItext);

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(15, 0.2),
                new Keyframe(15, 2),
                new Keyframe(200, 2.2),
        });
        animation.onUpdate(() -> {
            UItext.setFontSize(animation.getValue());
        });
        animation.onFinish(() -> {
            UIManager.removeElement(this);
            onDestroy.run();
        });
        animation.start(this);
    }

    public boolean getSuccess() {
        return success;
    }
}
