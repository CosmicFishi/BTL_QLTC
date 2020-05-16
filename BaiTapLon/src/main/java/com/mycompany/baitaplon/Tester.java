/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Tester {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SanhCuoi s1 = new SanhCuoi("abc", 1, 100);
        System.out.print(s1);
        SanhCuoi s2 = new SanhCuoi();
        s2.nhap(s);
        System.out.println(s2);
        
        DanhSachSanh dsSanh = new DanhSachSanh();
        System.out.println("=== danh sach sanh ===" );
        dsSanh.them(s1);
        dsSanh.them(s2);
        dsSanh.xuat();
        
        System.out.println("=== tim kiem ===");
        ArrayList<SanhCuoi> DanhSachSanh = dsSanh.traCuu("def");
        DanhSachSanh.forEach((SanhCuoi sc) -> System.out.print(sc));
    }
}
