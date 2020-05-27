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
public abstract class Menu {
    protected int[] dsThucUong;
    protected int[] slThucUong;
    private int[] dsThucAn;
    private int[] slThucAn;
    
    public Menu() {
    }
    
    public Menu(int[] dsAn,int[] slAn, int[] dsUong, int[] slUong) {
        this.dsThucUong = dsUong;
        this.slThucUong = slUong;
        this.dsThucAn = dsAn;
        this.slThucAn = slAn;
    }
    
//    public void nhap(Scanner scanner) {
//        System.out.println("Nhap vao ten thuc an: ");
//        this.tenThucAn = scanner.nextLine();
//        System.out.println("Nhap vao gia: ");
//        this.gia = scanner.nextInt();
//    }

    @Override
    public String toString() {
        return String.format("");
    }
    
    
}
