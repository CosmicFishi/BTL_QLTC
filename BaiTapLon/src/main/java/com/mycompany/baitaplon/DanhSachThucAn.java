/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.ThucAnApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DanhSachThucAn extends ThucAnApi {
    private List<ThucAn> dsThucAn = new ArrayList<>();
    private int[] slThucAn;
    
    public DanhSachThucAn(){}
    
    public DanhSachThucAn(List<ThucAn> dsThucAn,int[] slAn) {
        this.dsThucAn = dsThucAn;
        this.slThucAn = slAn;
    }
    public void xuat(){
        for(ThucAn ta: dsThucAn){
            System.out.println(ta);
        }
    }
    public void them(ThucAn e, int sl){
        dsThucAn.add(e);
        slThucAn[slThucAn.length] = sl;
    }
    public void them(Scanner scanner){
        ThucAn ta = new ThucAn();
        ta.nhap(scanner);
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        them(ta, sl);
    }
    public void themTuSql(Scanner scanner, int ma) throws SQLException{
        dsThucAn.add(getThucAn(ma));
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        slThucAn[slThucAn.length] = sl;
    }
    
    public void xuatThucAn() throws SQLException{
        super.readShow();
    }
    public void themThucAn(ThucAn ta) throws SQLException{
        super.addThucAn(ta);
    }
    public void themThucAn(Scanner scanner) throws SQLException{
        ThucAn ta = new ThucAn();
        ta.nhap(scanner);
        super.addThucAn(ta);
    }
    public void xoaThucAn(Scanner scanner) throws SQLException{
        System.out.println("Nhap ma Thuc An hoac Ten can xoa: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucAn(tenHoacMa) == false)
            return;
        super.showThucAn(1);
        System.out.println("Ban muon xoa sanh tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            System.out.println(selected);
            super.deleteThucAn();
        } else {
            System.out.println("Da huy xoa.");
        }
    }
    public void updateThucAn(Scanner scanner) throws SQLException{
        System.out.println("Nhap ten hoac ma ThucAn can cap nhat: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucAn(tenHoacMa)==false) return;
        super.showThucAn(1);
        System.out.println("Ban muon cap nhat ThucAn tren(y/n): ");
        if (scanner.nextLine().equals("y")) {
            ThucAn ta = new ThucAn();
            try {
                ta.nhap(scanner);
                super.edit(ta);
            } catch (Exception e) {
                System.err.println("Nhap sai kieu du lieu");
            }
        } else {
            System.out.println("Da huy bo cap nhat.");
        }
    }
    
}
