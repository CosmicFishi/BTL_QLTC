/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;
import com.mycompany.baitaplon.api.Api;
import com.mycompany.baitaplon.api.DVApi;
import com.mycompany.baitaplon.api.DVCaSiApi;
import com.mycompany.baitaplon.api.DVKaraokeApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class QLDV extends Api{ 
    private List<DichVu> ql = new ArrayList<DichVu>();
    
    public void themSQL(DichVu d) {
        d.addSQL();
    }
    public void capNhatSQL(DichVu d) {
        d.editSQL();
    }
    public void traCuuSQL(String kw) {
        try {
            //trying to code
            String sql = "select * from dv where TenDv = " + kw + ";";
            super.read(sql);
        } catch (SQLException ex) {
            System.err.println("Khong tim thay!");
        }
    }
    public void xuatDsSQL() {
        
    }
    public void xoaSQL(DichVu d) {
        d.deleteSQL();
    }
    /**
     * thêm vào một dịch vụ
     * @param d: Dịch vụ
     * @param dA: Api của dịch vụ đó
     * @throws SQLException 
     */
    public void them(DichVu d, DVApi dA) throws SQLException {
        //this.ql.add(d);
        //dA.addDV(d);
    }
    /**
     * Cập nhật dịch vụ 
     * @param d: Dịch vụ
     * @param dA: Api của dịch vụ đó
     * @throws SQLException 
     */
    public void capNhat(DichVu d, DVApi dA) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        d.nhap(scanner); 

//        DichVu d = new DichVu();
//        System.out.println("Nhap vao ma dich vu: ");
//        String maDichVu = scanner.nextLine();
//        //tim thay hay khong?
//        findDV(maDichVu);
//        if(isNullRS() == true) return; //khong tim thay thi return
//        super.showDV(1); // tim thay thi xuat ra 1 thang
//        System.out.println("Ban co muon cap nhat dich vu? (Y/N)");
//        if(scanner.nextLine().equals("y")) {
//            d.nhap(scanner);
//            super.edit(d); //nhet vao ham edit trong API de update mysql
//        }   else {
//            System.out.println("Da huy bo cap nhat");
//        }
        //dA.edit(d);
    }
    /**
     * Xoa mot dich vu
     * @param d: dịch vụ
     * @param dA: Api của dịch vụ đó
     * @throws SQLException 
     */
    public void xoa(DichVu d, DVApi dA) throws SQLException {
        this.ql.remove(d);
        //dA.deleteDV();
//        System.out.println("Nhap vao maich vu can xoa: ");
//        String dvXoa  d= scanner.nextLine();
//        findDV(dvXoa);
//        if(isNullRS() == true ) return; 
//        super.showDV(1);
//        System.out.println("ban co muon xoa sanh nay khong? (Y/N)");
//        if(scanner.nextLine().equals("y"))
//            super.deleteDV(); //xoa dich vu ben trong mysql
//        else
//            System.out.println("Da dung xoa dich vu");
    }
    
    //Can phai ghi lai mot ham tim kiem bang stored procudure
    /**
     * 
     * @param kw: Tên dịch vụ
     * @return 
     * @throws java.sql.SQLException 
     */
    public ArrayList<DichVu> traCuu(String kw) {
        kw = kw.toLowerCase();
        ArrayList<DichVu> kq = new ArrayList<>();
        for(DichVu d : this.ql) {
            if(d.getTenDV().toLowerCase().contains(kw) == true) 
                kq.add(d);
        }
        return kq;
    }
    
    /**
     * Xuất ra danh sách các dịch vụ
     * @param dA: Api của một dịch vụ cụ thể
     * @throws SQLException 
     */
    public void xuat(DVApi dA) throws SQLException {
         this.ql.forEach((DichVu d) -> System.out.println(d));
         //dA.readShow();
//        super.readShow();
    }
}
