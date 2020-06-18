/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.ThucUongApi;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DanhSachThucUong extends ThucUongApi {

    private List<ThucUong> dsThucUong = new ArrayList<>();
    private int[] slThucUong = new int[50];
    private int[] maThucUongSql = new int[50];

    public DanhSachThucUong() {
    }

    public DanhSachThucUong(List<ThucUong> dsThucUong, int[] slUong) {
        this.dsThucUong = dsThucUong;
        this.slThucUong = slUong;
    }

    @Override
    public String toString() {
        StringBuilder kq = new StringBuilder();
        for (int i = 0; i < this.getDsThucUong().size(); i++) {
            kq.append(getDsThucUong().get(i).xuat()).append(" ,so luong: ")
                    .append(getSlThucUong()[i]).append("\n");
        }
        return kq.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Tính tổng số tiền của danh sách các thức uống (đã lấy sl thức uống * giá)
     *
     * @return
     */
    public int tinhGiaDs() {
        int kq = 0;
        for (int i = 0; i < this.getDsThucUong().size(); i++) {
            kq += this.getDsThucUong().get(i).gia * this.getSlThucUong()[i];
        }
        return kq;
    }

    /**
     * Xuất thức uống có trong List<ThucUong>
     */
    public void xuat() {
        for (ThucUong ta : getDsThucUong()) {
            System.out.println(ta);
        }
    }

    /**
     * Thêm thức uống và sl có sẵn vào ds
     *
     * @param e
     * @param sl
     */
    public void them(ThucUong e, int sl) {
        getDsThucUong().add(e);
        slThucUong[getDsThucUong().size() - 1] = sl;
    }

    /**
     * (ADMIN)Thêm thức uống và số lượng mỗi loại vào danh sách
     *
     * @param scanner
     */
    public void themSql(Scanner scanner) {
        ThucUong ta = new ThucUong();
        ta.nhap(scanner);
        System.out.print("Nhap vao so luong Thuc Uong: ");
        int sl = scanner.nextInt();
        them(ta, sl);
        scanner.nextLine();
    }

    /**
     * (USER)Thêm thức uống vào danh sách bằng cách chọn thức uống có sẵn trong
     * sql
     *
     * @param scanner
     * @throws SQLException
     */
    public void themTuSql(Scanner scanner) {
        try {
            xuatThucUongSql();
            while (true) {
                System.out.print("Nhap ma thuc uong muon them(-1 to exit): ");
                int ma = scanner.nextInt();
                scanner.nextLine();
                if (ma == -1) {
                    break;
                }
                getDsThucUong().add(get1ThucUong(ma));
                this.maThucUongSql[getDsThucUong().size() - 1] = ma;
                System.out.print("Nhap vao so luong thuc uong: ");
                int sl = scanner.nextInt();
                slThucUong[getDsThucUong().size() - 1] = sl;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachThucUong.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void timThucUongSql(Scanner scanner) {
        try {
            xuatThucUongSql();
            System.out.print("Nhap vao ten hoac ma Thuc Uong can tim: ");
            super.findThucUong(scanner.nextLine());
            super.showThucUong(false);
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachThucAn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Xuất toàn bộ thức uống có sẵn trong sql ra console
     *
     * @throws SQLException
     */
    public void xuatThucUongSql() throws SQLException {
        super.readShow("thuc_uong");
    }

    /**
     * (ADMIN) Thêm 1 đối tượng ThucUong đã tạo vào sql
     *
     * @param ta
     * @throws SQLException
     */
    public void themThucUongSql(ThucUong ta) throws SQLException {
        super.addThucUong(ta);
    }

    /**
     * Thêm 1 ThucUong vào sql bằng cách nhập bằng tay trong console
     *
     * @param scanner
     * @throws SQLException
     */
    public void themThucUongSql(Scanner scanner) throws SQLException {
        ThucUong ta;
        try {
            ta = new ThucUong(getMaxMaThucUongSQL() +1);
            ta.nhap(scanner);
            super.addThucUong(ta);
        } catch (Exception ex) {
            Logger.getLogger(DanhSachThucUong.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * (ADMIN) xóa thức uống trong sql (in ra ds thức uống cho admin chọn mã
     * hoặc tên cần xóa)
     *
     * @param scanner
     * @throws SQLException
     */
    public void xoaThucUongSql(Scanner scanner) throws SQLException {
        System.out.println("Nhap ma Thuc An hoac Ten can xoa: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucUong(tenHoacMa) == false) {
            return;
        }
        super.showThucUong(true);
        System.out.println("Ban muon xoa sanh tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            System.out.println(selected);
            super.deleteThucUong();
        } else {
            System.out.println("Da huy xoa.");
        }
    }

    /**
     * Cập nhật Thức uống trong sql (bắt nhập tên hoặc mã thức uống cần sửa)
     *
     * @param scanner
     * @throws SQLException
     */
    public void updateThucUongSql(Scanner scanner) throws SQLException, Exception {
        xuatThucUongSql();
        System.out.println("Nhap ten hoac ma ThucUong can cap nhat: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucUong(tenHoacMa) == false) {
            return;
        }
        super.showThucUong(true);
        System.out.println("Ban muon cap nhat ThucUong tren(y/n): ");
        if (scanner.nextLine().equals("y")) {
            ThucUong ta = new ThucUong();
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

    public int[] getMaThucUongSql() {
        return maThucUongSql;
    }

    public List<ThucUong> getDsThucUong() {
        return dsThucUong;
    }

    public int[] getSlThucUong() {
        return slThucUong;
    }
}
