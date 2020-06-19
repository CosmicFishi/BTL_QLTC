/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import com.mycompany.baitaplon.api.MenuApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Admin
 */
public class QLMenu extends MenuApi{

    private final List<Menu> ql = new ArrayList<>();

    /**
     * Dùng để cho người dùng tạo ds menu, ds thức ăn thức uống
     *
     * @param scanner
     * @throws SQLException
     */
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

    /**
     *
     * @param maHD
     */
    public void layDsMonSQL(int maHD) {
        try {
            layDsMonAnTheoHoaDonSQL(maHD);
            showThucAnDaLuu();

            layDsMonUongTheoHoaDonSQL(maHD);
            showThucUongDaLuu();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//    /**
//     * Dùng để show những thức ăn đã luu trong qlmenu
//     *
//     * @throws SQLException
//     */
//    public void showThucAnDaLuu() throws SQLException {
//        int tongTien = 0;
//        System.out.format("\n+-----------+---------------------------------------------+--------------+-------|\n");
//        System.out.format("| So Luong  | Ten Thuc An                                 |  Gia         | isChay|\n");
//        System.out.format("+-----------+---------------------------------------------+--------------+-------|\n");
//        while (rs.next()) {
//            System.out.printf("| %-10d|  %-43s| %,-13d| %-6s|\n",
//                    rs.getInt("SoLuong"),
//                    rs.getString("TenThucAn"),
//                    rs.getInt("Gia"),
//                    rs.getBoolean("isChay"));
//            tongTien += rs.getInt("SoLuong") * rs.getInt("Gia");
//        }
//        System.out.printf(" Tong don gia thuc an: %,d\n", tongTien);
//        System.out.format("+-----------+---------------------------------------------+--------------+-------+\n");
//    }

//    /**
//     * Dùng để show những thức uóng đã luu trong qlmenu
//     *
//     * @throws SQLException
//     */
//    public void showThucUongDaLuu() throws SQLException {
//        int tongTien = 0;
//        System.out.format("\n+----------+---------------------------------------------+--------------+-------------+\n");
//        System.out.format("| So Luong | Ten Thuc Uong                               |  Gia         | Hang SX     |\n");
//        System.out.format("+----------+---------------------------------------------+--------------+-------------|\n");
//        while (rs.next()) {
//            System.out.printf("| %-9d|  %-43s| %,-13d| %-12s|\n",
//                    rs.getInt("SoLuong"),
//                    rs.getString("TenThucUong"),
//                    rs.getInt("Gia"),
//                    rs.getString("HangSX"));
//            tongTien += rs.getInt("SoLuong") * rs.getInt("Gia");
//        }
//        System.out.printf(" Tong don gia thuc uong: %,d\n", tongTien);
//        System.out.format("+----------+---------------------------------------------+--------------+-------------+\n");
//    }

    /**
     * dùng để đếm mỗi loại thức ăn trong qlmenu có số lượng bao nhiêu để thêm
     * vào sql
     *
     * @return HashMap
     */
    public Map demMonAnTrongQlMenu() {
        Map<Integer, Integer> m = new HashMap<>();
        
        for (Menu menu : this.ql) {
            int index = 0;
            for (ThucAn h : menu.getDsAn().getDsThucAn()) {
                int n = h.getMa();
                if (m.get(n) == null) {
                    m.put(n, menu.getDsAn().getSlThucAn()[index] * menu.getSlMenu());
                } else {
                    m.put(n, m.get(n) + menu.getDsAn().getSlThucAn()[index] * menu.getSlMenu());
                }
                index++;
            }
//            m.entrySet().forEach((hm) -> {
//                m.put(hm.getKey(), hm.getValue() * menu.getSlMenu());
//            });
        }
        return m;
    }

    /**
     * dùng để đếm mỗi loại thức uống trong qlmenu có số lượng bao nhiêu để thêm
     * vào sql
     *
     * @return HashMap
     */
    public Map demThucUongTrongQlMenu() {
        Map<Integer, Integer> m = new HashMap<>();
        for (Menu menu : this.ql) {
            int index = 0;
            for (ThucUong h : menu.getDsUong().getDsThucUong()) {
                int n = h.getMa();
                if (m.get(n) == null) {
                    m.put(n, menu.getDsUong().getSlThucUong()[index] * menu.getSlMenu());
                } else {
                    m.put(n, m.get(n) + menu.getDsUong().getSlThucUong()[index] * menu.getSlMenu());
                }
                index++;
            }
//            for (Map.Entry<Integer, Integer> k : m.entrySet()) {
//                m.put(k.getKey(), k.getValue() * this.ql);
//            }
//            m.entrySet().forEach((hm) -> {
//                m.put(hm.getKey(), hm.getValue() * menu.getSlMenu());
//            });
        }
        return m;
    }

    /**
     * Dùng để tính tổng tiền các thức ăn thức uống đã chọn
     *
     * @return int tổng giá của các menu
     */
    public int tinhGiaDs() {
        int kq = 0;
        kq = this.ql.stream().map((c) -> c.tinhGiaDs()).reduce(kq, Integer::sum);
        return kq;
    }

    public void them(Menu m) {
        this.ql.add(m);
    }

    public void xuat() {
        System.out.println("=========Xuat DS Menu===========");
        int dem = 1;
        for (int i = 0; i < this.ql.size(); i++) {
            System.out.printf("------------Menu %d-----------\n", dem++);
            System.out.println(this.ql.get(i).toString());
        }
    }

    public void xoa(Menu m) {
        this.ql.remove(m);
    }

    public List<Menu> getQl() {
        return ql;
    }
}
