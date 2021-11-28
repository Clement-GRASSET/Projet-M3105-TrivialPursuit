package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIScreenContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIText;
import iut.projets.trivialpursuit.game.questions.Question;

public class QuestionUI extends UIScreenContainer {

    private Runnable onDestroy;

    public QuestionUI() {
        onDestroy = () -> {};
    }

    public void addQuestion(Question question) {
        UIText questionText = new UIText();
        questionText.setText(question.getQuestion());
        questionText.setAnchor(Anchor.CENTER_CENTER);
        questionText.setAlignment(new Vector2D(0,0));
        questionText.setPosition(new Vector2D(0, -20));
        questionText.setTextAlign(Anchor.CENTER_CENTER);
        questionText.setFontSize(6);
        addElement(questionText);

        for (int i = 0; i < question.getAnswer().length; i++) {
            String answer = question.getAnswer()[i];
            UIButton answerBtn = new UIButton(answer);
            answerBtn.setAnchor(Anchor.CENTER_CENTER);
            answerBtn.setAlignment(new Vector2D(0,0));
            answerBtn.setPosition(new Vector2D(0,5 + i*10));
            answerBtn.setSize(new Vector2D(70, 8));

            answerBtn.onClick(() -> {
                Engine.getUserInterface().removeElement(this);
                onDestroy.run();
            });

            addElement(answerBtn);
        }
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

}
