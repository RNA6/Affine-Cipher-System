/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;

public class DecryptPage extends JFrame{
    
    private JTextField cipher_text;
    private JTextField  a;
    private JTextField b;
    
    private JLabel lbcipher_text;
    private JLabel lba;
    private JLabel lbb;
    private JLabel result;
    
    private JButton btndecrypt;
    
    private AffineCipher affineCipher;
    
    
    public DecryptPage(AffineCipher affineCipher)
    {
        super("Decrypt page: ");
        setLayout(new FlowLayout());
        
        getContentPane().setBackground(new Color(218,255,252));
        
        Font font=new Font("Serif", Font.BOLD, 20);
        
        this.affineCipher = affineCipher;
        
        lbcipher_text=new JLabel("Cipher Text:");
        lbcipher_text.setFont(font);
        add(lbcipher_text);
        
        cipher_text=new JTextField(20);
        add(cipher_text);
        
        lba=new JLabel("key a:");
        lba.setFont(font);
        add(lba);
        
        a=new JTextField(3);
        add(a);
        
        lbb=new JLabel("key b:");
        lbb.setFont(font);
        add(lbb);
        
        b=new JTextField(3);
        add(b);
        
        btndecrypt=new JButton("Decrypt");
        btndecrypt.setBackground(new Color(109,196,190));
        btndecrypt.setFont(font);
        add(btndecrypt);
        
        result=new JLabel();
        add(result);
        
        btndecrypt.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String cipher = cipher_text.getText();
                int ka = Integer.parseInt(a.getText());
                int kb = Integer.parseInt(b.getText());
                
                if(!affineCipher.checkCharacters(cipher))
                {
                    JOptionPane.showMessageDialog(null, "Invalid Characters");
                }
                
                if(!affineCipher.checkGCD(ka))
                {
                    JOptionPane.showMessageDialog(null, "Invalid key");
                }
                
                String plain = affineCipher.decrypt(ka,kb,cipher);
                result.setText(plain);
                
       
            }
            
        }
        );
        
        setSize(400,500);
        setLocation(450, 170);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);        
    }    
    
}
