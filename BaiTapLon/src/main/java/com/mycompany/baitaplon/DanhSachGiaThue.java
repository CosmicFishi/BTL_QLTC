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
public class DanhSachGiaThue {
    private List<GiaThue> dsGia = new ArrayList<>();
    
    public void them(GiaThue g) {
        this.dsGia.add(g);
    }
    public void capNhat(GiaThue g) {
        Scanner s = new Scanner(System.in);
        g.nhap(s);
    }
    public void xuat() {
        this.dsGia.forEach((GiaThue g) -> System.out.println(g));
    }
}
