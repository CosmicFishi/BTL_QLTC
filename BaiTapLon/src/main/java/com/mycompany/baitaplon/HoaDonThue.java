/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class HoaDonThue {
    private int maHD;
    private String tenBuoiTiec;
    private int soBanThue;
    private Date ngayThue;
    
    private GiaThue giaThueSanh;
    private SanhCuoi sanhCuoi;
    private QLMenu menu;
    private QLDV dichVu;
    
    private int giaSanh;
    private int giaMenu;
    private int giaDichVu;
    private int giaHoaDon; 
    
    private List<Integer> luaChonDv = new ArrayList<>();
    
    {
        this.giaHoaDon = giaThueSanh.getGiaThue();
    }
    
    public HoaDonThue(){}
    
    /**
     * Của Admin sài
     * @param maHD
     * @param tenBT
     * @param sanhCuoi
     * @param soBanThue
     * @param giaThue
     * @param ngay
     * @param menu
     * @param dv 
     */
    public HoaDonThue(int maHD, String tenBT, SanhCuoi sanhCuoi, int soBanThue, GiaThue giaThue, Date ngay, QLMenu menu, QLDV dv ) {
        this.maHD = maHD;
        this.tenBuoiTiec = tenBT;
        this.sanhCuoi = sanhCuoi;
        this.soBanThue = soBanThue;
        this.giaThueSanh = giaThue;
        this.ngayThue = ngay;
        this.menu = menu;
        this.dichVu = dv;
        this.giaMenu = 0; // cần thêm câu query
        this.giaDichVu = 0;
    }
    
//    @Override
//    public String toString() {
//        return String.format("Ma hoa don: %s\nTen bua tiec: %s\nTen sanh: %s\nGia thue: %d\nThoi diem: %s\nNgay: %s\n", args);
//    }
    public void tinhTien(){
        this.giaMenu = this.menu.tinhGiaDs();
        this.giaSanh = this.sanhCuoi.getGiaThue() + this.giaThueSanh.getGiaThue();
        this.giaDichVu = this.dichVu.layTongTienDVSQL(this.maHD);
        this.giaHoaDon = this.giaMenu+ this.giaDichVu + this.giaSanh;
    }
    /**
     * phần nhập của người dùng
     * @param s 
     */
    public void nhap(Scanner s) {
        
        System.out.println("Ban co muon them dich vu khong? (Y | N)");
        if("y".equals(s.nextLine().toLowerCase())) {
            System.out.println("Danh sach cac dich vu: ");
                this.dichVu.xuatDsSQL();
                luaChonDv = this.dichVu.nhapLuaChon(s, maHD);
        } else {
            System.out.println("ok then");
        }
        tinhTien();
    } 
}
