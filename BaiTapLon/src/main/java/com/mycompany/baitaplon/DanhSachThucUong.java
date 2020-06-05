/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.ThucUongApi;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DanhSachThucUong extends ThucUongApi{
    protected List<ThucUong> dsThucUong = new ArrayList<>();
    protected int[] slThucUong = new int[50];
    
    public DanhSachThucUong(){}
    
    public DanhSachThucUong(List<ThucUong> dsThucUong, int[] slUong) {
        this.dsThucUong = dsThucUong;
        this.slThucUong = slUong;
    }

    @Override
    public String toString() {
        StringBuilder kq = new StringBuilder();
        for(int i =0; i<this.dsThucUong.size(); i++){
            kq.append(dsThucUong.get(i).xuat()).append(" ,so luong: ")
                    .append(slThucUong[i]).append("\n");
        }
        return kq.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void xuat(){
        for(ThucUong ta: dsThucUong){
            System.out.println(ta);
        }
    }
    public void them(ThucUong e, int sl){
        dsThucUong.add(e);
        slThucUong[dsThucUong.size()-1] = sl;
    }
    public void them(Scanner scanner){
        ThucUong ta = new ThucUong();
        ta.nhap(scanner);
        System.out.print("Nhap vao so luong ThucUong: ");
        int sl = scanner.nextInt();
        them(ta, sl);
        scanner.nextLine();
    }
    public void themTuSql(Scanner scanner) throws SQLException{
        xuatThucUong();
        while(true){
            System.out.print("Nhap ma thuc uong muon them(-1 to exit): ");
            int ma = scanner.nextInt();
            scanner.nextLine();
            if(ma == -1) break;
            dsThucUong.add(getThucUong(ma));
            System.out.print("Nhap vao so luong thuc uong: ");
            int sl = scanner.nextInt();
            slThucUong[dsThucUong.size() - 1] = sl;
        }
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
