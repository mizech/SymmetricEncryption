/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 */
public class SymmetricEncryption
{
    public static boolean isStringValid(String input)
    {
        Pattern p = Pattern.compile("[^A-Z]+");
        Matcher m = p.matcher(input);

        if (m.find()) 
        {
            return false;
        } 
      
        return true;
    }

    public static String caesar(String input, int shift)
    {
        if (!isStringValid(input)) 
        {
            throw new IllegalArgumentException("Invalid input string.");
        }

        char[] inputChars = input.toCharArray();
        char[] crypted = new char[inputChars.length];

        for (int i = 0; i < inputChars.length; i++) 
        {
            int posInAlphabet = (inputChars[i] - 65 + shift) % 26;

            while (posInAlphabet < 0) 
            {
                posInAlphabet += 26;
            }

            crypted[i] = (char)((posInAlphabet + 65));
        }

        return String.copyValueOf(crypted);
    }
    
    public static String rot13(String input) 
    { 
        return caesar(input, 13);
    }
    
    public static String createValidKey(String keyBase, int exclusiveLimit) {
        String validKey = keyBase;
        
        while (validKey.length() < exclusiveLimit) {
            validKey += keyBase;
        }
        
        return validKey.substring(0, exclusiveLimit);
    }

    public static String vigenereEncrypt(String input, String key)
    {
        return "";
    }

    public static String vigenereDecrypt(String input, String key) 
    {
        return "";
    }
}
