/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class QLHD extends Api{
    //(Admin)
    List<HoaDonThue> ql = new ArrayList<>();
    public void themHD(HoaDonThue h1) {
        this.ql.add(h1);
    };
    public void xoaHD(HoaDonThue h1) {
        this.ql.remove(h1);
    };
    public void capNhatHD(HoaDonThue h1) {
        Scanner s = new Scanner(System.in);
        h1.nhap(s);
    };
    public void xuatDS() {
        this.ql.forEach((HoaDonThue h) -> h.xuat());
    };
     
    //(Người dùng)
    public void nhapSQL() {};
    public void xoaSQL() {};
    public void capNhatSQL() {};
    public void xuatDSSQL(){};
}
