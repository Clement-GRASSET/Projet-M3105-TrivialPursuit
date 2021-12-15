package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.game.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Vector;

/**
 * Menu de sélection des profils des joueurs.
 */
public class ProfileMenu extends JDialog implements ActionListener, ItemListener {
    private boolean exists = false;
    private GameInfo player_selection;

    private JButton add_profile;
    private JComboBox collist, plist;

    private Vector<JComboBox> vcollist = new Vector<>(), vplist = new Vector<>();
    private Vector<ProfileZone> zoneslist;
    private Vector<JButton> vremove = new Vector<>();

    String [] colors = {TrivialPursuitColor.BLUE.toString(),
                        TrivialPursuitColor.GREEN.toString(),
                        TrivialPursuitColor.ORANGE.toString(),
                        TrivialPursuitColor.PINK.toString(),
                        TrivialPursuitColor.PURPLE.toString(),
                        TrivialPursuitColor.YELLOW.toString()};

    String [] profiles;

    private class ProfileZone{
        private int x, y, xfont, yfont, xremovebutton, yremovebutton, width_removebutton, height_removebutton;

        private final int width = getWidth()/7, height = getHeight()/3;
        private final Color background_color = new Color(95, 95, 95);
        private final Color font_color = Color.WHITE;
        private final Color remove_color = new Color(229, 74, 74);
        private final Color add_color = new Color(74, 173, 74);
        private String name;

        ProfileZone(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;

            this.xfont = x+getWidth()/32;
            this.yfont = y+getHeight()/18;

            this.xremovebutton = x+width-29*getWidth()/720;
            this.yremovebutton = y+getWidth()/48;
            this.width_removebutton = width/6;
            this.height_removebutton = height/8;
        }

