/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;
import com.mycompany.baitaplon.api.DVApi;
import com.mycompany.baitaplon.api.DVCaSiApi;
import com.mycompany.baitaplon.api.DVKaraokeApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Admin
 */
public class QLDV extends DVApi{ 
    private List<DichVu> ql = new ArrayList<DichVu>();
    
    public void them(DichVu d, DVApi dA) throws SQLException {
        this.ql.add(d);
        dA.addDV(d);
    }
    public void capNhat(DichVu d, DVApi dA) throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        d.nhap(scanner); 

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
        dA.edit(d);
    }
    public void xoa(DichVu d, DVApi dA) throws SQLException {
        this.ql.remove(d);
        dA.deleteDV();
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
    
    public void xuat(DVApi dA) throws SQLException {
         this.ql.forEach((DichVu d) -> System.out.println(d));
         dA.readShow();
//        super.readShow();
    }
}
