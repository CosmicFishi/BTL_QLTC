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
public class DvThueCS extends DichVu {
    private String tenCS;
    private int soLuongBH;

    public DvThueCS() {
        super();
        this.tenCS = "ten ca si";
        this.soLuongBH = 0;
    }
    
    /**
     * 
     * @param maDV: Mã dịch vụ
     * @param ten: Tên dịch vụ
     * @param gia: Giá dịch vụ
     * @param tenCS: Tên ca sĩ
     * @param soLuong: Số lượng bài hát
     */
    public DvThueCS(String maDV, String ten, int gia, String tenCS, int soLuong) {
        super(maDV,ten,gia);
        this.tenCS = tenCS;
        this.soLuongBH = soLuong;
    }
    
    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner);
        scanner.nextLine();
        System.out.println("Nhap vao ten CS: ");
        this.tenCS = scanner.nextLine();
        System.out.println("Nhap vao so luong bai hat: ");
        this.soLuongBH = scanner.nextInt();
    }

    
    @Override
    public String toString() {
        return super.toString();
//        return String.format("%sTen ca si: %s\nSo luong bai hat: %d\n",
//                super.toString(),this.tenCS,this.soLuongBH);
    }
    @Override
    public String xuat() {
//        System.out.printf("\'%s\',\'%s\',%d", this.getMaDV(),this.getTenDV(),this.getGiaDV());
        return String.format("\'%s\',\'%s\', %d",this.getMaDV(), this.tenCS,this.soLuongBH);
    }
    
    /**
     * @return the thongTinCS
     */
    public String getTenCS() {
        return tenCS;
    }

    /**
     * @param thongTinCS the thongTinCS to set
     */
    public void setTenCS(String thongTinCS) {
        this.tenCS = thongTinCS;
    }

    /**
     * @return the soLuongCS
     */
    public int getSoLuongBH() {
        return soLuongBH;
    }

    /**
     * @param soLuongBH the soLuongCS to set
     */
    public void setSoLuongBH(int soLuongBH) {
        this.soLuongBH = soLuongBH;
    }

    /**
     * Hàm xuất để truy xuất mysql
     */
    
    
    
}
