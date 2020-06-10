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
    private int[] maThucAnSql = new int[50];
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
    public String toString() {
        StringBuilder kq = new StringBuilder();
        for(int i =0; i<this.dsThucAn.size(); i++){
            kq.append(dsThucAn.get(i).xuat()).append(" ,so luong: ").append(slThucAn[i]).append("\n");
        }
        return kq.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *Dùng để khởi tạo thêm ThucAn có sẵn
     * @param e Truyền vào 1 thức ăn
     * @param sl Truyền vào số lượng thức ăn đó
     */
    public void them(ThucAn e, int sl){
        dsThucAn.add(e);
        slThucAn[dsThucAn.size()-1] = sl;
    }

    /**
     *(ADMIN) Dùng để thêm ThucAn vào danh sách bằng cách tạo bằng tay qua scanner
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
     *(USER)Dùng để thêm thức ăn vào danh sách menu từ cơ sở dữ liệu
     * @param scanner
     * @throws SQLException
     */
    public void themTuSql(Scanner scanner) throws SQLException{
        xuatThucAn();
        while(true){
            System.out.print("Nhap ma thuc an muon them(-1 to exit): ");
            int ma = scanner.nextInt();
            scanner.nextLine();
            if(ma == -1) break;
            dsThucAn.add(get1ThucAn(ma));
            this.maThucAnSql[dsThucAn.size() - 1]=ma;
            System.out.print("Nhap vao so luong thuc an: ");
            int sl = scanner.nextInt();
            slThucAn[dsThucAn.size() - 1] = sl;
        }
    }
    
    /**
     *(ADMIN)Dùng để xuất tất cả ThucAn trong Mysql
     * @throws SQLException
     */
    public void xuatThucAn() throws SQLException{
        super.readShow();
    }
    
    /**
     *(ADMIN)thêm thức ăn đã tạo sẵn vào mysql
     * @param ta ThucAn
     * @throws SQLException
     */
    public void themThucAn(ThucAn ta) throws SQLException{
        super.addThucAn(ta);
    }

    /**
     *Thêm thức ăn bằng nhập bằng tay trong console vào mysql.
     * @param scanner
     * @throws SQLException
     */
    public void themThucAn(Scanner scanner) throws SQLException{
        ThucAn ta = new ThucAn();
        ta.nhap(scanner);
        super.addThucAn(ta);
    }

    /**
     *Nhập vào mã thức ăn cần xóa trong mysql.
     * @param scanner
     * @throws SQLException
     */
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
    
    /**
     *Nhập tên hoặc mã thức ăn cần update trong mysql
     * @param scanner
     * @throws SQLException
     */
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

    public int[] getMaThucAnSql() {
        return maThucAnSql;
    }
    
}
