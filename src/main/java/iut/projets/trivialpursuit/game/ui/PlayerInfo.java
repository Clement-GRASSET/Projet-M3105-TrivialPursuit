package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.basetypes.UIBoxContainer;
import iut.projets.trivialpursuit.engine.basetypes.UIImage;
import iut.projets.trivialpursuit.engine.basetypes.UIText;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.Profile;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfo extends UIBoxContainer {

    private static class ColorInfo extends UIBoxContainer {
        ColorInfo(Profile profile, TrivialPursuitColor color) {
            setAnchor(Anchor.TOP_CENTER);
            setSize(new Vector2D(24, 5));

            UIImage image = new UIImage();
            image.setImageUniform(color.getRGB());
            image.setAlignment(new Vector2D(1, 0));
            image.setAnchor(Anchor.CENTER_LEFT);
            image.setPosition(new Vector2D(0,0));
            image.setSize(new Vector2D(3.5,3.5));
            switch (color) {
                case BLUE: image.setImage(Resources.getImage("/images/slice_icon_blue.png")); break;
                case GREEN: image.setImage(Resources.getImage("/images/slice_icon_green.png")); break;
                case ORANGE: image.setImage(Resources.getImage("/images/slice_icon_orange.png")); break;
                case PINK: image.setImage(Resources.getImage("/images/slice_icon_pink.png")); break;
                case PURPLE: image.setImage(Resources.getImage("/images/slice_icon_purple.png")); break;
                case YELLOW: image.setImage(Resources.getImage("/images/slice_icon_yellow.png")); break;
            }
            addElement(image);

            UIText category = new UIText();
            category.setText(profile.getCategory(color).getCategoryName());
            category.setAnchor(Anchor.CENTER_LEFT);
            category.setAlignment(new Vector2D(0,0));
            category.setTextAlign(Anchor.CENTER_LEFT);
            category.setPosition(new Vector2D(5, -1));
            category.setFontSize(2);
            category.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            addElement(category);

            UIText difficulty = new UIText();
            difficulty.setText(profile.getCategory(color).getDifficulty());
            difficulty.setAnchor(Anchor.CENTER_LEFT);
            difficulty.setAlignment(new Vector2D(0,0));
            difficulty.setTextAlign(Anchor.CENTER_LEFT);
            difficulty.setPosition(new Vector2D(5, 1.4));
            difficulty.setFontSize(1.5);
            difficulty.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            addElement(difficulty);
        }
    }

    public PlayerInfo(Player player) {
        super();
        setAnchor(Anchor.CENTER_CENTER);
        setSize(new Vector2D(28,53));

        UIImage image = new UIImage();
        image.setSize(getSize());
        image.setAnchor(Anchor.TOP_LEFT);
        image.setAlignment(new Vector2D(1,1));
        switch (player.getPawnColor()) {
            case BLUE: image.setImage(Resources.getImage("/images/menu_player_info_blue.png")); break;
            case GREEN: image.setImage(Resources.getImage("/images/menu_player_info_green.png")); break;
            case ORANGE: image.setImage(Resources.getImage("/images/menu_player_info_orange.png")); break;
            case PINK: image.setImage(Resources.getImage("/images/menu_player_info_pink.png")); break;
            case PURPLE: image.setImage(Resources.getImage("/images/menu_player_info_purple.png")); break;
            case YELLOW: image.setImage(Resources.getImage("/images/menu_player_info_yellow.png")); break;
        }
        addElement(image);

        UIText name = new UIText();
        name.setText(player.getName());
        name.setAnchor(Anchor.TOP_CENTER);
        name.setAlignment(new Vector2D(0,0));
        name.setTextAlign(Anchor.CENTER_CENTER);
        name.setPosition(new Vector2D(0, 6));
        name.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        addElement(name);

        UIText profile = new UIText();
        profile.setText(player.getProfile().getName());
        profile.setAnchor(Anchor.TOP_CENTER);
        profile.setAlignment(new Vector2D(0,0));
        profile.setTextAlign(Anchor.CENTER_CENTER);
        profile.setPosition(new Vector2D(0, 10));
        profile.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        profile.setFontSize(2);
        addElement(profile);

        List<ColorInfo> colorInfos = new ArrayList<>();
        colorInfos.add(new ColorInfo(player.getProfile(), TrivialPursuitColor.PINK));
        colorInfos.add(new ColorInfo(player.getProfile(), TrivialPursuitColor.PURPLE));
        colorInfos.add(new ColorInfo(player.getProfile(), TrivialPursuitColor.BLUE));
        colorInfos.add(new ColorInfo(player.getProfile(), TrivialPursuitColor.GREEN));
        colorInfos.add(new ColorInfo(player.getProfile(), TrivialPursuitColor.YELLOW));
        colorInfos.add(new ColorInfo(player.getProfile(), TrivialPursuitColor.ORANGE));

        for (int i = 0; i < 6; i++) {
            colorInfos.get(i).setPosition(new Vector2D(0, 17 + i*6));
            addElement(colorInfos.get(i));
        }
    }

}
