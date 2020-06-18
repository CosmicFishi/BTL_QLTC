/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

//import com.mycompany.baitaplon.api.DVKaraokeApi;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class DvKaraoke extends DichVu implements TuongTacSQL{
    private String KhoangTG;
    
    public DvKaraoke() {
        super();
        this.KhoangTG = "0";
    }
    /**
     * 
     * @param maDV: Mã dịch vụ
     * @param ten: Tên dịch vụ
     * @param gia: Giá dịch vụ
     * @param TG: Thời gian thuê
     */
    public DvKaraoke(int maDV, String ten, int gia, String TG ) {
        super(maDV,ten,gia);
        this.KhoangTG = TG;
    }
    

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner); 
        scanner.nextLine();
        System.out.println("Nhap vao khoang thoi gian: ");
        this.setKhoangTG(scanner.nextLine());
    }

    @Override
    public String toString() {
        return String.format("%sKhoang thoi gian: %s",super.toString(),this.KhoangTG); 
    }
    @Override
        public String xuat() {
            return String.format("%d,\'%s\'",this.getMaDV(),this.KhoangTG);
        }
//phần tương tác với mysql 
    @Override
    public void readSQLShow() {
        super.readSQLShow();
        super.showSQL();
        String sql = "select * from dv_karaoke where MaDv = "+ this.getMaDV() + ";" ; 
        try {
        super.read(sql);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        showSQL();
    }
    @Override
    public void addSQL() {
        super.addSQL();
        String sql = this.xuat();
        sql = "insert into dv_karaoke values (" + sql + ");";
        try {
            super.writeOrDelete(sql, "add");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void deleteSQL() {
        
        String sql = "delete from dv_karaoke where MaDv =  " + this.getMaDV()+";";
        try {
            super.writeOrDelete(sql, "delete");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        super.deleteSQL();
    }
    @Override
    public void editSQL(Scanner scanner) {
        super.editSQL(scanner);
        try {
            pStm = conn.prepareCall("update dv_karaoke set "
                    + "KhoangThoiGianThue = ? "
                    + "where MaDv = ?");
            System.out.println("Nhap vao khoang thoi gian thue: ");
            pStm.setString(1, scanner.nextLine() );
            pStm.setInt(2, this.getMaDV());
            int kq = pStm.executeUpdate();
            if(kq == 1)
                System.out.println("edit successful");
            else
                System.out.println("Edit failed");
        } catch (SQLException e) {
            System.out.println("edit fail");
        } finally {
            try {
                pStm.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    public void showSQL() {
        try {
            System.out.println("Ma dich vu        | Khoang thoi gian thue    |");
            System.out.println("+----------------+|+------------------------+|");
            while(rs.next()) {
                System.out.printf("|%-17d| %-25s|\n",
                        rs.getInt("MaDV"),
                        rs.getString("KhoangThoiGianThue"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    /**
     * @return the KhoangTG
     */
    public String getKhoangTG() {
        return KhoangTG;
    }

    /**
     * @param KhoangTG the KhoangTG to set
     */
    public void setKhoangTG(String KhoangTG) {
        this.KhoangTG = KhoangTG;
    }
    /**
     * hàm xuất để truy xuất mysql
     */
}
