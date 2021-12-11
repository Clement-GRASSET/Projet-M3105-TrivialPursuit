package iut.projets.trivialpursuit.game.ui;


import iut.projets.trivialpursuit.game.GameInfo;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.Profile;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileMenu extends JDialog  implements ActionListener  {
    JComboBox collist, catlist, difflist;
    GameInfo player_selection = new GameInfo();

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
        this.player_selection = pselect;

        setSize(width, height);
        setLocation(x+getWidth()/2, y+getHeight()/2);
        setUndecorated(true);
        getContentPane().setBackground(new Color(94, 94, 94));
        setLayout(null);
        
        /*
        String [] colors = {TrivialPursuitColor.BLUE.toString(),
                            TrivialPursuitColor.GREEN.toString(),
                            TrivialPursuitColor.ORANGE.toString(),
                            TrivialPursuitColor.PINK.toString(),
                            TrivialPursuitColor.PURPLE.toString(),
                            TrivialPursuitColor.YELLOW.toString()};

        collist = new MyComboBox(colors);
        collist.setBounds(getWidth()/2-2*getWidth()/12-getWidth()/6, 3*getHeight()/8, getWidth()/6, getHeight()/11);
        collist.setFont(new Font("Arial", Font.PLAIN, collist.getHeight()/2));

        collist.setFocusable(false);

        collist.setBackground(new Color(255, 120, 0));
        collist.setForeground(Color.WHITE);

        add(collist);


        String [] categories = {"Histoire", "Science", "Y", "Nature", "Arts", "Sports"};

        catlist = new MyComboBox(categories);
        catlist.setBounds(getWidth()/2-getWidth()/12, 3*getHeight()/8, getWidth()/6, getHeight()/11);
        catlist.setFont(new Font("Arial", Font.PLAIN, catlist.getHeight()/2));

        catlist.setFocusable(false);

        catlist.setBackground(new Color(255, 120, 0));
        catlist.setForeground(Color.WHITE);

        add(catlist);


        String [] difficulties = {"Débutant", "Intermédiraire", "Expert"};

        difflist = new MyComboBox(difficulties);
        difflist.setBounds(getWidth()/2+2*getWidth()/12, 3*getHeight()/8, getWidth()/6, getHeight()/11);
        difflist.setFont(new Font("Arial", Font.PLAIN, difflist.getHeight()/2));

        difflist.setFocusable(false);

        difflist.setBackground(new Color(255, 120, 0));
        difflist.setForeground(Color.WHITE);

        add(difflist);*/


        JButton quit_button = new JButton("OK");
        quit_button.setActionCommand("action_quit");
        quit_button.addActionListener(this);

        quit_button.setSize(getWidth()/6, getHeight()/8);
        quit_button.setLocation(getWidth()/2-quit_button.getSize().width/2, 3*getHeight()/4+quit_button.getSize().height/2);
        quit_button.setFont(new Font("Arial", Font.PLAIN, quit_button.getHeight()/2));

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


        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("action_quit")) {
            /*for (int i = 0 ; i < 6 ; i++) {
                Profile profile = new Profile();
                profile.setCategory(
                                    TrivialPursuitColor.valueOf(collist.getItemAt(collist.getSelectedIndex()).toString()),
                                                                catlist.getItemAt(catlist.getSelectedIndex()).toString(),
                                                                difflist.getItemAt(difflist.getSelectedIndex()).toString() );

                    player_selection.addPlayer(
                                                new Player(
                                                            profile,
                                                            TrivialPursuitColor.valueOf(collist.getItemAt(collist.getSelectedIndex()).toString()) ));
            }*/

            dispose();
            System.out.println("Fermée");
        }
    }
}
