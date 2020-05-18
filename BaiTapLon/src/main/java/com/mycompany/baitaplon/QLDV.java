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
public class QLDV {
    private List<DichVu> ql = new ArrayList<DichVu>();
    
    public void them(DichVu d) {
        this.ql.add(d);
    }
    public void capNhat(DichVu d) {
        Scanner s = new Scanner(System.in);
        d.nhap(s);
    }
    public void xoa(DichVu d) {
        this.ql.remove(d);
    }
    public ArrayList<DichVu> traCuu(String kw) {
        kw = kw.toLowerCase();
        ArrayList<DichVu> kq = new ArrayList<>();
        for(DichVu d : this.ql) {
            if(d.getTenDV().toLowerCase().contains(kw) == true) 
                kq.add(d);
        }
        return kq;
    }
}
