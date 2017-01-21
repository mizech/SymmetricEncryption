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
        out.println("Result isValid: "
                + SymmetricEncryption.isStringValid("ABCDEF"));
        out.println("Result isValid: " 
                + SymmetricEncryption.isStringValid("ABC1DEF") + "\n");
        
        out.println("\nCaesar");
        out.println(SymmetricEncryption.caesar("ABCXYZ", 1));
        out.println(SymmetricEncryption.caesar("HALLO", -3));
        
        String tmp = SymmetricEncryption.rot13("ABCXYZ");
        
        out.println("\nROT13: " + tmp);
        out.println("ROT13: " + SymmetricEncryption.rot13(tmp));
        
        out.println("\nVigenere");
        out.println(SymmetricEncryption.vigenereEncrypt("HALLO", "TELEFON"));
        out.println(SymmetricEncryption.vigenereDecrypt("AEWPT", "TELEFON"));
        
        /*  ---------- RESULTS -------------------------------------------
            Result isValid: true
            Result isValid: false

            Caesar
            BCDYZA
            EXIIL

            ROT13: NOPKLM
            ROT13: ABCXYZ

            Vigenere
            AEWPT
            HALLO
        */
    }
}
