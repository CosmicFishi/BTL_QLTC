package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.SCApi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QLSanhCuoi extends SCApi {
   
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *Tao sảnh cưới từ SQL
     * @param scanner
     * @return
     */
    public SanhCuoi taoScFromSQL(Scanner scanner){
        try {
            xuatSC();
            System.out.print("Nhap ma SC muon tao: ");
            String ma = scanner.nextLine();
            return get1SC(ma.toUpperCase());
        } catch (SQLException ex) {
            Logger.getLogger(QLSanhCuoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SanhCuoi();
    }
    /**
     *(ADMIN) thêm sảnh cưới mới vào SQL
     * @param scanner
     * @throws SQLException
     */
    public void themSC(Scanner scanner) throws SQLException{
        SanhCuoi sc = new SanhCuoi();
        sc.nhap(scanner);
        super.addSC(sc);
    }
    
    /**
     *xuất tất cả sảnh cưới trong mysql 
     * @throws SQLException
     */
    public void xuatSC() throws SQLException {
        super.readShow();
    }
    
    /**
     *cập nhật sảnh cưới qua console trong danh sách sảnh cưới từ mysql.
     * @param scanner
     * @throws SQLException
     */
    public void capNhatSC(Scanner scanner) throws SQLException {
        SanhCuoi sc = new SanhCuoi();
        System.out.println("Nhap ten hoac ma SC can cap nhat: ");
        String tenHoacMa = scanner.nextLine();
        findSC(tenHoacMa);
        if (super.isNullRs()) return;
        super.showSC(1);
        System.out.println("Ban muon cap nhat SC tren(y/n): ");
        if (scanner.nextLine().equals("y")) {
            sc.nhap(scanner);
            System.out.println("...");
            super.edit(sc);
        } else {
            System.out.println("Da huy bo cap nhat.");
        }
    }

    /**
     *xóa sảnh cưới qua console bằng scanner trong mysql
     * @param scanner
     * @throws SQLException
     */
    public void xoaSC(Scanner scanner) throws SQLException {
        System.out.println("Nhap ma SC hoac tenSC can xoa: ");
        String tenHoacMa = scanner.nextLine();
        findSC(tenHoacMa);
        if (super.isNullRs()) return;
        super.showSC(1); 
        System.out.println("Ban muon xoa sanh tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            super.deleteSC();
        } else {
            System.out.println("Da huy xoa.");
        }
    }

//    public ArrayList<SanhCuoi> traCuu(String kw) {
//        kw = kw.toLowerCase();
//        ArrayList<SanhCuoi> kq = new ArrayList<>();
//        for(SanhCuoi s: this.dsSanh) 
//            if(s.getTenSanh().toLowerCase().contains(kw) == true)
//                kq.add(s);
//        return kq;
//    }
//    public ArrayList<SanhCuoi> traCuu(int t) {
//        ArrayList<SanhCuoi> kq = new ArrayList<>();
//        this.dsSanh.stream().filter((s) -> (s.getTenSanh().toLowerCase().equals(t) == true)).forEachOrdered((s) -> {
//            kq.add(s);
//        });
//        return kq;
//    }
}
