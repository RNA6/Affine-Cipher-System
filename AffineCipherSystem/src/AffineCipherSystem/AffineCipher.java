/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AffineCipherSystem;

import java.util.ArrayList;
import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author RNA
 */
public class AffineCipher {
    //Each affine cipher has its own characters list
    //Affine cipher will not change. Therefore, its attributes are set final 
    private final ArrayList<Character> charcters;
    private final int numOfCharacters;
    private HashMap<Integer, Integer> keys; //similar to a dictionary to store key and key inverse pairs

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
        
        keys = new HashMap<>();
    }
    
    public boolean checkGCD(int keyA){//verifies if the greater common divisor of keyA and number of characters is 1
        //BigInteger is used to perform mathematical operations on integers of large size
        //number of characters and keyA are not large integers, but the class is used to call built-in GCD method
        
        BigInteger numOfCharacters_BigInteger = BigInteger.valueOf(this.numOfCharacters);
        BigInteger keyA_BigInteger = BigInteger.valueOf(keyA);
       
        //BigInteger gcd function calculates the gcd of the 2 numbers
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
    
    private int get_a_inverse(int keyA) throws ArithmeticException{ //gets the inverse of keyA
        
        if(keys.containsKey(keyA)){ //checks if the key is stored previously
            return keys.get(keyA);
        }
        
        else if(keys.containsValue(keyA)){ //checks if the key is stored as an inverse key
            for (int key : keys.keySet()) { //loops until the key inverse is found
                if(keys.get(key) == keyA){
                    return key;
                }
            }
        }        
        
        //if the inverse key is not found in the hashmap, it must be calculated
        BigInteger numOfCharacters_BigInteger = BigInteger.valueOf(this.numOfCharacters);
        BigInteger keyA_BigInteger = BigInteger.valueOf(keyA);
        
        //BigInteger modInverse function returns the modular multiplicative inverse which is the inverse of the key
        int keyA_inverse = keyA_BigInteger.modInverse(numOfCharacters_BigInteger).intValue();

        keys.put(keyA, keyA_inverse); //add the key and key inverse pair to the hashmap
        return keyA_inverse;
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
    
    public String decrypt(int keyA, int keyB, String cipherText) throws ArithmeticException{ //returns the decrypted text
        String plainText = "";
        int keyA_Inverse = get_a_inverse(keyA);
        
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
