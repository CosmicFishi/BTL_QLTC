/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.ThucUongApi;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DanhSachThucUong extends ThucUongApi{
    protected List<ThucUong> dsThucUong;
    protected int[] slThucUong;
    
    public DanhSachThucUong(List<ThucUong> dsThucUong, int[] slUong) {
        this.dsThucUong = dsThucUong;
        this.slThucUong = slUong;
    }
    public void xuat(){
        for(ThucUong ta: dsThucUong){
            System.out.println(ta);
        }
    }
    public void them(ThucUong e, int sl){
        dsThucUong.add(e);
        slThucUong[slThucUong.length] = sl;
    }
    public void them(Scanner scanner){
        ThucUong ta = new ThucUong();
        ta.nhap(scanner);
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        them(ta, sl);
    }
    public void themTuSql(Scanner scanner, int ma) throws SQLException{
        xuatThucUong();
        dsThucUong.add(getThucUong(ma));
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        slThucUong[slThucUong.length] = sl;
    }
    public void xuatThucUong() throws SQLException{
        super.readShow("thuc_uong");
    }
    public void themThucUong(ThucUong ta) throws SQLException{
        super.addThucUong(ta, "thuc_uong");
    }
    public void themThucUong(Scanner scanner) throws SQLException{
        ThucUong ta = new ThucUong();
        ta.nhap(scanner);
        super.addThucUong(ta, "thuc_uong");
    }
    public void xoaThucUong(Scanner scanner) throws SQLException{
        System.out.println("Nhap ma Thuc An hoac Ten can xoa: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucUong(tenHoacMa) == false)
            return;
        super.showThucUong(1);
        System.out.println("Ban muon xoa sanh tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            System.out.println(selected);
            super.deleteThucUong();
        } else {
            System.out.println("Da huy xoa.");
        }
    }
    public void updateThucUong(Scanner scanner) throws SQLException{
        System.out.println("Nhap ten hoac ma ThucUong can cap nhat: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucUong(tenHoacMa)==false) return;
        super.showThucUong(1);
        System.out.println("Ban muon cap nhat ThucUong tren(y/n): ");
        if (scanner.nextLine().equals("y")) {
            ThucUong ta = new ThucUong();
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
