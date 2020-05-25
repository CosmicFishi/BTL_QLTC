/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonThue {
    private String maHD;
    private String tenBT;
    private String tenSanh;
    private int giaThueSanh;
    private String thoiDiemThue;
    private Date ngayThue;
    private QLMenu menu;
    private int giaMenu;
    private QLDV dichVu;
    private int giaDichVu;
    
    public HoaDonThue(){}
    
    public HoaDonThue(String maHD, String tenBT, String tenSanh, int giaThue, String thoiDiem, Date ngay, QLMenu menu, QLDV dv ) {
        this.maHD = maHD;
        this.tenBT = tenBT;
        this.tenSanh = tenSanh;
        this.giaThueSanh = giaThue;
        this.thoiDiemThue = thoiDiem;
        this.ngayThue = ngay;
        this.menu = menu;
        this.dichVu = dv;
        this.giaMenu = 0; // cần thêm câu query
        this.giaDichVu = 0;
    }

    @Override
    public String toString() {
        return String.format("Ma hoa don: %s\nTen bua tiec: %s\nTen sanh: %s\nGia thue: %d\nThoi diem: %s\nNgay: %s\n", args);
    }
    
    
    
}
