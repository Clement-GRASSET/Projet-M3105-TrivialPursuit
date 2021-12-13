package iut.projets.trivialpursuit.game.ui;


import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.game.GameInfo;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.Profile;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class ProfileMenu extends JDialog implements ActionListener {
    JButton add_profile;
    JComboBox collist, plist;
    Vector<ProfileZone> zoneslist;
    GameInfo player_selection = new GameInfo();
    String [] colors = {TrivialPursuitColor.BLUE.toString(),
                        TrivialPursuitColor.GREEN.toString(),
                        TrivialPursuitColor.ORANGE.toString(),
                        TrivialPursuitColor.PINK.toString(),
                        TrivialPursuitColor.PURPLE.toString(),
                        TrivialPursuitColor.YELLOW.toString()};

    String [] profiles = {"Profile 1", "Profile 2", "Profile 3", "Profile 4", "Profile 5", "Profile 6"};



    private class ProfileZone{
        int x, y, xfont, yfont, xremovebutton, yremovebutton, width_removebutton, height_removebutton;
        int xremovelogo, yremovelogo, width_removelogo, height_removelogo;
        int xaddlogo_horizontal, yaddlogo_horizontal, width_addlogo_horizontal, height_addlogo_horizontal;
        int xaddlogo_vertical, yaddlogo_vertical, width_addlogo_vertical, height_addlogo_vertical;


        final int width = getWidth()/7, height = getHeight()/3;
        final Color background_color = new Color(95, 95, 95);
        final Color font_color = Color.WHITE;
        final Color remove_color = new Color(229, 74, 74);
        final Color add_color = new Color(74, 173, 74);
        String name;

        ProfileZone(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;

            this.xfont = x+getWidth()/32;
            this.yfont = y+getHeight()/18;

            this.xremovebutton = x+width-getWidth()/36;
            this.yremovebutton = y;
            this.width_removebutton = width/6;
            this.height_removebutton = height/8;

            this.xremovelogo = xremovebutton+width_removebutton/4;
            this.yremovelogo = yremovebutton+height_removebutton/4;
            this.width_removelogo = 3*width_removebutton/4;
            this.height_removelogo = height_removebutton/3;
        }

        ProfileZone(int x, int y) {
            this.x = x;
            this.y = y;

            this.xaddlogo_horizontal = x+width/6;
            this.yaddlogo_horizontal = y+4*height/9;
            this.width_addlogo_horizontal = 2*width/3;
            this.height_addlogo_horizontal = height/9;

            this.xaddlogo_vertical = x+(width-height_addlogo_horizontal)/2;
            this.yaddlogo_vertical = y+(height-width_addlogo_horizontal)/2;
            this.width_addlogo_vertical = height_addlogo_horizontal;
            this.height_addlogo_vertical = width_addlogo_horizontal;
        }
    }




    private class MyComboBox<E> extends JComboBox<E> {
        public MyComboBox(E[] list) {
            super(list);
        }

        @Override
        public void updateUI() {
            super.updateUI();

            UIManager.put("ComboBox.squareButton", Boolean.FALSE);

            setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    JButton b = new JButton();

                    b.setBorder(BorderFactory.createEmptyBorder());
                    b.setVisible(false);

                    return b;
                }
            });
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
    }

    private void createProfileZone() {
        if (zoneslist.size() == 0)
            for (int i = 0; i < player_selection.getPlayers().size(); i++) {
                if (player_selection.getPlayers().size() == 6)
                    zoneslist.add(new ProfileZone(("Joueur " + (i + 1)), 320 - (((player_selection.getPlayers().size() * getWidth() / 7) / 2) + (((getWidth() / 49) * (player_selection.getPlayers().size() - 1)) / 2)) + i * 8 * getWidth() / 49, 5 * getHeight() / 24));
                else
                    zoneslist.add(new ProfileZone(("Joueur " + (i + 1)), 320 - ((((player_selection.getPlayers().size()+1) * getWidth() / 7) / 2) + (((getWidth() / 49) * (player_selection.getPlayers().size()+1)) / 2)) + i * 8 * getWidth() / 49, 5 * getHeight() / 24));
            }

        for (int i = 0 ; i < player_selection.getPlayers().size() ; i++) {
            collist = new MyComboBox(colors);
            collist.setBounds(zoneslist.get(i).x+8, zoneslist.get(i).y+35, getWidth()/9, getHeight()/15);
            collist.setFont(new Font("Arial", Font.PLAIN, collist.getHeight() / 2));

            collist.setFocusable(false);

            collist.setBackground(new Color(85, 85, 85));
            collist.setForeground(Color.WHITE);
            collist.setOpaque(true);
            collist.setVisible(true);

            add(collist);

            //Sélection de profile
            plist = new MyComboBox(profiles);
            plist.setBounds(zoneslist.get(i).x+8, zoneslist.get(i).y+75, getWidth()/9, getHeight()/15);
            plist.setFont(new Font("Arial", Font.PLAIN, collist.getHeight() / 2));

            plist.setFocusable(false);

            plist.setBackground(new Color(85, 85, 85));
            plist.setForeground(Color.WHITE);

            add(plist);


            JPanel panel = new JPanel();
            panel.setBackground(new Color(74, 74, 74));
            panel.setBounds(zoneslist.get(i).x, zoneslist.get(i).y, zoneslist.get(i).width, zoneslist.get(i).height);
            JLabel player_name = new JLabel("Joueur "+(i+1));
            player_name.setForeground(Color.WHITE);
            panel.add(player_name);

            //Boutton suppression
            if (player_selection.getPlayers().size() > 2)
            {
                JButton remove_profile = new JButton();
                remove_profile.setBounds(zoneslist.get(i).xremovebutton, zoneslist.get(i).yremovebutton, zoneslist.get(i).width_removebutton, zoneslist.get(i).height_removebutton);

                remove_profile.setOpaque(false);
                remove_profile.setFocusPainted(false);
                remove_profile.setContentAreaFilled(false);
                remove_profile.setBorderPainted(false);

                remove_profile.setActionCommand("action_removep");
                remove_profile.addActionListener(this);

                add(remove_profile);

                JLabel minus = new JLabel("-");
                minus.setBounds(zoneslist.get(i).xremovelogo, zoneslist.get(i).yremovelogo, zoneslist.get(i).width_removelogo, zoneslist.get(i).height_removelogo);
                minus.setForeground(zoneslist.get(i).remove_color);

                panel.add(minus);
            }
            add(panel);
            revalidate();
        }


        if (player_selection.getPlayers().size() < 6) {
            ProfileZone add_zone = new ProfileZone(320 - ((((player_selection.getPlayers().size()+1) * getWidth() / 7) / 2) + (((getWidth() / 49) * (player_selection.getPlayers().size())) / 2)) + (player_selection.getPlayers().size()) * 8 * getWidth() / 49, 5*getHeight()/24);

            JPanel panel = new JPanel();
            panel.setBackground(new Color(74, 74, 74));
            panel.setBounds(add_zone.x, add_zone.y, add_zone.width, add_zone.height);

            //Boutton ajouter
            add_profile = new JButton();
            add_profile.setBounds(add_zone.x, add_zone.y, add_zone.width, add_zone.height);

            add_profile.setOpaque(false);
            add_profile.setContentAreaFilled(false);
            add_profile.setBorderPainted(false);

            add_profile.setActionCommand("action_addp");
            add_profile.addActionListener(this);

            add(add_profile);

            JLabel plus = new JLabel("+");
            plus.setBounds(add_zone.xaddlogo_horizontal, add_zone.yaddlogo_vertical, add_zone.width_addlogo_horizontal, add_zone.height_addlogo_vertical);
            plus.setForeground(add_zone.add_color);
            plus.setFont(new Font("Arial", Font.BOLD, 100));

            panel.add(plus);
            add(panel);
            revalidate();
        }

        //Boutton quitter "OK"
        JButton quit_button = new JButton("OK");
        quit_button.setActionCommand("action_quit");
        quit_button.addActionListener(this);

        quit_button.setSize(getWidth() / 6, getHeight() / 8);
        quit_button.setLocation(getWidth() / 2 - quit_button.getSize().width / 2, 3 * getHeight() / 4 + quit_button.getSize().height / 2);
        quit_button.setFont(new Font("Arial", Font.PLAIN, quit_button.getHeight() / 2));

        quit_button.setFocusPainted(false);
        quit_button.setBorderPainted(false);

        quit_button.setBackground(new Color(255, 120, 0));
        quit_button.setForeground(Color.WHITE);

        quit_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                quit_button.setBackground(new Color(255, 160, 0));
            }

            public void mouseExited(MouseEvent e) {
                quit_button.setBackground(new Color(255, 120, 0));
            }

            public void mouseClicked(MouseEvent e) {
                quit_button.setBackground(new Color(255, 220, 50));
            }
        });

        add(quit_button);
    }

    ProfileMenu(JFrame owner, int width, int height, int x, int y, GameInfo pselect) {
        super(owner, true);
        zoneslist = new Vector<>();
        this.player_selection = pselect;

        setSize(width, height);
        setLocation(x+Game.getWindow().getWidth()/2-width/2, y+ Game.getWindow().getHeight()/2-height/2);
        setUndecorated(true);

        getContentPane().setBackground(new Color(95, 95, 95));
        setLayout(null);

        createProfileZone();

        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("action_addp") && player_selection.getPlayers().size() < 6) {
            Profile profile = new Profile();
            profile.setName("Joueur " + (player_selection.getPlayers().size() + 1));
            profile.setCategory(TrivialPursuitColor.BLUE, "Y", "Débutant");
            profile.setCategory(TrivialPursuitColor.GREEN, "Y", "Débutant");
            profile.setCategory(TrivialPursuitColor.ORANGE, "Y", "Intermédiaire");
            profile.setCategory(TrivialPursuitColor.PINK, "Y", "Intermédiaire");
            profile.setCategory(TrivialPursuitColor.PURPLE, "Y", "Expert");
            profile.setCategory(TrivialPursuitColor.YELLOW, "Y", "Expert");

            Vector<TrivialPursuitColor> colors_array = new Vector<>();
            colors_array.add(TrivialPursuitColor.BLUE);
            colors_array.add(TrivialPursuitColor.GREEN);
            colors_array.add(TrivialPursuitColor.ORANGE);
            colors_array.add(TrivialPursuitColor.PINK);
            colors_array.add(TrivialPursuitColor.PURPLE);
            colors_array.add(TrivialPursuitColor.YELLOW);

            for (int i = 0; i < player_selection.getPlayers().size(); i++) {
                colors_array.remove(player_selection.getPlayers().get(i).getPawnColor());
            }

            Player player = new Player(profile, colors_array.lastElement());
            player_selection.getPlayers().add(player);


            for (int i = 0; i < zoneslist.size(); i++)
                zoneslist.remove(i);

            zoneslist.setSize(0);

            System.out.println("Ajouter");

            getContentPane().removeAll();
            repaint();
            createProfileZone();
        }

        if(e.getActionCommand().equals("action_removep") && player_selection.getPlayers().size() > 2) {
            player_selection.getPlayers().remove(player_selection.getPlayers().size()-1);

            for (int i = 0 ; i < zoneslist.size() ; i++)
                zoneslist.remove(i);

            zoneslist.setSize(0);

            System.out.println("Supprimé");

            getContentPane().removeAll();
            repaint();
            createProfileZone();
        }

        if (e.getActionCommand().equals("action_quit")) {
            dispose();
        }
    }
}
