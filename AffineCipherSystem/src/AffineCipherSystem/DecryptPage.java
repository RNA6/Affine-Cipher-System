/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AffineCipherSystem;

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
        
        Font font=new Font("Serif", Font.BOLD, 18);
        
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
                int ka ;
                int kb ;
                
                //checks if any of the fields is empty
                if(cipher_text.getText().trim().isEmpty()||a.getText().trim().isEmpty()||b.getText().trim().isEmpty())
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
                
                if(!affineCipher.checkCharacters(cipher))
                {
                    JOptionPane.showMessageDialog(null, "Invalid Characters");
                    return;
                }
                
                if(!affineCipher.checkGCD(ka))
                {
                    JOptionPane.showMessageDialog(null, "Invalid key");
                    return;
                }
                
                String plain;
                
                try{
                    plain = affineCipher.decrypt(ka,kb,cipher);
                }
                catch(ArithmeticException exception){
                    JOptionPane.showMessageDialog(null, "Key does not have inverse!");
                    return;
                }
                
                result.setText(plain);                
       
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
