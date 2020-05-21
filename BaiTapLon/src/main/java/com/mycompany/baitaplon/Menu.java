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
    protected String tenThucAn;
    protected int gia;

    public Menu() {
        this.tenThucAn = "thuc an";
        this.gia = 0;
    }
    public Menu(String ten, int gia) {
        this.tenThucAn = ten;
        this.gia = gia;
    }
    
    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao ten thuc an: ");
        this.tenThucAn = scanner.nextLine();
        System.out.println("Nhap vao gia: ");
        this.gia = scanner.nextInt();
    }

    @Override
    public String toString() {
        return String.format("Ten: %s\nGia: %d\n",this.tenThucAn, this.gia);
    }
    
    
    /**
     * @return the tenThucAn
     */
    public String getTenThucAn() {
        return tenThucAn;
    }

    /**
     * @param tenThucAn the tenThucAn to set
     */
    public void setTenThucAn(String tenThucAn) {
        this.tenThucAn = tenThucAn;
    }

    /**
     * @return the gia
     */
    public int getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(int gia) {
        this.gia = gia;
    }
}
