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
public class ThucUong extends DoAnUong {
    private String hangSX;

    public ThucUong() {
        super();
    }
    public ThucUong(int ma, String ten, int gia, String nhaSX) {
        super(ma, ten, gia);
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
        String kq = super.toString();
        return String.format(kq + ", %s", this.hangSX);
    }
}
