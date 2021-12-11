package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.types.Vector2D;

/**
 * UIButton qui contient un UIText.
 */
public class UITextButton extends UIButton {

    private final UIText textElement;

    /**
     * Construit un UITextButton
     * @param text Texte à afficher dans le bouton.
     */
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

    /**
     * Renvoie l'élément de texte du bouton.
     * @return L'élément de texte du bouton.
     */
    public UIText getTextElement() {
        return textElement;
    }

    /**
     * Définit le texte à afficher.
     * @param text Texte à afficher.
     */
    public void setText(String text) {
        textElement.setText(text);
    }
}
