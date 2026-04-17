/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.util.ArrayList;
import java.math.BigInteger;

/**
 *
 * @author RNA
 */
public class AffineCipher {
    //Each affine cipher has its own characters list
    //Affine cipher will not change. Therefore, its attributes are set final 
    private final ArrayList<Character> charcters;
    private final int numOfCharacters;

    public AffineCipher(String charactersString) {
        //For making it easy to understand, the set of characters are passed as String of characters
        this.charcters = new ArrayList<>();
        
        //converting the string of characters into ArrayList
        for(char character: charactersString.toCharArray()){ 
            if(!this.charcters.contains(character)){//checks for new characters
                this.charcters.add(character);
            }            
        }
        
        this.numOfCharacters = this.charcters.size();
        
        //##Logs
        System.out.println(this.charcters);
        System.out.println(this.numOfCharacters);
    }
    
    public boolean checkGCD(int keyA){//verifies if the greater common divisor of keyA and number of characters is 1
        //BigInteger is used to perform mathematical operations on integers of large size
        //number of characters and keyA are not large integers, but the class is used to call built-in GCD method
        
        BigInteger numOfCharacters_BigInteger = BigInteger.valueOf(this.numOfCharacters);
        BigInteger keyA_BigInteger = BigInteger.valueOf(keyA);
       
        int gcd = numOfCharacters_BigInteger.gcd(keyA_BigInteger).intValue();
        return gcd == 1;        
    }
    
    public boolean checkCharacters(String text){//verifies if the text does not contain characters out of the affine cipher range
        for(int index=0; index < text.length(); index++){
            if(!charcters.contains(text.charAt(index))){//checks for character ot of the affine cipher range
                return false;
            }
            //to avoid redundancy checked characters are removed from the text 
            text = text.replaceAll(String.valueOf(text.charAt(index)), "");
        }
        return true;  
    }
    
    public String encrypt(int keyA, int keyB, String plainText){//returns the encrypted text
        String cipherText = "";
        
        for(int index = 0; index < plainText.length(); index++){
            char textCharacter = plainText.charAt(index);
            int characterIndex = charcters.indexOf(textCharacter); //gets the index in the affine cipher characters list
            
            //apply affine cipher encryption algorithm
            int newCharacterIndex = (keyA * characterIndex + keyB) % numOfCharacters;
            cipherText += charcters.get(newCharacterIndex);
        }
        
        return cipherText;                
    }
    
    public String decrypt(int keyA_Inverse, int keyB, String cipherText){ //returns the decrypted text
        String plainText = "";
        
        for(int index = 0; index < cipherText.length(); index++){
            char textCharacter = cipherText.charAt(index);
            int characterIndex = charcters.indexOf(textCharacter); //gets the index in the affine cipher characters list
            
            //apply affine cipher decryption algorithm
            int newCharacterIndex = keyA_Inverse * (characterIndex - keyB) % numOfCharacters;
            if(newCharacterIndex < 0){ //checks if the calculated index is negative
                newCharacterIndex += numOfCharacters; //converts the negative index to positive index
            }
            plainText += charcters.get(newCharacterIndex);
        }
        
        return plainText;                
    }    
}
