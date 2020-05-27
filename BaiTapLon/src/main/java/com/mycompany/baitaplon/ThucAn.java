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
public class ThucAn extends DoAnUong{
    private boolean isAnChay;
    
    public ThucAn() {
        super();
        this.isAnChay=false;
    }
    
    public ThucAn( int ma, String ten, int gia, boolean isAnChay) {
        super(ma, ten, gia);
        this.isAnChay = isAnChay;
    }
    public void nhap(Scanner scanner) {
        super.nhap(scanner);
        System.out.println("Co an chay duoc: 1 hoac 0 ");
        this.setIsAnChay(scanner.nextInt() == 1); //trả về true nếu là 1, false nếu là 0
    }

    @Override
    public String toString() {
        String kq = super.toString();
        return String.format(kq+", %b", this.isIsAnChay());
    }

    public boolean isIsAnChay() {
        return isAnChay;
    }

    public void setIsAnChay(boolean isAnChay) {
        this.isAnChay = isAnChay;
    }
}
