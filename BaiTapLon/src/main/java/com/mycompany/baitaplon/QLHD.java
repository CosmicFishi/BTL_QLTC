/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import com.mycompany.baitaplon.api.SCApi;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
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
     * @throws java.lang.Exception
     */
    public void nhapHoaDon(Scanner scanner) throws Exception {
        int dem = ds.size();
        ds.add(new HoaDonThue(getMaxMaHDSQL() + 1));
        System.out.println("===============NHAP HOA DON =============== ");
        ds.get(dem).nhap(scanner);
        this.luuHoaDonSQL(ds.get(dem));
    }

    public int getMaxMaHDSQL() throws SQLException, Exception {
        try {
            String sql = "select max(MaHoaDon) as 'Max' from hoa_don;";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Max");
            }else
                throw new Exception("Loi khong lay dc id hoa don max.");
        } catch (SQLException ex) {
            throw new Exception("Loi khong lay dc id hoa don max.");
        }
    }

    /**
     * Dùng để lưu hóa đơn vào sql
     *
     * @param hoaDon truyền vào kiểu HoaDon
     */
    public void luuHoaDonSQL(HoaDonThue hoaDon) {
        try {
            String sqlHoaDon = "insert into hoa_don values (" + hoaDon.toString() + " )";
            super.writeOrDelete(sqlHoaDon, "Luu hoa don ");

            hoaDon.getDichVu().nhapLuaChonSQL(hoaDon.getMaHD(), hoaDon.getLuaChonDv());

            Map<Integer, Integer> Map;
            Map = hoaDon.getDSmenu().demMonAnTrongQlMenu();
            for (Map.Entry<Integer, Integer> k : Map.entrySet()) {
                String sqlMenu = String.format("insert into hoa_don_thuc_an values (%d, %d, %d)",
                        hoaDon.getMaHD(), k.getKey(), k.getValue());
                super.writeOrDelete(sqlMenu, "Luu thuc an ");
            }

            Map = hoaDon.getDSmenu().demThucUongTrongQlMenu();
            for (Map.Entry<Integer, Integer> k : Map.entrySet()) {
                String sqlMenu = String.format("insert into hoa_don_thuc_uong values (%d, %d, %d)",
                        hoaDon.getMaHD(), k.getKey(), k.getValue());
                super.writeOrDelete(sqlMenu, "Luu thuc uong ");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Xuất toàn bộ hóa đơn trong sql
     */
    public void xuatHoaDonSQL() {
        String sql = "select * from hoa_don;";
        try {
            super.read(sql);
            String maSC = "";
            int maHD = 0;
            QLDV DvTemp = new QLDV();
            SCApi SCTemp = new SCApi();
            QLMenu menuTemp = new QLMenu();
            if (rs.isBeforeFirst() == true) {
                while (rs.next()) {
                    System.out.println("==================================XUAT HOA DON=================================================================================");
                    System.out.println("+--------------+---------------+---------------+--------------------+--------------+");
                    System.out.println("| Ma hoa don   |Thoi diem      |Ngay thue      |Ten buoi tiec       |Tong tien     |");
                    System.out.println("+--------------+---------------+---------------+--------------------+--------------|");
                    System.out.printf("|%-14d|%-15s|%-15s|%-20s|%,-14d| \n",
                            rs.getInt("MaHoaDon"),
                            rs.getString("ThoiDiem"),
                            chuyenNgay(rs.getDate("NgayThue")),
                            rs.getString("TenBuoiTiec"),
                            rs.getInt("TongTien"));
                    System.out.println("+--------------+---------------+---------------+--------------------+--------------+\n");

                    maSC = rs.getString("MaSC");
                    maHD = rs.getInt("MaHoaDon");
                    DvTemp.xuatLuaChonTuSQL(maHD);

                    if (SCTemp.findSC(maSC)) {
                        SCTemp.showSC(true);
                    }
                    menuTemp.layDsMonSQL(maHD);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Xuất một hóa đơn trong mysql
     *
     * @param maHD truyền vào mã hóa đơn kiểu Int
     */
    public void xuatHoaDonSQL(int maHD) {
        String sql = "select * from hoa_don where hoa_don.MaHoaDon = " + maHD + ";";
        try {
            super.read(sql);
            String maSC = "";
            if (rs.isBeforeFirst() == true) {
                System.out.println("==================================XUAT HOA DON=================================================================================");
                System.out.println("+--------------+---------------+---------------+--------------------+--------------+");
                System.out.println("| Ma hoa don   |Thoi diem      |Ngay thue      |Ten buoi tiec       |Tong tien     |");
                System.out.println("+--------------+---------------+---------------+--------------------+--------------|");
                while (rs.next()) {
                    System.out.printf("|%-14d|%-15s|%-15s|%-20s|%,-13d|\n",
                            rs.getInt("MaHoaDon"),
                            rs.getString("ThoiDiem"),
                            chuyenNgay(rs.getDate("NgayThue")),
                            rs.getString("TenBuoiTiec"),
                            rs.getInt("TongTien"));
                    maSC = rs.getString("MaSC");
                }
                System.out.println("+--------------+---------------+---------------+--------------------+--------------+\n");

                QLDV DvTemp = new QLDV();
                QLMenu menuTemp = new QLMenu();
                QLSanhCuoi SCTemp = new QLSanhCuoi();

                DvTemp.xuatLuaChonTuSQL(maHD);
                if (SCTemp.findSC(maSC)) {
                    SCTemp.showSC(true);
                }
                menuTemp.layDsMonSQL(maHD);
            } else {
                System.out.println("Hoa don khong ton tai.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Xóa hóa đơn theo mã hóa đơn trong Sql
     *
     * @param maHD kiểu Int
     */
    public void xoaHoaDonSQL(int maHD) {
        try {
            cStm = conn.prepareCall("{call xoaHoaDonTheoMa(?)}");
            cStm.setInt(1, maHD);
            int kq = cStm.executeUpdate();
            if (kq == 1) {
                System.out.println("xoa thanh cong");
            } else {
                System.out.println("xoa that bai.");
            }
        } catch (SQLException ex) {
            throw new Error("Cant delete hoa don/ error QLHD/xoaHoaDonSQL(int maHD)");
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
                System.out.printf("Tong tien: %,d\n", rs.getInt("TongTien"));
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
                System.out.printf("Tong tien: %,d\n", rs.getInt("TongTien"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
