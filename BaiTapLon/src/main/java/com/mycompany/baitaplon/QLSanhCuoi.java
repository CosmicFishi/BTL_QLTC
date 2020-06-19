package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.SCApi;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QLSanhCuoi extends SCApi {

    /**
     * (USER)Tạo sảnh cưới từ SQL
     *
     * @param scanner
     * @return
     */
    public SanhCuoi taoScFromSQL(Scanner scanner) {
        try {
            xuatSC();
            System.out.print("Nhap ma SC muon tao: ");
            String ma = scanner.nextLine();
            return get1SC(ma.toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(QLSanhCuoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SanhCuoi();
    }
    
    

    /**
     * (ADMIN) thêm sảnh cưới mới vào SQL
     *
     * @param scanner
     */
    public void themSC(Scanner scanner) {
        try {
            SanhCuoi sc = new SanhCuoi(getMaxMaSCSQL());
            sc.nhap(scanner);
            super.addSC(sc);
        } catch (Exception ex) {
            Logger.getLogger(QLSanhCuoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void timSCTenMa(Scanner scanner) {
        System.out.print("Nhap ten hoac ma can tim: ");
        if (findSC(scanner.nextLine()))
            showSC(false);
    }

    public void timSCViChua(Scanner scanner) throws SQLException {
        try {
            System.out.print("Nhap vi tri hoac suc chua can tim: ");
            findSCShow(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * xuất tất cả sảnh cưới trong Mysql
     */
    public void xuatSC() throws SQLException {
        super.readShow();
    }

    /**
     * (ADMIN)cập nhật sảnh cưới qua console trong danh sách sảnh cưới từ mysql.
     *
     * @param scanner
     */
    public void capNhatSC(Scanner scanner) {
        try {
            SanhCuoi sc = new SanhCuoi();
            System.out.print("Nhap ten hoac ma SC can cap nhat: ");
            
            if (findSC(scanner.nextLine())) {
                super.showSC(true);
            }else
                return;
            System.out.print("Ban muon cap nhat SC tren(y/n): ");
            if (scanner.nextLine().equals("y")) {
                sc.nhap(scanner);
                super.edit(sc);
            } else {
                System.out.println("Da huy bo cap nhat.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLSanhCuoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * (ADMIN)xóa sảnh cưới qua console bằng scanner trong mysql
     *
     * @param scanner
     */
    public void xoaSC(Scanner scanner) {
        System.out.print("Nhap ma SC hoac tenSC can xoa: ");
        if (findSC(scanner.nextLine()) == false) {
            return;
        }
        super.showSC(true);
        System.out.print("Ban muon xoa sanh tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            super.deleteSC();
        } else {
            System.out.println("Da huy xoa.");
        }
    }
}
