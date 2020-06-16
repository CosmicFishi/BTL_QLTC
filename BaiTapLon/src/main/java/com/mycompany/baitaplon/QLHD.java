/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QLHD extends Api {

    List<HoaDonThue> ds = new ArrayList<>();

    /**
     * (User) nhập hóa đơn rồi lưu hóa đơn vào sql
     *
     * @param scanner
     */
    public void nhapHoaDon(Scanner scanner) {
        int dem = 0;
        while (true) {
            ds.add(new HoaDonThue());
            System.out.println("==================================Nhâp hóa đơn: ");
            ds.get(dem).nhap(scanner);
            this.luuHoaDonSQL(ds.get(dem));
            ds.get(dem).xuatSQL();

            System.out.println("Nhap 1 để thêm hóa đơn, nhap -1 để thoát");
            if (Integer.parseInt(scanner.nextLine()) == -1) {
                break;
            }
            dem++;
        }
    }

    /**
     * Dùng để lưu hóa đơn vào sql
     *
     * @param hoaDon truyền vào kiểu HoaDon
     */
    public void luuHoaDonSQL(HoaDonThue hoaDon) {
        try {
            //Đã tồn tại hàm bên trong QLDV nên tao thay
            hoaDon.getDichVu().nhapLuaChonSQL(hoaDon.getMaHD(), hoaDon.getLuaChonDv());

            String sqlHoaDon = "insert into hoa_don values (" + hoaDon.toString() + " )";
            super.writeOrDelete(sqlHoaDon, " luu hoa don.");

            Map<Integer, Integer> mAn = hoaDon.getDSmenu().demMonAnTrongQlMenu();
            for (Map.Entry<Integer, Integer> k : mAn.entrySet()) {
                String sqlMenu = "insert into hoa_don_thuc_an values (" + hoaDon.getMaHD() + ", " + k.getKey() + ", " + k.getValue() + " )";
                super.writeOrDelete(sqlMenu, " ds thuc an");
            }

            Map<Integer, Integer> mUong = hoaDon.getDSmenu().demThucUongTrongQlMenu();
            for (Map.Entry<Integer, Integer> k : mUong.entrySet()) {
                String sqlMenu = "insert into hoa_don_thuc_uong values (" + hoaDon.getMaHD() + ", " + k.getKey() + ", " + k.getValue() + " )";
                super.writeOrDelete(sqlMenu, " ds thuc an");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Xuất toàn bộ hóa đơn trong sql
     */
    public void xuatHoaDon() {
        this.ds.forEach((HoaDonThue h) -> h.xuatSQL());
    }

    /**
     *Xuất tất cả hóa đơn trong MySQL
     */
    public void xuatHoaDonSQL() {
        String sql = "select * from hoa_don;";
        try {
            super.read(sql);
            String maSC = "";
            int maHD = 0;
            QLDV DvTemp = new QLDV();
            QLSanhCuoi SCTemp = new QLSanhCuoi();
            QLMenu menuTemp = new QLMenu();
            if (rs.isBeforeFirst() == true) {
                while (rs.next()) {
                    System.out.println("===============================================================================================================================");
                    System.out.println("Ma hoa don     |Thoi diem      |Ngay thue      |Ten buoi tiec       |Tong tien      ");
                    System.out.printf("%-15d|%-15s|%-15s|%-20s|%-15d\n",
                            rs.getInt("MaHoaDon"),
                            rs.getString("ThoiDiem"),
                            chuyenNgay(rs.getDate("NgayThue")),
                            rs.getString("TenBuoiTiec"),
                            rs.getInt("TongTien"));
                    maSC = rs.getString("MaSC");
                    maHD = rs.getInt("MaHoaDon");
                    DvTemp.xuatLuaChonTuSQL(maHD);
                    SCTemp.findSCShow(maSC);
                    menuTemp.layDsMonSQL(maHD);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Xuất một hóa đơn trong mysql
     * @param maHD truyền vào mã hóa đơn kiểu Int
     * @throws ParseException
     */
    public void xuatHoaDonSQL(int maHD) throws ParseException {
        String sql = "select * from hoa_don where hoa_don.MaHoaDon = " + maHD + ";";
        try {
            String maSC = "";
            super.read(sql);
            if (rs.isBeforeFirst() == true) {
                System.out.println("Ma hoa don     |Thoi diem      |Ngay thue      |Ten buoi tiec       |Tong tien      ");
                while (rs.next()) {
                    System.out.printf("%-15d|%-15s|%-15s|%-20s|%-15d\n",
                            rs.getInt("MaHoaDon"),
                            rs.getString("ThoiDiem"),
                            chuyenNgay(rs.getDate("NgayThue")),
                            rs.getString("TenBuoiTiec"),
                            rs.getInt("TongTien"));
                    maSC = rs.getString("MaSC");
                }
                QLDV DvTemp = new QLDV();
                DvTemp.xuatLuaChonTuSQL(maHD);
                QLSanhCuoi SCTemp = new QLSanhCuoi();
                SCTemp.findSCShow(maSC);
                QLMenu menuTemp = new QLMenu();
                menuTemp.layDsMonSQL(maHD);
                //thiếu phần của Hậu
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     *Xóa hóa đơn theo mã hóa đơn trong Sql
     * @param maHD kiểu Int
     * @throws ParseException
     */
    public void xoaHoaDonSQL(int maHD) throws ParseException {
        try {
            cStm = conn.prepareCall("{call xoaHoaDonTheoMa(?)}");
            cStm.setInt(1, maHD);
            int kq = cStm.executeUpdate();
            if(kq==1){
                System.out.println("xoa thanh cong");
            } else
                System.out.println("xoa that bai.");
        } catch (SQLException ex) {
            Logger.getLogger(QLHD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String chuyenNgay(java.sql.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void xuatDoanhThuThang(int thang) {
        try {
            String sql = "select sum(TongTien) as 'TongTien' from hoa_don \n"
                    + " where month(NgayThue)=" + thang + ";";
            super.read(sql);
            while (rs.next()) {
                System.out.printf("Tong tien: %d\n", rs.getInt("TongTien"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void xuatDoanhThuQuy(int quy) {
        try {
            String sql = "select sum(TongTien) as 'TongTien' from hoa_don \n"
                    + "where (" + quy + "*3-2) <= month(NgayThue) && month(NgayThue) <= " + quy + "*3;";
            super.read(sql);
            while (rs.next()) {
                System.out.printf("Tong tien: %d\n", rs.getInt("TongTien"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}