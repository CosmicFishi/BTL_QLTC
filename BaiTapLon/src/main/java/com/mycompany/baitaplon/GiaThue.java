/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class GiaThue {
    private int giaThue;
    private String thoiDiem;
    private String Ngay;
    private boolean isDipLe;
    
    public GiaThue() {
        this.giaThue = 0;
        this.thoiDiem = "Thoi diem";
        this.Ngay = "ngay";
        this.isDipLe = false;
    }
    /**
     * @param gia :giá thuê
     * @param thoiDiem : thời điểm thuê (Sáng, trưa, chiều, tối)
     * @param ngayThue : Ngày thuê trong tuần
     * @param isDip : có phải dịp lễ hay không
     */
    public GiaThue(int gia, String thoiDiem, String ngayThue, boolean isDip) {
        this.giaThue = gia;
        this.thoiDiem = thoiDiem;
        this.Ngay = ngayThue;
        this.isDipLe = isDip;
    }
    
    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao gia thue: ");
        this.giaThue = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhap vao thoi diem thue: (Sang, trua, chieu, toi) ");
        this.thoiDiem = scanner.nextLine();
        System.out.println("Nhap vao ngay thue: ");
        this.Ngay = scanner.nextLine();
        System.out.println("Dip le ? 0 hoac 1");
        this.isDipLe = scanner.nextInt() == 1;
    }

    @Override
    public String toString() {
        return String.format("Gia thue: %d\nThoi diem: %s\nNgay thue: %s\nDip le: %b\n",
                this.giaThue,this.thoiDiem,this.Ngay,this.isDipLe);
    }
    

    /**
     * @return the giaThue
     */
    public int getGiaThue() {
        return giaThue;
    }

    /**
     * @param giaThue the giaThue to set
     */
    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    /**
     * @return the thoiDiem
     */
    public String getThoiDiem() {
        return thoiDiem;
    }

    /**
     * @param thoiDiem the thoiDiem to set
     */
    public void setThoiDiem(String thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    /**
     * @return the Ngay
     */
    public String getNgay() {
        return Ngay;
    }

    /**
     * @param Ngay the Ngay to set
     */
    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    /**
     * @return the isDipLe
     */
    public boolean isIsDipLe() {
        return isDipLe;
    }

    /**
     * @param isDipLe the isDipLe to set
     */
    public void setIsDipLe(boolean isDipLe) {
        this.isDipLe = isDipLe;
    }
    
    
}
