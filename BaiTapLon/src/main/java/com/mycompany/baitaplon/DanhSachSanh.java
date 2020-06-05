package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.SCApi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DanhSachSanh extends SCApi {
    private final List<SanhCuoi> dsSanh = new ArrayList<>();
    
    public DanhSachSanh(){};
    
    /**
     *thêm Sảnh cưới vào List<SanhCuoi>.
     * @param s
     */
    public void them(SanhCuoi s){
        this.dsSanh.add(s);
    }

    /**
     *Xuất tất cả sảnh cưới trong List<SanhCuoi>.
     */
    public void xuat(){
        System.out.println("MaSC   |Ten Sanh Cuoi       |vi tri|suc chua|gia thue       |");
        for(SanhCuoi sc: this.dsSanh){
            System.out.println(sc);
        }
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     *Them sảnh cưới đã tạo vào mysql.
     * @param s Sảnh Cưới đã tạo
     * @throws SQLException
     */
    public void themMySQL(SanhCuoi s) throws SQLException {
        super.addSC(s);
    }

    /**
     *Them sảnh cưới tạo bằng tay qua scanner vào mysql
     * @param scanner
     * @throws SQLException
     */
    public void themMysql(Scanner scanner) throws SQLException{
        SanhCuoi sc = new SanhCuoi();
        sc.nhap(scanner);
        super.addSC(sc);
    }
    
    /**
     *xuất tất cả sảnh cưới trong mysql 
     * @throws SQLException
     */
    public void xuatMysql() throws SQLException {
        super.readShow();
    }
    
    /**
     *cập nhật sảnh cưới qua console trong danh sách sảnh cưới từ mysql.
     * @param scanner
     * @throws SQLException
     */
    public void capNhatMysql(Scanner scanner) throws SQLException {
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
    public void xoaMysql(Scanner scanner) throws SQLException {
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
