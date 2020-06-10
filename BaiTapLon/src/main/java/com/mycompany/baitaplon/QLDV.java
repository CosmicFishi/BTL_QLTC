/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;
import com.mycompany.baitaplon.api.Api;
import com.mycompany.baitaplon.api.DVApi;
//import com.mycompany.baitaplon.api.DVCaSiApi;
//import com.mycompany.baitaplon.api.DVKaraokeApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class QLDV extends Api{ 
    private List<DichVu> ql = new ArrayList<DichVu>();
    Scanner scanner = new Scanner(System.in);
    
    public void themSQL(DichVu d) {
        d.addSQL();
    }
    public void capNhatSQL(DichVu d) {
        d.editSQL(scanner);
    }
    public void traCuuSQL(String kw) {
            try {
            String sql = "select dv.*, cs.ThongTinCaSi , cs.SoLuongBaiHat, kara.KhoangThoiGianThue\n" +
            "	from dv\n" +
            "	left join dv_ca_si cs on dv.MaDv = cs.MaDv\n " +
            "	left join dv_karaoke kara on dv.MaDv = kara.MaDv\n" +
            "   where dv.TenDv = '" + kw + "';";
            super.read(sql);
//            System.out.println(rs.isBeforeFirst());
            if(rs.isBeforeFirst()== true) {
                System.out.println("Ma dich vu        | Ten dich vu              | Gia dich vu |Thong tin ca si     |So luong bai hat |KhoangThoiGianThue");
                System.out.println("+----------------+|+------------------------+|+-----------+|+------------------+|+---------------+|+------------------+");
                while(rs.next()) {
                System.out.printf("|%-17d| %-25s| %-12d|%-20s|%-17d|%-20s\n",
                        rs.getInt("MaDv"),
                        rs.getString("TenDv"),
                        rs.getInt("giaDichVu"),
                        rs.getString("ThongTinCaSi"),
                        rs.getInt("SoLuongBaiHat"),
                        rs.getString("KhoangThoiGianThue"));
                }
            }  else {
                System.out.println("Khong tim thay");
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void xuatDsSQL() {
        try {
            String sql = "select dv.*, cs.ThongTinCaSi , cs.SoLuongBaiHat, kara.KhoangThoiGianThue\n" +
            "	from dv\n" +
            "	left join dv_ca_si cs on dv.MaDv = cs.MaDv\n" +
            "	left join dv_karaoke kara on dv.MaDv = kara.MaDv;";
            super.read(sql);
            System.out.println("Ma dich vu        | Ten dich vu              | Gia dich vu |Thong tin ca si     |So luong bai hat |KhoangThoiGianThue");
            System.out.println("+----------------+|+------------------------+|+-----------+|+------------------+|+---------------+|+------------------+");
            while(rs.next()) {
                System.out.printf("|%-17d| %-25s| %-12d|%-20s|%-17d|%-20s\n",
                        rs.getInt("MaDv"),
                        rs.getString("TenDv"),
                        rs.getInt("giaDichVu"),
                        rs.getString("ThongTinCaSi"),
                        rs.getInt("SoLuongBaiHat"),
                        rs.getString("KhoangThoiGianThue"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void xuatDsSQL(int maDv) {
        try {
            String sql = "select dv.*, cs.ThongTinCaSi , cs.SoLuongBaiHat, kara.KhoangThoiGianThue\n" +
            "	from dv\n" +
            "	left join dv_ca_si cs on dv.MaDv = cs.MaDv\n" +
            "	left join dv_karaoke kara on dv.MaDv = kara.MaDv\n" + 
            "   where dv.MaDv = " + maDv + ";";
            super.read(sql);
            while(rs.next()) {
                System.out.printf("|%-17d| %-25s| %-12d|%-20s|%-17d|%-20s\n",
                        rs.getInt("MaDv"),
                        rs.getString("TenDv"),
                        rs.getInt("giaDichVu"),
                        rs.getString("ThongTinCaSi"),
                        rs.getInt("SoLuongBaiHat"),
                        rs.getString("KhoangThoiGianThue"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //thiếu hàm xóa dựa vào mã dịch vụ
    public void xoaSQL(DichVu d) {
        d.deleteSQL();
    }
    
    /**
     * Nhập vào các lựa chọn
     * @param s : Scanner
     * @param maHoaDon : mã hóa đơn
     */
    public void nhapLuaChon(Scanner s, int maHoaDon) {
        List<Integer> luaChon = new ArrayList<>();
        int c = 0;
        System.out.println("Nhap vao lua chon cua ban (Ma dich vu)\n Nhap vao -1 de hoan tat nhap");
        while (c != -1) {
            c = s.nextInt();
            if(luaChon.contains(c) == false && isTonTaiDV(c)) {
                luaChon.add(c);
                System.out.println("lua chon thanh cong !!!");
            }
            else if(c != -1)
                System.out.println("Lua chon da co trong danh sach hoac Lua chon khong ton tai !!!");
        }
        if(luaChon.isEmpty())
            System.out.println("Danh sach lua chon rong !!!");
        luaChon.forEach(i -> System.out.println(i.intValue()));
    };
    /**
     * Các lựa chọn sẽ được nhập vào trong mysql || CẦN PHẢI THÊM LUACHON VAO TRONG HOADONTHUE
     * @param maHoaDon : Mã hóa đơn
     * @param luaChon : Danh sách các lựa chọn
     */
    public void nhapLuaChonSQL(int maHoaDon, List<Integer> luaChon) {
        luaChon.forEach((i) -> {
            try {
                String sql = "insert into hoa_don_dv values("+  String.format("%d, %d", maHoaDon,i) + ");";
                super.writeOrDelete(sql, "add");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        });
    }
    /**
     * xuất ra các lựa chọn của 1 hóa đơn
     * @param maHoaDon 
     */
    public void xuatLuaChonTuSQL(int maHoaDon) {
        //đọc dữ liệu về
        try {
            String sql = "select * from hoa_don_dv where MaHD = " + maHoaDon +";";
            super.read(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //lưu các mã dịch vụ vào một mảng
        List<Integer> luachon = new ArrayList<>();
        try {
            while(rs.next()) {
                luachon.add(rs.getInt("MaDv"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //từ mảng xuất ra từng dịch vụ
        System.out.println("Ma dich vu        | Ten dich vu              | Gia dich vu |Thong tin ca si     |So luong bai hat |KhoangThoiGianThue");
        System.out.println("+----------------+|+------------------------+|+-----------+|+------------------+|+---------------+|+------------------+");
        luachon.forEach(i -> xuatDsSQL(i));
    }
    /**
     * Xóa các lựa chọn của 1 hóa đơn
     * @param maHoaDon 
     */
    public void xoaLuaChonSQL(int maHoaDon) {
        try {
            String sql = "delete from hoa_don_dv where MaHD = " + maHoaDon +  ";";
            super.writeOrDelete(sql, "delete");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    /**
     * Kiểm tra mã dịch vụ có tồn tại trong mysql
     * @param maDv
     * @return 
     */
    public boolean isTonTaiDV(int maDv) {
        try {
            String sql = "select MaDv from dv where MaDv = " + maDv + ";";
            super.read(sql);
            if(rs.isBeforeFirst()) 
                return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    /**
     * thêm vào một dịch vụ
     * @param d: Dịch vụ
     * @param dA: Api của dịch vụ đó
     * @throws SQLException 
     */
    public void them(DichVu d, DVApi dA) throws SQLException {
        //this.ql.add(d);
        //dA.addDV(d);
    }
    /**
     * Cập nhật dịch vụ 
     * @param d: Dịch vụ
     * @param dA: Api của dịch vụ đó
     * @throws SQLException 
     */
    public void capNhat(DichVu d, DVApi dA) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        d.nhap(scanner); 

//        DichVu d = new DichVu();
//        System.out.println("Nhap vao ma dich vu: ");
//        String maDichVu = scanner.nextLine();
//        //tim thay hay khong?
//        findDV(maDichVu);
//        if(isNullRS() == true) return; //khong tim thay thi return
//        super.showDV(1); // tim thay thi xuat ra 1 thang
//        System.out.println("Ban co muon cap nhat dich vu? (Y/N)");
//        if(scanner.nextLine().equals("y")) {
//            d.nhap(scanner);
//            super.edit(d); //nhet vao ham edit trong API de update mysql
//        }   else {
//            System.out.println("Da huy bo cap nhat");
//        }
        //dA.edit(d);
    }
    /**
     * Xoa mot dich vu
     * @param d: dịch vụ
     * @param dA: Api của dịch vụ đó
     * @throws SQLException 
     */
    public void xoa(DichVu d, DVApi dA) throws SQLException {
        this.ql.remove(d);
        //dA.deleteDV();
//        System.out.println("Nhap vao maich vu can xoa: ");
//        String dvXoa  d= scanner.nextLine();
//        findDV(dvXoa);
//        if(isNullRS() == true ) return; 
//        super.showDV(1);
//        System.out.println("ban co muon xoa sanh nay khong? (Y/N)");
//        if(scanner.nextLine().equals("y"))
//            super.deleteDV(); //xoa dich vu ben trong mysql
//        else
//            System.out.println("Da dung xoa dich vu");
    }
    
    //Can phai ghi lai mot ham tim kiem bang stored procudure
    /**
     * 
     * @param kw: Tên dịch vụ
     * @return 
     * @throws java.sql.SQLException 
     */
    public ArrayList<DichVu> traCuu(String kw) {
        kw = kw.toLowerCase();
        ArrayList<DichVu> kq = new ArrayList<>();
        for(DichVu d : this.ql) {
            if(d.getTenDV().toLowerCase().contains(kw) == true) 
                kq.add(d);
        }
        return kq;
    }
    
    /**
     * Xuất ra danh sách các dịch vụ
     * @param dA: Api của một dịch vụ cụ thể
     * @throws SQLException 
     */
    public void xuat(DVApi dA) throws SQLException {
         this.ql.forEach((DichVu d) -> System.out.println(d));
         //dA.readShow();
//        super.readShow();
    }
}
