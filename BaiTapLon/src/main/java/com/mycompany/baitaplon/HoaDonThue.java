/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.sql.SQLException;
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
    private static int dem =0;//
    private int maHD;//
    private String tenBuoiTiec;
    private int soBanThue;
    private Date ngayThue;
    
    private QLSanhCuoi qlSanh = new QLSanhCuoi();
    private GiaThue giaThueSanh = new GiaThue();
    private SanhCuoi sanhCuoi;
    private QLMenu DSmenu = new QLMenu();
    private QLDV dichVu = new QLDV();
    
    private int giaSanh;
    private int giaMenu;
    private int giaDichVu;
    private int giaHoaDon; 
    
    private List<Integer> luaChonDv = new ArrayList<>();
    
    {
        this.maHD = ++dem;
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
        this.DSmenu = menu;
        this.dichVu = dv;
        this.giaMenu = 0; // cần thêm câu query
        this.giaDichVu = 0;
    }
    
//    @Override
//    public String toString() {
//        return String.format("Ma hoa don: %s\nTen bua tiec: %s\nTen sanh: %s\nGia thue: %d\nThoi diem: %s\nNgay: %s\n", args);
//    }
    public void tinhTien(){
        this.giaMenu = this.DSmenu.tinhGiaDs();
        this.giaSanh = this.sanhCuoi.getGiaThue() + this.giaThueSanh.getGiaThue();
        this.giaDichVu = this.dichVu.layTongTienDVSQL(this.maHD);
        this.giaHoaDon = this.giaMenu+ this.giaDichVu + this.giaSanh;
    }
    /**
     * phần nhập của người dùng
     * @param s 
     */
    public void nhap(Scanner s) {
        System.out.print("Nhap ten buoi tiec: ");
        this.tenBuoiTiec = s.nextLine();
        System.out.print("Nhap so ban thue: ");
        this.soBanThue = Integer.parseInt(s.nextLine());
        System.out.print("Nhap thoi diem thue: ");
        this.giaThueSanh.nhap(s);
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
                luaChonDv = this.dichVu.nhapLuaChon(s, maHD);
        } else {
            System.out.println("ok then");
        }
        try {
            this.DSmenu.chon(s);
        } catch (SQLException ex) {
            System.err.println("Loi nhap menu.");
        }
        this.sanhCuoi=qlSanh.taoScFromSQL(s);
        tinhTien();
    } 
    /**
     * (admin)
     * Phần xuất của riêng admin
     */
    public void xuat() {
        System.out.printf("Ma hoa don: %d\n Ten bua tiec: %s\nSo ban thue: %d\n", this.maHD, this.tenBuoiTiec, this.soBanThue);
        this.sanhCuoi.xuat();
        this.DSmenu.xuat();
        this.dichVu.xuat();
        System.out.printf("Gia thue sanh: %d\nGia menu: %d\nGia dich vu: %d\nTong gia cua hoa don: %d\n", 
                this.giaSanh, this.giaMenu, this.giaDichVu, this.giaHoaDon);
        
    }
    public void xuatSQL() {
        this.dichVu.xuatLuaChonTuSQL(this.maHD);
    }
    public void capNhat() {};
    public void xoa() {
        this.dichVu.xoaLuaChonSQL(this.maHD);
    };
}
