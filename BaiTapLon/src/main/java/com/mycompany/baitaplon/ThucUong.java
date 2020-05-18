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
public class ThucUong extends Menu {
    private int maThucUong = ++dem;
    private static int dem;
    private String hangSX;

    public ThucUong() {
        super();
        this.hangSX = "nha san xuat";
    }
    public ThucUong(String ten, int gia, String nhaSX) {
        super(ten,gia);
        this.hangSX = nhaSX;
    }
    
    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner); 
        scanner.nextLine();
        System.out.println("Nhap vao hang san xuat: ");
        this.hangSX = scanner.nextLine();
    }

    @Override
    public String toString() {
        return String.format("Ten: %s\nGia: %d\nMa thuc uong: %d\nHang san xuat: %s\n",
                this.tenThucAn, this.gia, this.maThucUong, this.hangSX);
    }
}
