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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DanhSachThucAn extends ThucAnApi {

    private List<ThucAn> dsThucAn = new ArrayList<>();
    private int[] slThucAn = new int[50];

    public DanhSachThucAn() {
    }

    public DanhSachThucAn(List<ThucAn> dsThucAn, int[] slAn) {
        this.dsThucAn = dsThucAn;
        this.slThucAn = slAn;
    }

    @Override
    public String toString() {
        StringBuilder kq = new StringBuilder();
        for (int i = 0; i < this.getDsThucAn().size(); i++) {
            kq.append(getDsThucAn().get(i).xuat()).append(" ,so luong: ").append(getSlThucAn()[i]).append("\n");
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
        for (int i = 0; i < this.getDsThucAn().size(); i++) {
            kq += this.getDsThucAn().get(i).gia * this.getSlThucAn()[i];
        }
        return kq;
    }

    /**
     *  /**
     * Xuất thức uống có trong List<ThucAn>
     */
    public void xuat() {
        getDsThucAn().forEach((ta) -> {
            System.out.println(ta);
        });
    }

    /**
     * Thêm ThucAn và sl có sẵn vào ds
     *
     * @param e Truyền vào 1 thức ăn
     * @param sl Truyền vào số lượng thức ăn đó
     */
    public void them(ThucAn e, int sl) {
        getDsThucAn().add(e);
        getSlThucAn()[getDsThucAn().size() - 1] = sl;
    }

    /**
     * (ADMIN) Dùng để thêm ThucAn vào danh sách bằng cách tạo bằng tay qua
     * scanner
     *
     * @param scanner
     */
    public void themSql(Scanner scanner) {
        ThucAn ta = new ThucAn();
        ta.nhap(scanner);
        System.out.print("Nhap vao so luong thuc an: ");
        int sl = scanner.nextInt();
        them(ta, sl);
        scanner.nextLine();
    }

    /**
     * (USER)Dùng để thêm thức ăn vào danh sách menu từ cơ sở dữ liệu
     *
     * @param scanner
     */
    public void themTuSql(Scanner scanner) {
        try {
            xuatThucAnSql();
            while(true){
            System.out.print("Nhap ma thuc an muon them(-1 to exit): ");
            int ma = scanner.nextInt();
            scanner.nextLine();
            if(ma == -1) break;
                getDsThucAn().add(get1ThucAn(ma));
                System.out.print("Nhap vao so luong thuc an: ");
                int sl = scanner.nextInt();
                getSlThucAn()[getDsThucAn().size() - 1] = sl;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachThucAn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void timThucAnSql(Scanner scanner){
        try {
            xuatThucAnSql();
            System.out.print("Nhap vao ten hoac ma Thuc An can tim: ");
            super.findThucAn(scanner.nextLine());
            super.showThucAn(false);
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachThucAn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Dùng để xuất tất cả ThucAn trong Mysql
     *
     * @throws SQLException
     */
    public void xuatThucAnSql() throws SQLException {
        super.readShow();
    }

    /**
     * (ADMIN)thêm 1 thức ăn đã tạo sẵn vào mysql
     *
     * @param ta ThucAn
     * @throws SQLException
     */
    public void themThucAnSql(ThucAn ta) throws SQLException {
        super.addThucAn(ta);
    }

    /**
     * Thêm thức ăn bằng nhập bằng tay trong console vào mysql.
     *
     * @param scanner
     * @throws SQLException
     */
    public void themThucAnSql(Scanner scanner) throws SQLException, Exception {
        ThucAn ta = new ThucAn(getMaxMaThucAnSQL() +1);
        ta.nhap(scanner);
        super.addThucAn(ta);
    }
    
    /**
     * (ADMIN)Nhập vào mã thức ăn cần xóa trong mysql.
     *
     * @param scanner
     * @throws SQLException
     */
    public void xoaThucAnSql(Scanner scanner) throws SQLException {
        xuatThucAnSql();
        System.out.println("Nhap ma Thuc An hoac Ten can xoa: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucAn(tenHoacMa) == false) {
            return;
        }
        super.showThucAn(true);
        System.out.println("Ban muon xoa Thuc An tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            super.deleteThucAn();
        } else {
            System.out.println("Da huy xoa.");
        }
    }

    /**
     * Nhập tên hoặc mã thức ăn cần update trong mysql
     *
     * @param scanner
     * @throws SQLException
     */
    public void updateThucAnSql(Scanner scanner) throws SQLException {
        xuatThucAnSql();
        System.out.print("Nhap ten hoac ma ThucAn can cap nhat: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucAn(tenHoacMa) == false) {
            return;
        }
        super.showThucAn(true);
        System.out.print("Ban muon cap nhat ThucAn tren(y/n): ");
        if (scanner.nextLine().equals("y")) {
            ThucAn ta = new ThucAn(ThucAnApi.selected);
            try {
                ta.nhap(scanner);
                super.edit(ta);
            } catch (SQLException e) {
                System.err.println("Nhap sai kieu du lieu");
            }
        } else {
            System.out.println("Da huy bo cap nhat.");
        }
    }

    public List<ThucAn> getDsThucAn() {
        return dsThucAn;
    }

    public int[] getSlThucAn() {
        return slThucAn;
    }

    public void setSlThucAn(int[] slThucAn) {
        this.slThucAn = slThucAn;
    }
}
