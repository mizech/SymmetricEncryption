
package encryption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymmetricEncryption
{
    private static final int COUNT_CHARS_ALPHABET = 26;
    private static final char A = 'A';
    
    // ---------- HELPER METHODS -- Start ----------------------------------
    
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
    
    private static String createVigenereString( 
            String input,
            String key,
            int decrypt ) {
        
        String[] encryptedStrings = new String[input.length()];
        
        for (int i = 0; i < input.length(); i++) 
        {
            String currentInput = input.substring(i, i + 1);
                                               
            encryptedStrings[i] = caesar( currentInput,
                                          (key.charAt(i) - A) * decrypt
                                        );
        }
              
        return join(encryptedStrings);
    }
    // ---------- HELPER METHODS -- End ----------------------------------
    
    // ---------- REQUIRED METHODS ---------------------------------------
    // Names, parameter, return values etc. are prescribed by the exercise
    //  description that way ...
    
    /**
     * Write a public, static method which has a boolean
     *  value as return. 
     *  The method has a return value of false if at least
     *  one char IS NOT between A and Z.
     *  In all other cases it return true.
     * 
     * @param input
     * @return 
     */
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
    
    /**
     * Shifts all chars of an text m for n positions n
     *  in the alphabet.
     *  In case the encrypted char is beyond the last
     *  char of the alphabet it is started from the 
     *  beginning again.
     * 
     *  c = m + n mod 26
     * 
     * Implement a validation check. 
     * 
     * @param input Text to encrypt.
     * @param shift Places to shift within the alphabet.
     * @return 
     */
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
    
    /**
     * ROT13 is a Caesar chiffre with the shift set fixed
     *  to 13 chars. Because of that one single method can
     *  be used for encryption AND decryption.
     * 
     *  c = rot13(m)
     *  m = rot13(c)
     * 
     * @param input
     * @return 
     */
    public static String rot13(String input) 
    { 
        return caesar(input, 13);
    }
    
    /**
     * Difference Vigenere to Caesar chipher: A keyword is needed
     *  for the encryption.
     * 
     *  In case the text to encrypt is longer as the keyword: Then
     *   the keyword is repeated until it has the length of the text.
     *  
     *  Each char m is shifted for k positions within the 
     *   alphabet.
     *   
     *   c = m + k mod 26
     * 
     *   EXAMPLE:
     *   m = GEHEIMNIS, k = AKEY
     *   m = GEHEIMNIS
     *   k = AKEYAKEYA
     *   c = GOLCIWRGS 
     *
     * Implement a validation check. 
     * 
     * @param input
     * @param key
     * @return 
     */
    public static String vigenereEncrypt(String input, String key)
    {
        validateInput(input);
        validateInput(key);
        
        key = createValidKey(key, input.length());
          
        return createVigenereString(input, key, 1);
    }

    public static String vigenereDecrypt(String input, String key) 
    {
        validateInput(input);
        validateInput(key);
        
        key = createValidKey(key, input.length());
         
        return createVigenereString(input, key, -1);
    }
}
