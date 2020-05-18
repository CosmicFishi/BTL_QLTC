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
public class QLMenu {
    private List<Menu> ql = new ArrayList<>();
    
    public void them (Menu m) {
        this.ql.add(m);
    }
    public void xuat() {
        this.ql.forEach((Menu m) -> {
            System.out.println(m);
        });
    }
    public void capNhat(Menu n) {
        Scanner s = new Scanner(System.in);
        n.nhap(s);
    }
    public void xoa(Menu m) {
        this.ql.remove(m);
    }
    public ArrayList<Menu> traCuu(String kw) {
        kw = kw.toLowerCase();
        ArrayList<Menu> kq = new ArrayList<>();
        for(Menu m: this.ql) {
            if(m.getTenThucAn().toLowerCase().contains(kw) == true)
                kq.add(m);
        }
        return kq;
    }
}
