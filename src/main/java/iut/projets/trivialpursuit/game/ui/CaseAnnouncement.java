package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.game.Animation;
import iut.projets.trivialpursuit.engine.game.Keyframe;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIScreenContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIText;
import iut.projets.trivialpursuit.game.Profile;
import iut.projets.trivialpursuit.game.actors.Case;

public class CaseAnnouncement extends UIScreenContainer {

    private Runnable onDestroy;

    public CaseAnnouncement(Case c, Profile playerProfile) {
        onDestroy = () -> {};

        UIText textElement = new UIText();

        if (c.getType() == Case.CaseType.MULTI)
            textElement.setText("Multi !");
        else if (c.getType() == Case.CaseType.ROLL_AGAIN)
            textElement.setText("Rejouez !");
        else
            textElement.setText(c.getType().name());

        textElement.setAnchor(Anchor.CENTER_CENTER);
        textElement.setAlignment(new Vector2D(0, 0));
        textElement.setTextAlign(Anchor.CENTER_CENTER);
        addElement(textElement);

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(15, 0.2),
                new Keyframe(15, 2),
                new Keyframe(200, 2.2),
        });
        animation.onUpdate(() -> {
            textElement.setFontSize(animation.getValue());
        });
        animation.onFinish(() -> {
            Engine.getUserInterface().removeElement(this);
            onDestroy.run();
        });
        animation.start(this);
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

}
