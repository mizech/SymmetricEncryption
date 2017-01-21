/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymmetricEncryption
{
    private static final int COUNT_CHARS_ALPHABET = 26;
    private static final char A = 'A';
    
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
        validateInput(input);

        char[] inputChars = input.toCharArray();
        char[] crypted = new char[inputChars.length];

        for (int i = 0; i < inputChars.length; i++) 
        {
            int posInAlphabet = (inputChars[i] - A + shift)
                    % COUNT_CHARS_ALPHABET;

            while (posInAlphabet < 0) 
            {
                posInAlphabet += COUNT_CHARS_ALPHABET;
            }

            crypted[i] = (char)(posInAlphabet += A);
        }

        return String.copyValueOf(crypted);
    }
    
    public static String rot13(String input) 
    { 
        return caesar(input, 13);
    }
    
    private static String createValidKey(String keyBase, int exclusiveLimit) 
    {
        String validKey = keyBase;
        
        while (validKey.length() < exclusiveLimit) 
        {
            validKey += keyBase;
        }
        
        return validKey.substring(0, exclusiveLimit);
    }
    
    private static void validateInput(String input) 
    {
        if (!isStringValid(input)) 
        {
            throw new IllegalArgumentException("Invalid input string.");
        }
    }
    
    private static String join(String[] strings) 
    {
        StringBuilder joinedElements = new StringBuilder();
        
        for (String currentString : strings) 
        {
            joinedElements.append(currentString);
        }
        
        return joinedElements.toString();
    }

    public static String vigenereEncrypt(String input, String key)
    {
        validateInput(input);
        validateInput(key);
        
        key = createValidKey(key, input.length());
        String[] encryptedStrings = new String[input.length()];
        
        for (int i = 0; i < input.length(); i++) 
        {
            String currentInput = input.substring(i, i + 1);
                                               
            encryptedStrings[i] = caesar( currentInput,
                                          (key.charAt(i) - A)
                                        );
        }
              
        return join(encryptedStrings);
    }

    public static String vigenereDecrypt(String input, String key) 
    {
        validateInput(input);
        validateInput(key);
        
        key = createValidKey(key, input.length());
        String[] encryptedStrings = new String[input.length()];
        
        for (int i = 0; i < input.length(); i++) 
        {
            String currentInput = input.substring(i, i + 1);
                                               
            encryptedStrings[i] = caesar( currentInput,
                                          (key.charAt(i) - A) * -1
                                        );
        }
              
        return join(encryptedStrings);
    }
}
