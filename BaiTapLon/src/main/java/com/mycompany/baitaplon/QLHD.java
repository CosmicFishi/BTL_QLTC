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
     * (User)
     * nhập hóa đơn rồi lưu hóa đơn vào sql
     * @param scanner 
     */
    public void nhapHoaDon(Scanner scanner){
        int dem =0;
        while(true){
            ds.add(new HoaDonThue());
            System.out.println("==================================Nhâp hóa đơn: ");
            ds.get(dem).nhap(scanner);
            this.luuHoaDonSQL( ds.get(dem) );
            ds.get(dem).xuatSQL();
            
            System.out.println("Nhap 1 để thêm hóa đơn -1 để thoát");
            if (Integer.parseInt( scanner.nextLine() ) == -1) break;
            dem++;
        }
    }
    
    /**
     *Dùng để lưu hóa đơn vào sql 
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
                String sqlMenu = "insert into hoa_don_thuc_an values (" + hoaDon.getMaHD() + ", "+ k.getKey()+ ", "+k.getValue()+" )";
                super.writeOrDelete(sqlMenu, " ds thuc an");
            }
            
            Map<Integer, Integer> mUong = hoaDon.getDSmenu().demThucUongTrongQlMenu();
            for (Map.Entry<Integer, Integer> k : mUong.entrySet()) {
                String sqlMenu = "insert into hoa_don_thuc_uong values (" + hoaDon.getMaHD() + ", "+ k.getKey()+ ", "+k.getValue()+" )";
                super.writeOrDelete(sqlMenu, " ds thuc an");
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void xuatHoaDon() {
        this.ds.forEach((HoaDonThue h) -> h.xuatSQL());
    }
    public void xuatHoaDonSQL(Scanner scanner) throws ParseException {
        System.out.println("Nhap vao ma hoa don can xuat: ");
        String ma = scanner.nextLine();
        
        //Kiểm tra coi mã có tồn tại bên trong mysql
        String sql = "select hoa_don.MaHoaDon from hoa_don where hoa_don.MaHoaDon = " + ma +";";
        try {
            super.read(sql);
            if(rs.isBeforeFirst() == true) {
                System.out.println("Ma hoa don     |Thoi diem      |Ngay thue      |Ten buoi tiec       |Tong tien      ");
                while(rs.next()) {
                    System.out.printf("%-15d|%-15s|%-15s|%-20s|%-15d",
                        rs.getInt("MaHoaDon"),
                        rs.getString("ThoiDiem"),
                        chuyenFormatNgay(rs.getDate("NgayThue")),
                        rs.getString("TenBuoiTiec"),
                        rs.getInt("TongTien"));
                }
                QLDV DvTemp = new QLDV();
                DvTemp.xuatLuaChonTuSQL(rs.getInt("MaHoaDon"));
                QLSanhCuoi SCTemp = new QLSanhCuoi();
                SCTemp.findSC(rs.getString("MaSC"));
                //thiếu phần của Hậu
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private String chuyenFormatNgay(Date d) throws ParseException {
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = f2.format(d); // chuyển từ yyyy-MM-dd sang String
        Date d1 = f1.parse(s1);
        String kq = f1.format(d1);
        return kq;
    }
    public void xuatDoanhThuThang(int thang) {
        try {
            String sql = "select sum(TongTien) as 'TongTien' from hoa_don \n" +
                    " where month(NgayThue)=" + thang + ";";
            super.read(sql);
            while(rs.next()) {
                System.out.printf("Tong tien: %d\n", rs.getInt("TongTien"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    public void xuatDoanhThuQuy(int quy) {
        try {
            String sql = "select sum(TongTien) as 'TongTien' from hoa_don \n" +
                        "where (" + quy + "*3-2) <= month(NgayThue) && month(NgayThue) <= " + quy + "*3;";
            super.read(sql);
            while(rs.next()) {
                System.out.printf("Tong tien: %d\n", rs.getInt("TongTien"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
}
