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
    private int[] slThucAn = new int[50];
    
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
    
    
    /**
     *Dùng để thêm ThucAn có sẵn
     * @param e Truyền vào 1 thức ăn
     * @param sl Truyền vào số lượng thức ăn đó
     */
    public void them(ThucAn e, int sl){
        dsThucAn.add(e);
        slThucAn[dsThucAn.size()-1] = sl;
    }

    /**
     *Dùng để thêm ThucAn vào danh sách bằng cách tạo bằng tay qua scanner
     * @param scanner
     */
    public void them(Scanner scanner){
        ThucAn ta = new ThucAn();
        ta.nhap(scanner);
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        them(ta, sl);
        scanner.nextLine();
    }

    /**
     *dùng để thêm thức ăn có sẵn trong mysql vào danh sách thức ăn
     * @param scanner
     * @param ma
     * @throws SQLException
     */
    public void them(Scanner scanner, int ma) throws SQLException{
        dsThucAn.add(getThucAn(ma));
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        slThucAn[slThucAn.length] = sl;
    }
    
    /**
     *Dùng để xuất tất cả ThucAn trong Mysql
     * @throws SQLException
     */
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
