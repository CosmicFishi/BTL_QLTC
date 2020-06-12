/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HoaDonThue {
    private int maHD;
    private String tenBT;
    private String tenSanh;
    private GiaThue giaThueSanh;
    private String thoiDiemThue;
    private int soBanThue;
    private Date ngayThue;
    private QLMenu menu;
    private int giaMenu;
    private QLDV dichVu;
    private int giaDichVu;
    private int giaThue; 
    
    private List<Integer> luaChon = new ArrayList<>();
    
    {
        this.giaThue = giaThueSanh.getGiaThue();
    }
    
    public HoaDonThue(){
    
    }
    
    /**
     * Của Admin sài
     * @param maHD
     * @param tenBT
     * @param tenSanh
     * @param soBanThue
     * @param giaThue
     * @param thoiDiem
     * @param ngay
     * @param menu
     * @param dv 
     */
    public HoaDonThue(int maHD, String tenBT, String tenSanh, int soBanThue, GiaThue giaThue, String thoiDiem, Date ngay, QLMenu menu, QLDV dv ) {
        this.maHD = maHD;
        this.tenBT = tenBT;
        this.tenSanh = tenSanh;
        this.soBanThue = soBanThue;
        this.giaThueSanh = giaThue;
        this.thoiDiemThue = thoiDiem;
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
    
    
    


    /**
     * phần nhập của người dùng
     * @param s 
     */
    public void nhap(Scanner s) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhap vao ngay thang nam: ");
        String date = s.nextLine();
        try {
            this.ngayThue = f.parse(date);
        } catch (ParseException ex) {
            System.err.println("Error at ngay thue");
        }
        System.out.println("Ban co muon them dich vu khong? (Y | N)");
        if("y".equals(s.nextLine().toLowerCase())) {
            System.out.println("Danh sach cac dich vu: ");
                this.dichVu.xuatDsSQL();
                luaChon = this.dichVu.nhapLuaChon(s, maHD);
        } else {
            System.out.println("ok then");
        }
    } 
    public void capNhat() {};
    public void xoa() {};
}
