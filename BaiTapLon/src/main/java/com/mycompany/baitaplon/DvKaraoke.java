/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DvKaraoke extends DichVu {
    private String KhoangTG;

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner); 
        System.out.println("Nhap vao khoang thoi gian (hh:mm:ss)");
        this.KhoangTG = scanner.nextLine();
    }
    
    
    
}
