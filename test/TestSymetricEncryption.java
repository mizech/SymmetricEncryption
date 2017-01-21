/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import encryption.SymmetricEncryption;
import static java.lang.System.*;

public class TestSymetricEncryption
{
    public static void main(String[] args) 
    {
        out.println(SymmetricEncryption.isStringValid("ABCDEF"));
        out.println(SymmetricEncryption.isStringValid("ABC1DEF"));
        out.println(SymmetricEncryption.isStringValid("ABC DEF"));
        out.println(SymmetricEncryption.caesar("ABCXYZ", 1));
        out.println(SymmetricEncryption.caesar("HALLO", -3));
        
        String tmp = SymmetricEncryption.rot13("ABCXYZ");
        
        out.println(tmp);
        out.println(SymmetricEncryption.rot13(tmp));
       
        char demo = 'A';
        out.println(demo += 32);
        
        out.println("Vigenere");
        out.println(SymmetricEncryption.vigenereEncrypt("HALLO", "TELEFON"));
        out.println(SymmetricEncryption.vigenereDecrypt("AEWPT", "TELEFON"));
    }
}
