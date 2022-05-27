/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author sagar
 */
public class Bycrypt {
    public  static String hashString(String unHashedPassword){
                String bcryptHashString = BCrypt.withDefaults().hashToString(12, unHashedPassword.toCharArray());
                  
                return bcryptHashString ;

    }
    public static boolean isHashingMatched(String password,String bcryptHashString){
        
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        return result.verified;
    }
    
}
