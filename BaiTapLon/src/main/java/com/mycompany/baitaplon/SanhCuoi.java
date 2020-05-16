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
public class SanhCuoi {
    private int maSC = ++Dem ;
    private static int Dem;
    private String tenSanh;
    private int viTriSanh;
    private int sucChua = 0;
    
    public SanhCuoi() {
        this.tenSanh = "TenRong";
        this.viTriSanh = -1;
        this.sucChua = 0;
    }
    public SanhCuoi(String ten, int vt, int sc) {
        this.tenSanh = ten;
        this.viTriSanh = vt;
        this.sucChua = sc;
    }

    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao ten sanh: ");
        this.tenSanh = scanner.nextLine();
        System.out.println("Nhap vao vi tri sanh:  ");
        this.viTriSanh = scanner.nextInt();
        System.out.println("Nhap vao suc chua: ");
        this.sucChua = scanner.nextInt();
    }
    
    @Override
    public String toString() {
        return String.format("Ma sanh: %s\nTen sanh: %s\nVi tri sanh: %d\n"
                + "Suc chua: %d\n", "S"+ this.maSC,this.tenSanh,this.viTriSanh,
                this.sucChua);
    }
    
    /**
     * @return the maSC
     */
    public int getMaSC() {
        return maSC;
    }

    /**
     * @param maSC the maSC to set
     */
    public void setMaSC(int maSC) {
        this.maSC = maSC;
    }

    /**
     * @return the Dem
     */
    public static int getDem() {
        return Dem;
    }

    /**
     * @param aDem the Dem to set
     */
    public static void setDem(int aDem) {
        Dem = aDem;
    }

    /**
     * @return the tenSanh
     */
    public String getTenSanh() {
        return tenSanh;
    }

    /**
     * @param tenSanh the tenSanh to set
     */
    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    /**
     * @return the viTriSanh
     */
    public int getViTriSanh() {
        return viTriSanh;
    }

    /**
     * @param viTriSanh the viTriSanh to set
     */
    public void setViTriSanh(int viTriSanh) {
        this.viTriSanh = viTriSanh;
    }

    /**
     * @return the sucChua
     */
    public int getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }
    
}
