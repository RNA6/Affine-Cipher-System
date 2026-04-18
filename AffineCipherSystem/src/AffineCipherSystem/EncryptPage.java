/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AffineCipherSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EncryptPage extends JFrame{
    
    private JTextField plain_text;
    private JTextField  a;
    private JTextField b;
    
    private JLabel lbplain_text;
    private JLabel lba;
    private JLabel lbb;
    private JLabel result;
    
    private JButton btnencrypt;
    
    private AffineCipher affineCipher;
    
    
    public EncryptPage(AffineCipher affineCipher)
    {
        super("Encrypt page: ");
        setLayout(new FlowLayout());
        
        getContentPane().setBackground(new Color(218,255,252));
        
        Font font=new Font("Serif", Font.BOLD, 18);
        
        this.affineCipher = affineCipher;
                
        lbplain_text=new JLabel("Plain Text:");
        lbplain_text.setFont(font);
        add(lbplain_text);
        
        plain_text=new JTextField(20);
        add(plain_text);
        
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
        
        btnencrypt=new JButton("Encrypt");
        btnencrypt.setBackground(new Color(109,196,190));
        btnencrypt.setFont(font);
        add(btnencrypt);
        
        result=new JLabel();
        add(result);
        
        btnencrypt.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String plain =  plain_text.getText();
                int ka ;
                int kb; 
                
                //checks if any of the fields is empty
                if(plain_text.getText().trim().isEmpty()||a.getText().trim().isEmpty()||b.getText().trim().isEmpty())
                {
                    //informs the user of the problem
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }
                // informs the user to enter only numbers if he/she entered other characters
                try
                {
                    ka = Integer.parseInt(a.getText());
                    kb = Integer.parseInt(b.getText()); 
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "keys must be numbers");
                    return;

                }
                
                if(!affineCipher.checkCharacters(plain))
                {
                    JOptionPane.showMessageDialog(null, "Invalid Characters");
                    return;
                }
                
                if(!affineCipher.checkGCD(ka))
                {
                    JOptionPane.showMessageDialog(null, "Invalid key");
                    return;
                }
                
                String cipher = affineCipher.encrypt(ka,kb,plain);
                result.setText(cipher);                
       
            }            
        }
        );
        
        setSize(250,320);
        setLocation(530, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);  
    }    
    
}