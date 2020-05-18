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
public class DichVu {
    private String maDV;
    private String tenDV;
    private int giaDV;

    public DichVu() {
        this.maDV = "ma dich vu";
        this.tenDV = "ten";
        this.giaDV = 0;
    }
    public DichVu(String ma, String ten, int gia) {
        this.maDV = ma;
        this.tenDV = ten;
        this.giaDV = gia;
    }
    
    
    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao ma dich vu: ");
        this.setMaDV(scanner.nextLine());
        System.out.println("Nhap vao ten dich vu: ");
        this.setTenDV(scanner.nextLine());
        System.out.println("Nhap vao gia dich vu: ");
        this.setGiaDV(scanner.nextInt());
    }

    @Override
    public String toString() {
        return String.format("Ma dich vu: %s\nTen dich vu: %s\nGia dich vu: %s\n", this.getMaDV(), this.getTenDV(), this.getGiaDV());
    }

    /**
     * @return the maDV
     */
    public String getMaDV() {
        return maDV;
    }

    /**
     * @param maDV the maDV to set
     */
    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    /**
     * @return the tenDV
     */
    public String getTenDV() {
        return tenDV;
    }

    /**
     * @param tenDV the tenDV to set
     */
    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    /**
     * @return the giaDV
     */
    public int getGiaDV() {
        return giaDV;
    }

    /**
     * @param giaDV the giaDV to set
     */
    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }
    
}
