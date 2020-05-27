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

    public void them(SanhCuoi s) throws SQLException {
        super.addSC(s);
    }
    public void them(Scanner scanner) throws SQLException{
        SanhCuoi sc = new SanhCuoi();
        sc.nhap(scanner);
        super.addSC(sc);
    }
    public void xuat() throws SQLException {
        super.readShow();
    }

    public void capNhat(Scanner scanner) throws SQLException {
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

    public void xoa(Scanner scanner) throws SQLException {
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
