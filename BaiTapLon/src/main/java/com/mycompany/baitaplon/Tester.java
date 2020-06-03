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
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Tester {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//Thử dịch vụ của Dũng
        try{
            Api api = new Api();
            Api.connectSql();
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println("khong truy cap duoc mysql");
        }
        DichVu d1 = new DvKaraoke(5, "alo", 3000, "1 tieng");
        DichVu d2 = new DvThueCS(5, "hi hi", 1000, "dung", 5);
        DVCaSiApi DVCS = new DVCaSiApi();
        QLDV ql = new QLDV();
        ql.them(d1, DVCS);
        
        ql.xuat(DVCS);
        //ql.capNhat(d1, DVCS );
        
//thử đồ ăn, thức uổng của Hậu
//        Scanner scanner = new Scanner(System.in);
//        
//        DanhSachThucAn dsAn1 = new DanhSachThucAn();
//        DanhSachThucUong dsUong1 = new DanhSachThucUong();
//        
//        ThucUong uong1 = new ThucUong("coca", 12000, "My");
//        ThucUong uong2 = new ThucUong("sting", 10000, "VN");
//        ThucAn an1 = new ThucAn("lau", 25000, false);
//        
//        //dsAn1.them(scanner);
//        //dsAn1.them(scanner);
//        //dsAn1.them(scanner);
//        dsAn1.them(an1, 5);
//        dsUong1.them(uong1, 10);
//        dsUong1.them(uong2, 5);
//        //dsUong1.them(scanner);
//        
//        Menu menu1 = new Menu(dsAn1, dsUong1);
//        Menu menu2 = new Menu(dsAn1, dsUong1);
//        QLMenu qlmenu = new QLMenu();
//        qlmenu.them(menu1);
//        qlmenu.them(menu2);
//        qlmenu.xuat();
        
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
    }
}
