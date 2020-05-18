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
public class ThucAn extends Menu {
    private int maThucAn = ++dem;
    private static int dem;
    private boolean isAnChay;

    public ThucAn() {
        super();
        this.isAnChay = true;
    }
    public ThucAn(String ten, int gia, boolean isAnChay) {
        super(ten, gia);
        this.isAnChay = isAnChay;
    }
    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner);
        System.out.println("Co an chay duoc: 1 hoac 0 ");
        this.isAnChay = scanner.nextInt() == 1; //trả về true nếu là 1, false nếu là 0
    }

    @Override
    public String toString() {
        return String.format("Ten: %s\nGia: %d\nMa thuc an: %d\nAn chay duoc: %b\n",
                this.tenThucAn, this.gia, this.maThucAn,this.isAnChay);
    }
    
    
}
