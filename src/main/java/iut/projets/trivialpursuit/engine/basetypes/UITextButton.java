package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.types.Vector2D;

public class UITextButton extends UIButton {

    private final UIText textElement;

    public UITextButton(String text) {
        super();

        textElement = new UIText();
        textElement.setAnchor(Anchor.CENTER_CENTER);
        textElement.setAlignment(new Vector2D(0, 0));
        textElement.setTextAlign(Anchor.CENTER_CENTER);
        textElement.setFontSize(3);
        setText(text);
        addElement(textElement);
    }

    public UIText getTextElement() {
        return textElement;
    }

    public void setText(String text) {
        textElement.setText(text);
    }
}
