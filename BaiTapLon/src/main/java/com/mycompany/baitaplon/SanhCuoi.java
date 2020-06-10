/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class SanhCuoi {

    private int dem = 0;
    private String maSC;
    private String tenSanh;
    private int viTriSanh;
    private int sucChua = 0;
    private int giaThue;

    /**
     * Khởi tạo SanhCuoi rỗng
     */
    public SanhCuoi(String s) {
    }
    public SanhCuoi() {
        this.maSC = String.format("S%03d", dem);
        this.tenSanh = "TenRong";
        this.viTriSanh = -1;
        this.sucChua = 0;
    }

    /**
     * Nhập vào tên, vị trí sảnh và sức chứa
     *
     * @param ten : String tên
     * @param vt : int vị trí
     * @param sc : int sức chứa4
     */
    public SanhCuoi(String ten, int vt, int sc, int giaThue) {
        this.maSC = String.format("S%03d", dem);
        this.tenSanh = ten;
        this.viTriSanh = vt;
        this.sucChua = sc;
        this.giaThue = giaThue;
    }
    public SanhCuoi(String maSC, String ten, int vt, int sc, int giaThue) {
        this.maSC = maSC;
        this.tenSanh = ten;
        this.viTriSanh = vt;
        this.sucChua = sc;
        this.giaThue = giaThue;
    }

    /**
     * Xuất sảnh cưới mã, tên, vị trí, sức chứa, giá thuê
     */
    public void xuat() {
        System.out.printf("%-7s|%-20s|%-5d|%-6d|%-15d",
                this.maSC, this.tenSanh, this.viTriSanh, this.sucChua, this.giaThue);
    }

    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao ten sanh: ");
        this.tenSanh = scanner.nextLine();
        System.out.println("Nhap vao vi tri sanh:  ");
        this.viTriSanh = scanner.nextInt();
        System.out.println("Nhap vao suc chua: ");
        this.sucChua = scanner.nextInt();
        System.out.println("Nhap gia thue: ");
        this.giaThue = scanner.nextInt();
    }

    @Override
    public String toString() {
        return String.format("\'%s\', \'%s\', %d, %d, %d", this.maSC, this.tenSanh, this.viTriSanh,
                this.sucChua, this.getGiaThue());
    }
    // <editor-fold defaultstate="collapsed" desc="Get and set">

    /**
     * @return the maSC
     */
    public String getMaSC() {
        return maSC;
    }

    /**
     * @param maSC the maSC to set
     */
    public void setMaSC(String maSC) {
        this.maSC = maSC;
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

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }
    // </editor-fold>
}
