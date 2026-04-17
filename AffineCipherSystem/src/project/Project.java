/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project;

/**
 *
 * @author RNA
 */
public class Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789";
        
        AffineCipher a = new AffineCipher(characters);
        System.out.println(a.checkGCD(3));
        System.out.println(a.encrypt(3, 4, "MONEY"));
        System.out.println(a.decrypt(25, 4, "DJGQC"));
        
        new WelcomePage(a);
    }
    
}

/* Possible A keys and their inverse
(1, 1)
(2, 19)
(3, 25)
(4, 28)
(5, 15)
(6, 31)
(7, 16)
(8, 14)
(9, 33)
(10, 26)
(11, 27)
(12, 34)
(13, 20)
(17, 24)
(18, 35)
(21, 30)
(22, 32)
(23, 29)
(36, 36)
*/