/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.*; //để sài gói API

import com.mycompany.baitaplon.api.Api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Tester {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //dòng cần thiết cho toàn bộ quá trình 
        Api api = new Api();
        Api.connectSql();
        Scanner scanner = new Scanner(System.in);
        int luaChon;
        QLHD qlhd = new QLHD();
//        QLMenu a = new QLMenu();
//        a.layDsMonSQL(1);
        //User interface in console
        do {
            System.out.println("Chao mung!! Hay chon cac lua chon sau: ");
            System.out.println("1. Xuat danh sach sanh cac sanh cuoi co trong he thong");
            System.out.println("2. Xuat danh sach sanh cac dich vu trong he thong");
            System.out.println("3. Xuat danh sach sanh cac menu co trong he thong");
            System.out.println("4. Nhap hoa don");
            System.out.println("5. Xuat toan bo hoa don");
            System.out.println("6. Xuat mot hoa don");
            System.out.println("7. Xoa hoa don"); //thiếu hàm xóa
            System.out.println("8. Xuat doanh thu theo thang");
            System.out.println("9. Xuat doanh thu theo thang");
            System.out.println("Nhap vao -1 de thoat");
            luaChon = scanner.nextInt();
            switch (luaChon) {
                case 1: {
                    QLSanhCuoi qls = new QLSanhCuoi();
                    qls.xuatSC();
                    break;
                }
                case 2: {
                    QLDV qldv = new QLDV();
                    qldv.xuatDsSQL();
                    break;
                }
                case 3: {
                    QLMenu qlmenu = new QLMenu();
                    //thiếu phần của Hậu
                    break;
                }
                case 4: {
                    qlhd.nhapHoaDon(scanner);
                    break;
                }
                case 5: {
                    qlhd.xuatHoaDonSQL();
                }
                case 6: {
                    try {
                        qlhd.xuatHoaDonSQL(scanner);
                    } catch (ParseException ex) {
                        System.err.println("Loi khi xuat hoa don");
                    }
                }
                case 7: {
                    //Thiếu xóa hóa đơn
                }
                case 8: {
                    System.out.println("Nhap vao thang: ");
                    int thang = scanner.nextInt();
                    if(thang > 0 || thang <=12)
                        qlhd.xuatDoanhThuThang(scanner.nextInt());
                    else
                        System.out.println("Thang khong ton tai");
                }
                case 9: {
                    System.out.println("Nhap vao quy: ");
                    int thang = scanner.nextInt();
                    if(thang > 0 || thang <=4)
                        qlhd.xuatDoanhThuQuy(scanner.nextInt());
                    else
                        System.out.println("Quy khong ton tai");
                }
                default: 
                    System.out.println("Lua chon khong ton tai");
            }
        } while(luaChon != -1);
        Api.disconnectSql();
    }
}
