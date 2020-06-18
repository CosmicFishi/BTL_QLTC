/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;
import com.mycompany.baitaplon.api.Api;
//import com.mycompany.baitaplon.api.DVApi;
//import com.mycompany.baitaplon.api.DVCaSiApi;
//import com.mycompany.baitaplon.api.DVKaraokeApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
            System.out.println("+-----------------+--------------------------+-------------+--------------------+-----------------+-------------------+");
            System.out.println("| Ma dich vu      | Ten dich vu              | Gia dich vu |Thong tin ca si     |So luong bai hat |KhoangThoiGianThue |");
            System.out.println("+-----------------+--------------------------+-------------+--------------------+-----------------+-------------------+");
            while(rs.next()) {
                System.out.printf("|%-17d| %-25s| %,-12d|%-20s|%-17d|%-19s|\n",
                        rs.getInt("MaDv"),
                        rs.getString("TenDv"),
                        rs.getInt("giaDichVu"),
                        rs.getString("ThongTinCaSi"),
                        rs.getInt("SoLuongBaiHat"),
                        rs.getString("KhoangThoiGianThue"));
            }
            System.out.println("+-----------------+--------------------------+-------------+--------------------+-----------------+-------------------+");
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
                System.out.printf("|%-17d| %-25s| %,-12d|%-20s|%-17d|%-19s|\n",
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
    /**
     * Xóa dựa vào mã dịch vụ
     * @param MaDv 
     */
    public void xoaSQL(int MaDv) {
        if(isTonTaiDV(MaDv)) {
            try {
            String sql = "delete from dv_karaoke where MaDv =  " + MaDv+";";
            super.writeOrDelete(sql, "delete");
            sql = "delete from dv_ca_si where MaDv =  " + MaDv+";";
            super.writeOrDelete(sql, "delete");
            sql = "delete from dv where MaDv =  " + MaDv +";";
            super.writeOrDelete(sql, "delete");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("Ma dich vu khong ton tai!!!");
        }
        
    }
    /**
     * Xóa dựa vào đối tượng dịch vụ
     * @param d 
     */
    public void xoaSQL(DichVu d) {
        d.deleteSQL();
    }
    
    /**
     * Nhập vào các lựa chọn
     * @param s : Scanner
     * @param maHoaDon : mã hóa đơn
     * @return Danh sách các lưa chọn mà người dùng đã nhập
     */
    public List<Integer> nhapLuaChon(Scanner s, int maHoaDon) {
        List<Integer> luaChon = new ArrayList<>();
        int c = 0;
        System.out.println("Nhap vao lua chon cua ban (Ma dich vu)\n Nhap vao -1 de hoan tat nhap: ");
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
//        luaChon.forEach(i -> System.out.println(i.intValue()));
        return luaChon;
    };
    /**
     * Các lựa chọn sẽ được nhập vào trong mysql
     * @param maHoaDon : Mã hóa đơn
     * @param luaChon : Danh sách các lựa chọn
     */
    public void nhapLuaChonSQL(int maHoaDon, List<Integer> luaChon) {
        if(!(luaChon.isEmpty())) {
            luaChon.forEach((i) -> {
                try {
                    String sql = "insert into hoa_don_dv values("+  String.format("%d, %d", maHoaDon,i) + ");";
                    super.writeOrDelete(sql, "Luu dich vu ");
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            });
        };
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
        } catch (Exception ex) {
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
        System.out.println("+-----------------+--------------------------+-------------+--------------------+-----------------+-------------------+");
        System.out.println("|  Ma dich vu     | Ten dich vu              | Gia dich vu |Thong tin ca si     |So luong bai hat |KhoangThoiGianThue |");
        System.out.println("+-----------------+--------------------------+-------------+--------------------+-----------------+-------------------+");
        luachon.forEach(i -> xuatDsSQL(i));
        System.out.println("+-----------------+--------------------------+-------------+--------------------+-----------------+-------------------+");
    }
    /**
     * Xóa các lựa chọn của 1 hóa đơn
     * @param maHoaDon 
     */
    public void xoaLuaChonSQL(int maHoaDon) {
        try {
            String sql = "delete from hoa_don_dv where MaHD = " + maHoaDon +  ";";
            super.writeOrDelete(sql, "delete");
        } catch (Exception ex) {
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
    public int layTongTienDVSQL(int maHoaDon) {
        int kq = 0;
        try {
            
            String sql = "select dv.GiaDichVu\n"
                    + "from dv\n"
                    + "left join hoa_don_dv hddv on hddv.MaDv = dv.MaDv\n"
                    + "where hddv.MaHD = " + maHoaDon + ";";
            super.read(sql);
            while(rs.next()) {
                kq += rs.getInt("GiaDichVu");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return kq;
    }
    /**
     * thêm vào một dịch vụ
     * @param d: Dịch vụ
     * @throws SQLException 
     */
    public void them(DichVu d) throws SQLException {
        this.ql.add(d);
        //dA.addDV(d);
    }
    /**
     * Cập nhật dịch vụ 
     * @param d: Dịch vụ
     * @throws SQLException 
     */
    public void capNhat(DichVu d) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        d.nhap(scanner); 
    }
    /**
     * Xoa mot dich vu
     * @param d: dịch vụ
     */
    public void xoa(DichVu d) {
        this.ql.remove(d);
    }
    
    //Can phai ghi lai mot ham tim kiem bang stored procudure
    /**
     * 
     * @param kw: Tên dịch vụ
     * @return 
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
     */
    public void xuat(){
         this.ql.forEach((DichVu d) -> System.out.println(d));
         //dA.readShow();
//        super.readShow();
    }
}