        ProfileZone(int x, int y) {
            this.x = x;
            this.y = y;
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




    ProfileMenu(JFrame owner, int width, int height, int x, int y, GameInfo pselect) {
        super(owner, true);

        this.zoneslist = new Vector<>();
        this.player_selection = pselect;

        this.profiles = new String[ProfilesManager.getProfilesListSize()];

        for (int i = 0 ; i < ProfilesManager.getProfilesListSize() ; i++) {
            System.out.println(ProfilesManager.getProfiles(i).getName());
            this.profiles[i] = ProfilesManager.getProfiles(i).getName();}

        setSize(width, height);
        setLocation(x+Game.getWindow().getWidth()/2-width/2, y+ Game.getWindow().getHeight()/2-height/2);
        setUndecorated(true);

        getContentPane().setBackground(new Color(74, 74, 74));
        setLayout(null);

        createProfileZone();

        setResizable(false);
        setVisible(true);
    }

    private void createProfileZone() {
        if (zoneslist.size() == 0)
            for (int i = 0; i < player_selection.getPlayers().size(); i++) {
                if (player_selection.getPlayers().size() == 6)
                    zoneslist.add(new ProfileZone(("Joueur " + (i + 1)), 320 - (((player_selection.getPlayers().size() * getWidth() / 7) / 2) + (((getWidth() / 49) * (player_selection.getPlayers().size() - 1)) / 2)) + i * 8 * getWidth() / 49, 5 * getHeight() / 24));
                else
                    zoneslist.add(new ProfileZone(("Joueur " + (i + 1)), 320 - ((((player_selection.getPlayers().size()+1) * getWidth() / 7) / 2) + (((getWidth() / 49) * (player_selection.getPlayers().size()+1)) / 2)) + i * 8 * getWidth() / 49, 5 * getHeight() / 24));
            }

        vcollist.clear();
        vplist.clear();
        vremove.clear();

        for (int i = 0 ; i < player_selection.getPlayers().size() ; i++) {
            collist = new MyComboBox(colors);
            collist.setBounds(zoneslist.get(i).x+8, zoneslist.get(i).y+35, getWidth()/9, getHeight()/15);
            collist.setFont(new Font("Arial", Font.PLAIN, collist.getHeight() / 2));

            collist.setFocusable(false);

            collist.setBackground(new Color(85, 85, 85));
            collist.setForeground(Color.WHITE);
            collist.setOpaque(true);
            collist.setVisible(true);

            collist.addItemListener(this);

            collist.setSelectedItem(player_selection.getPlayers().get(i).getPawnColor().toString());

            vcollist.add(collist);
            add(collist);

            //Sélection de profile
            plist = new MyComboBox(profiles);
            plist.setBounds(zoneslist.get(i).x+8, zoneslist.get(i).y+75, getWidth()/9, getHeight()/15);
            plist.setFont(new Font("Arial", Font.PLAIN, collist.getHeight() / 2));

            plist.setFocusable(false);

            plist.setBackground(new Color(85, 85, 85));
            plist.setForeground(zoneslist.get(i).font_color);

            plist.addItemListener(this);

            plist.setSelectedItem(player_selection.getPlayers().get(i).getProfile().getName());

            vplist.add(plist);
            add(plist);


            JPanel panel = new JPanel();
            panel.setBackground(zoneslist.get(i).background_color);
            panel.setBounds(zoneslist.get(i).x, zoneslist.get(i).y, zoneslist.get(i).width, zoneslist.get(i).height);

            JLabel player_name = new JLabel("Joueur "+(i+1));
            player_name.setForeground(zoneslist.get(i).font_color);

            panel.add(player_name);

            //Boutton suppression
            if (player_selection.getPlayers().size() > 2)
            {
                JButton remove_profile = new JButton();
                remove_profile.setBounds(zoneslist.get(i).xremovebutton, zoneslist.get(i).yremovebutton, zoneslist.get(i).width_removebutton, zoneslist.get(i).height_removebutton);

                remove_profile.setOpaque(false);
                remove_profile.setFocusPainted(false);
                remove_profile.setContentAreaFilled(false);
                remove_profile.setBorder(BorderFactory.createLineBorder(zoneslist.get(i).remove_color));

                remove_profile.setActionCommand("action_removep");
                remove_profile.addActionListener(this);

                vremove.add(remove_profile);
                add(remove_profile);

                JLabel minus = new JLabel("-");
                minus.setForeground(zoneslist.get(i).remove_color);
                minus.setFont(new Font("Arial", Font.BOLD, 24));

                panel.add(minus);
            }
            add(panel);
            revalidate();
        }


        //Zone d'ajout
        if (player_selection.getPlayers().size() < 6) {
            ProfileZone add_zone = new ProfileZone(320-((((player_selection.getPlayers().size()+1)*getWidth()/7)/2)+(((getWidth()/49)*(player_selection.getPlayers().size()))/2))+(player_selection.getPlayers().size())*8*getWidth()/49, 5*getHeight()/24);

            JPanel panel = new JPanel();
            panel.setBackground(add_zone.background_color);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boutton d'ajout de profile
        if(e.getActionCommand().equals("action_addp") && player_selection.getPlayers().size() < 6) {
            Profile profile = ProfilesManager.getDefaultProfile();

            Vector<TrivialPursuitColor> colors_array = new Vector<>();
            colors_array.add(TrivialPursuitColor.BLUE);
            colors_array.add(TrivialPursuitColor.GREEN);
            colors_array.add(TrivialPursuitColor.ORANGE);
            colors_array.add(TrivialPursuitColor.PINK);
            colors_array.add(TrivialPursuitColor.PURPLE);
            colors_array.add(TrivialPursuitColor.YELLOW);

            for (int i = 0; i < player_selection.getPlayers().size(); i++)
                colors_array.remove(player_selection.getPlayers().get(i).getPawnColor());

            Player player = new Player(profile, colors_array.lastElement());
            player.setName("Joueur " + (player_selection.getPlayers().size()+1));
            player_selection.getPlayers().add(player);


            for (int i = 0; i < zoneslist.size(); i++)
                zoneslist.remove(i);

            zoneslist.setSize(0);

            System.out.println("Ajouter");

            getContentPane().removeAll();
            repaint();
            createProfileZone();
        }

        //Boutton de suppression de profile
        if(e.getActionCommand().equals("action_removep") && player_selection.getPlayers().size() > 2) {
            for (int i = 0 ;  i < player_selection.getPlayers().size() ; i++)
                if (e.getSource() == vremove.get(i))
                    player_selection.getPlayers().remove(i);

            for (int i = 0 ;  i < player_selection.getPlayers().size() ; i++)
                    player_selection.getPlayers().get(i).setName("Joueur " + (i+1));

            for (int i = 0 ; i < zoneslist.size() ; i++)
                zoneslist.remove(i);

            zoneslist.setSize(0);

            System.out.println("Supprimé");

            getContentPane().removeAll();
            repaint();
            createProfileZone();
        }

        //Bouton OK pour quitter le menu
        if (e.getActionCommand().equals("action_quit")) {
            exists = false;
            int nbexisting = 0;

            for (int i = 0 ; i < player_selection.getPlayers().size() ; i++)
                for (int j = 0; j < player_selection.getPlayers().size(); j++)
                    if (player_selection.getPlayers().get(i).getPawnColor().toString() == player_selection.getPlayers().get(j).getPawnColor().toString() && i != j)
                        nbexisting++;

            if (nbexisting >= 1)
                exists = true;

                if (!exists) {
                    System.out.println("Fermer");
                    dispose();
                }
                else
                    System.out.println("Existe déjà");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            exists = false;
            int nbexisting = 0;

            for (int j = 0 ; j < player_selection.getPlayers().size() ; j++)
                if (e.getItem().toString() == player_selection.getPlayers().get(j).getPawnColor().toString())
                    nbexisting++;

            if (nbexisting >= 1)
                exists = true;


            for (int i = 0 ; i < vcollist.size() ; i++)
                if (e.getSource().equals(vcollist.get(i))) {
                    Player player = new Player(player_selection.getPlayers().get(i).getProfile(), TrivialPursuitColor.valueOf(vcollist.get(i).getSelectedItem().toString()));
                    player.setName("Joueur " + (i+1));
                    player_selection.getPlayers().set(i, player);
                }

            for (int i = 0 ; i < vplist.size() ; i++)
                if (e.getSource().equals(vplist.get(i)))
                    for (int j = 0; j < ProfilesManager.getProfilesListSize(); j++)
                        if (ProfilesManager.getProfiles(j).getName() == vplist.get(i).getSelectedItem().toString()) {
                            Player player = new Player(ProfilesManager.getProfiles(j), player_selection.getPlayers().get(i).getPawnColor());
                            player.setName("Joueur " + (i+1));
                            player_selection.getPlayers().set(i, player);
                        }

        }
    }
}
