/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

/**
 *
 * @author Admin
 */
public class test {
    public void nhap(){
        
    }
    public static void main(String[] args) {
        try {
            int a = 10;
            CheckValue.checkSoAm(a);
            
            String day ="20/13/2020";
            CheckValue.checkDay(day);
            
            String firstName = "Nhau ";
            CheckValue.checkName(firstName);
             if ( CheckValue.firstName(firstName) );
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
