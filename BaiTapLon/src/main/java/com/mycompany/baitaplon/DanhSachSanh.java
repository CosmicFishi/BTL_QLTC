/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DanhSachSanh {
    private List<SanhCuoi> dsSanh =  new ArrayList<>();
    
    public void them(SanhCuoi s) {
        this.dsSanh.add(s);
    }
    public void xuat() {
        this.dsSanh.forEach((SanhCuoi s) -> {
            System.out.println(s);
        });
    }
    public void capNhat(SanhCuoi s) {
        Scanner scanner = new Scanner(System.in);
        s.nhap(scanner);
    }
    public void xoa(SanhCuoi s) {
        this.dsSanh.remove(s);
    }
    
    public ArrayList<SanhCuoi> traCuu(String kw) {
        kw = kw.toLowerCase();
        ArrayList<SanhCuoi> kq = new ArrayList<>();
        for(SanhCuoi s: this.dsSanh) 
            if(s.getTenSanh().toLowerCase().contains(kw) == true)
                kq.add(s);
        return kq;
    }
    public ArrayList<SanhCuoi> traCuu(int t) {
        ArrayList<SanhCuoi> kq = new ArrayList<>();
        this.dsSanh.stream().filter((s) -> (s.getTenSanh().toLowerCase().equals(t) == true)).forEachOrdered((s) -> {
            kq.add(s);
        });
        return kq;
    }
}
