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

        String nhap = "";
        int luaChon = 0;

        QLHD qlhd = new QLHD();
        QLDV qldv = new QLDV();
        QLSanhCuoi qls = new QLSanhCuoi();
        DanhSachThucAn dsAn = new DanhSachThucAn();
        DanhSachThucUong dsUong = new DanhSachThucUong();

        //User interface in console
        do {
            System.out.println("Chao mung!! Hay chon cac lua chon sau: ");
            System.out.println("1. Xuat danh sach sanh cac sanh cuoi co trong he thong");
            System.out.println("2. Xuat danh sach sanh cac dich vu trong he thong");
            System.out.println("3. Xuat danh sach sanh cac menu co trong he thong");
            System.out.println("4. Nhap hoa don");
            System.out.println("5. Xuat toan bo hoa don");
            System.out.println("6. Xuat mot hoa don");
            System.out.println("7. Xoa hoa don");
            System.out.println("8. Xuat doanh thu theo thang");
            System.out.println("9. Xuat doanh thu theo quy");
            System.out.print("Nhap vao -1 de thoat: ");
            nhap = scanner.nextLine();
            try {
                luaChon = Integer.parseInt(nhap);
            } catch (NumberFormatException e) {
                luaChon = 0;
            }
            switch (luaChon) {
                case -1:
                    break;
                case 1: {
                    qls.xuatSC();
                    break;
                }
                case 2: {
                    qldv.xuatDsSQL();
                    break;
                }
                case 3: {
                    dsAn.xuatThucAnSql();
                    dsUong.xuatThucUongSql();
                    break;
                }
                case 4: {
                    try {
                        qlhd.nhapHoaDon(scanner);
                        break;
                    } catch (Error e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    qlhd.xuatHoaDonSQL();
                    break;
                }
                case 6: {
                    System.out.print("Nhap ma hoa don can xuat: ");
                    qlhd.xuatHoaDonSQL(scanner.nextInt());
                    scanner.nextLine();
                    break;
                }
                
                case 7: {
                    System.out.print("Nhap ma hoa don muon xoa: ");
                    qlhd.xoaHoaDonSQL(scanner.nextInt());
                    scanner.nextLine();
                    break;
                }

                case 8: {
                    System.out.print("Nhap vao thang: ");
                    int thang = scanner.nextInt();
                    scanner.nextLine();
                    if (thang > 0 && thang <= 12)
                        qlhd.xuatDoanhThuThang(thang);
                    else 
                        System.out.println("Thang khong ton tai");
                    break;
                }
                case 9: {
                    System.out.print("Nhap vao quy: ");
                    int quy = scanner.nextInt();
                    scanner.nextLine();
                    if (quy > 0 && quy <= 4) 
                        qlhd.xuatDoanhThuQuy(quy);
                    else 
                        System.out.println("Quy khong ton tai");
                    break;
                }
                
                default:
                    System.out.println("Lua chon khong ton tai");
            }
        } while (luaChon != -1);
        Api.disconnectSql();
    }
}
