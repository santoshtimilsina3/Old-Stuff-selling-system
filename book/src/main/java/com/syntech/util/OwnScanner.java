/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.util.Scanner;

/**
 *
 * @author sagar
 */
public class OwnScanner {

    private static Scanner scanner=null;
    
    public static Scanner scan() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
