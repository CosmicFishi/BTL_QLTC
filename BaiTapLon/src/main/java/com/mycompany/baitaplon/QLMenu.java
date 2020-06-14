/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import com.mycompany.baitaplon.api.ThucAnApi;
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
public class QLMenu extends Api {

    private List<Menu> ql = new ArrayList<>();

    public void chon(Scanner scanner) throws SQLException {
        System.out.println("===============Them DS Menu===============");
        int dem = 0;
        while (true) {
            this.them(new Menu());
            System.out.println("=========Them Menu============");
            this.getQl().get(dem++).nhap(scanner);
            System.out.print("Ban co muon them menu (1 them; -1 thoat):");
            int input = Integer.parseInt(scanner.nextLine());
            if (input == -1) {
                return;
            }
        }
    }

    public void layDsMon(int maHD) {
        try {
            cStm = conn.prepareCall("{call getThucAnTheoHoaDon(?)}");
            cStm.setInt(1, maHD);
            rs = cStm.executeQuery();
            showThucAnDaLuu();

            cStm = conn.prepareCall("{call getThucUongTheoHoaDon(?)}");
            cStm.setInt(1, maHD);
            rs = cStm.executeQuery();
            showThucUongDaLuu();
        } catch (SQLException ex) {
            Logger.getLogger(QLMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void showThucAnDaLuu() throws SQLException {
        System.out.format("  So Luong | Ten Thuc An                                 |  Gia         | isChay\n");
        System.out.format("+----------+---------------------------------------------+--------------+-------%n");
        while (rs.next()) {
            System.out.printf("| %-9d|  %-43s| %-13d| %-6s\n",
                    rs.getInt("SoLuong"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"),
                    rs.getBoolean("isChay"));
        }
    }

    protected void showThucUongDaLuu() throws SQLException {
        System.out.format(" So Luong | Ten Thuc Uong                               |  Gia         | Hang SX     \n");
        System.out.format("+---------+---------------------------------------------+--------------+-------------%n");
        while (rs.next()) {
            System.out.printf("| %-9d|  %-43s| %-13d| %-12s\n",
                    rs.getInt("SoLuong"),
                    rs.getString("TenThucUong"),
                    rs.getInt("Gia"),
                    rs.getString("HangSX"));
        }
    }

    public int tinhGiaDs() {
        int kq = 0;
        for (Menu c : this.getQl()) {
            kq += c.tinhGiaDs();
        }
        return kq;
    }

    public void them(Menu m) {
        this.getQl().add(m);
    }

    public void xuat() {
        System.out.println("=========Xuat DS Menu===========");
        int dem = 1;
        for (int i = 0; i < getQl().size(); i++) {
            System.out.printf("------------Menu %d-----------\n", dem++);
            System.out.println(getQl().get(i).toString());
        }
    }

    public void capNhat(Menu n) {
        //Scanner s = new Scanner(System.in);
    }

    public void xoa(Menu m) {
        this.ql.remove(m);
    }
//    public ArrayList<Menu> traCuu(String kw) {
//        kw = kw.toLowerCase();
//        ArrayList<Menu> kq = new ArrayList<>();
//        for(Menu m: this.ql) {
//            if(m.getTenThucAn().toLowerCase().contains(kw) == true)
//                kq.add(m);
//        }
//        return kq;
//    }

    public List<Menu> getQl() {
        return ql;
    }
}
