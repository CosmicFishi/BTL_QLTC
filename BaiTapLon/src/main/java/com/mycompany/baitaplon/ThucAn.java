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
    private static int dem = 12;
   
    public ThucAn() {
        super();
        this.ma = ++dem;
        this.isAnChay=false;
    }
    
    public ThucAn( String ten, int gia, boolean isAnChay) {
        super(++dem, ten, gia);
        this.isAnChay = isAnChay;
    }
    public String xuat(){
        return String.format("Ma: %-3d Ten: %-15s Gia: %-9d Is Chay: %-7b", this.getMa(), this.getTen(), this.getGia(), this.isAnChay);
    }
    public void nhap(Scanner scanner) {
        System.out.println("-------------------------------------------------");
        System.out.println("Nhap thuc an: ");
        super.nhap(scanner);
        System.out.print("isChay (1 hoac 0): ");
        this.setIsAnChay(scanner.nextInt() == 1);
        scanner.nextLine();
        //trả về true nếu là 1, false nếu là 0
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
