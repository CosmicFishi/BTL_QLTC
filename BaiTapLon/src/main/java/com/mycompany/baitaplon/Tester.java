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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
//Thử dịch vụ của Dũng 
//        DichVu d1 = new DvKaraoke(1, "idk", 10, "10 tieng");
//        d1.addSQL();
//        DichVu d2 = new DvThueCS(2, "idkw", 30,"Dung", 5);
//        d2.addSQL();
////        d1.readSQLShow();
////        d2.readSQLShow();
//        QLDV ql = new QLDV();
//        ql.xuatDsSQL();
//        //ql.nhapLuaChon(scanner, 1);
//        List<Integer> luaChon = new ArrayList<>();
////        luaChon.add(1);
//        luaChon.add(2);
//        ql.nhapLuaChonSQL(1,luaChon);
//        ql.xuatLuaChonTuSQL(1);
//        //ql.xuatDsSQL();
//        //ql.traCuuSQL("idk");
//        ql.xoaLuaChonSQL(1);
//        d1.deleteSQL();
//        d2.deleteSQL();
//thử đồ ăn, thức uổng của Hậu
        try {
            DanhSachThucAn lq = new DanhSachThucAn();
            System.out.println("1. xuat tat ca ThucAn");
            System.out.println("2. them ThucAn vao SQL");
            System.out.println("3. xoa ThucAn trong SQL");
            System.out.println("4. chinh sua ThucAn trong SQL");
            System.out.println("5. tim ThucAn theo TEN || MA trong SQL");
            System.out.println("6. them ThucAn tu Sql");
            System.out.println("?. so khac de thoat.");
            while (true) {
                System.out.print("Chon : ");
                int nhap = Integer.parseInt(scanner.nextLine());
                if(nhap > 6) break;
                switch (nhap) {
                    case 1:
                        lq.xuatThucAn();
                        break;
                    case 2:
                        lq.themThucAn(scanner);
                        break;
                    case 3:
                        lq.xoaThucAn(scanner);
                        break;
                    case 4:
                        lq.updateThucAn(scanner);
                        break;
                    case 5:
                        lq.timThucAn(scanner);
                        break;
                    case 6:
                        lq.themTuSql(scanner);
                        lq.xuat();
                        break;
                    default:
                        System.out.println("Chọn sai chọn lại");
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } finally {
            Api.disconnectSql();
        }
        
        Api.disconnectSql();
// <editor-fold defaultstate="collapsed" desc="Đã test">
//        try {
//            Scanner scanner = new Scanner(System.in);
//            QLMenu qlmenu = new QLMenu();
//            qlmenu.chon(scanner);
//            qlmenu.xuat();
//            
//            DanhSachThucAn dsAn1 = new DanhSachThucAn();
//            DanhSachThucUong dsUong1 = new DanhSachThucUong();
//            dsUong1.themTuSql(scanner);
//            dsAn1.themTuSql(scanner);
//            
//            Menu menu1 = new Menu(dsAn1, dsUong1);
//            QLMenu qlmenu = new QLMenu();
//            qlmenu.them(menu1);
//            qlmenu.xuat();
/**
            System.out.println("1. xuat tat ca SC");
            System.out.println("2. them SC vao SQL");
            System.out.println("3. xoa SC trong SQL");
            System.out.println("4. chinh sua SC trong SQL");
            System.out.println("5. tim SC theo TEN || MA trong SQL");
            System.out.println("6. tim SC theo VT || Sức Chứa trong SQL");
            System.out.println("?. so khac de thoat.");
            while (true) {
                System.out.print("Chon : ");
                int nhap = Integer.parseInt(scanner.nextLine());
                if(nhap > 6) break;
                switch (nhap) {
                    case 1:
                        lq.xuatSC();
                        break;
                    case 2:
                        lq.themSC(scanner);
                        break;
                    case 3:
                        lq.xoaSC(scanner);
                        break;
                    case 4:
                        lq.capNhatSC(scanner);
                        break;
                    case 5:
                        lq.timSCTenMa(scanner);
                        break;
                    case 6:
                        lq.timSCViChua(scanner);
                        break;
                    default:
                        System.out.println("Chọn sai chọn lại");
                        break;
                }
            }
            **/
//        } catch (NumberFormatException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            Api.disconnectSql();
//            System.out.println("Disconnected to mysql database.");
//        }
//        ThucUong uong1 = new ThucUong("coca", 12000, "My");
//        ThucUong uong2 = new ThucUong("sting", 10000, "VN");
//        ThucAn an1 = new ThucAn("lau", 25000, false);
//        dsAn1.them(scanner);
//        dsAn1.them(scanner);
//        dsAn1.them(scanner);
//        dsAn1.them(an1, 5);
//        dsUong1.them(uong1, 10);
//        dsUong1.them(uong2, 5);
//        dsUong1.them(scanner);
//        try {
//            Api api = new Api();
//            Api.connectSql();
//        } catch (Exception e) {
//            System.err.println("error create Api.");
//        }
//
//        try {
//            //SanhCuoi a= new SanhCuoi("S011","Sảnh hoang vu", 4, 450, 45000000);
//            //DanhSachSanh ds = new DanhSachSanh();
////            ds.them(a);
////            ds.xuat();
////            System.out.println("==============================");
//            Scanner scanner = new Scanner(System.in);
////            ds.xoa(scanner);
////            ds.xuat();
//            //System.out.println("==============================");
//            //ds.capNhat(scanner);
//            //ds.xuat();
//            ThucAn ta = new ThucAn(11, "bun man", 25000, true);
//            //ta.nhap(scanner);
//            DanhSachThucAn ds = new DanhSachThucAn();
//            //ds.xoa(scanner);
//            //ds.them(ta);
//            ds.updateThucAn(scanner);
//            ds.xuat();
//            
//            
//        } catch (SQLException e) {
//            System.err.print(e.getMessage());
//        } finally {
//            Api.disconnectSql();
//        }
//        DichVu d = new DichVu();
//        System.out.println();
//Thử sảnh cưới
//        SanhCuoi s1 = new SanhCuoi("abc", 1, 100);
//        System.out.print(s1);
//        SanhCuoi s2 = new SanhCuoi();
//        s2.nhap(s);
//        System.out.println(s2);
//        
//        DanhSachSanh dsSanh = new DanhSachSanh();
//        System.out.println("=== danh sach sanh ===" );
//        dsSanh.them(s1);
//        dsSanh.them(s2);
//        dsSanh.xuat();
//        
//        System.out.println("=== tim kiem ===");
//        ArrayList<SanhCuoi> DanhSachSanh = dsSanh.traCuu("def");
//        DanhSachSanh.forEach((SanhCuoi sc) -> System.out.print(sc));
//Thử menu
//        Menu m1 = new ThucAn("abc", 500, true);
//        Menu m2 = new ThucAn("def",600, false);
//        Menu m3 = new ThucUong("idk", 20, "my mom");
//        Menu m4 = new ThucUong("asda",10, "ur mom");
//        
//        QLMenu ql = new QLMenu();
//        ql.them(m1);
//        ql.them(m4);
//        ql.them(m2);
//        ql.xuat();
//        System.out.println("====");
//        ql.xoa(m2);
//        ql.xuat();
//        System.out.println("=== Tim Kiem ===");
//        ArrayList kq = ql.traCuu("abc");
//        kq.forEach(m -> System.out.println(m));
//Thử giá thuê
//       GiaThue g1 = new GiaThue(100, "Sang", "Bay, Chu Nhat", false);
//       GiaThue g2 = new GiaThue();
//       g2.nhap(s);
//       System.out.println("===");
//       DanhSachGiaThue ds = new DanhSachGiaThue();
//       ds.them(g1);
//       ds.them(g2);
//       ds.xuat();
//Thử dịch vụ
//        DichVu d1 = new DvKaraoke("01","hat",100,"30 phut");
//        DichVu d2 = new DvThueCS("02","Thue",200,"dung",5);
//        System.out.println(d1);
//        System.out.println(d2);
//        System.out.println("===");
//        QLDV ql = new QLDV();
//        ql.them(d1);
//        ql.them(d2);
//        ql.xuat();
//        System.out.println("=== tra cuu ===");
//        ArrayList<DichVu> kq = ql.traCuu("hat");
//        kq.forEach((DichVu d) -> System.out.println(d));
// </editor-fold>
    }
}
