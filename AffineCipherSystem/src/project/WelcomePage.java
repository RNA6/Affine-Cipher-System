/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RNA
 */
public class WelcomePage extends JFrame{
    
    private JLabel welcome_label;
    private JLabel action_label;
    private JButton encrypt_button;
    private JButton decrypt_button;
    
    private JPanel label_panel;
    private JPanel buttons_panel;
    
    private GridBagConstraints constraints;
    
    private AffineCipher affineCipher;
    
    public WelcomePage(AffineCipher affineCipher){
        this.getContentPane().setBackground(new Color(218, 255, 252));
        
        this.affineCipher = affineCipher;
        
        createLabel_panel();
        
        createLabels();
        
        label_panel.add(welcome_label);        
        label_panel.add(action_label);
        add(label_panel, BorderLayout.CENTER);
        
        createButtons_panel();        
        
        encrypt_button = createButton("Encrypt");
        encrypt_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //navigating between pages
                new EncryptPage(affineCipher);
            }
        });
        
        decrypt_button = createButton("Decrypt");
        decrypt_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //navigating between pages
                new DecryptPage(affineCipher);
            }
        });
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 30, 0);
        buttons_panel.add(encrypt_button, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        buttons_panel.add(decrypt_button, constraints);
                
        add(buttons_panel, BorderLayout.SOUTH);
        
        
        setSize(400,500);
        setLocation(450, 170);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void createLabel_panel(){
        label_panel = new JPanel();       
        label_panel.setOpaque(false);
        label_panel.setPreferredSize(new Dimension(320, 400)); 
    }
    
    private void createButtons_panel(){
        buttons_panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();        
        buttons_panel.setOpaque(false);
        buttons_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 75, 0));
    }
    
    private void createLabels(){        
        welcome_label = new JLabel("Welcome to Affine Cipher System"); 
        welcome_label.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        welcome_label.setFont(new Font("Serif", Font.BOLD, 23));
        
        action_label = new JLabel("Select your choice:");
        action_label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        action_label.setFont(new Font("Serif", Font.BOLD, 21));
    }
    
    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setBackground(new Color(109, 196, 190));
        button.setFocusable(false);
        
        return button;
    }

}
