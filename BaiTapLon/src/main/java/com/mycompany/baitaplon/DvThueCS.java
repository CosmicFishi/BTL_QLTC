/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.DVCaSiApi;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class DvThueCS extends DichVu implements TuongTacSQL{
    private String tenCS;
    private int soLuongBH;

    public DvThueCS() {
        super();
        this.tenCS = "ten ca si";
        this.soLuongBH = 0;
    }
    
    /**
     * 
     * @param maDV: Mã dịch vụ
     * @param ten: Tên dịch vụ
     * @param gia: Giá dịch vụ
     * @param tenCS: Tên ca sĩ
     * @param soLuong: Số lượng bài hát
     */
    public DvThueCS(int maDV, String ten, int gia, String tenCS, int soLuong) {
        super(maDV,ten,gia);
        this.tenCS = tenCS;
        this.soLuongBH = soLuong;
    }
    
    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner);
        scanner.nextLine();
        System.out.println("Nhap vao ten CS: ");
        this.tenCS = scanner.nextLine();
        System.out.println("Nhap vao so luong bai hat: ");
        this.soLuongBH = scanner.nextInt();
    }

    
    @Override
    public String toString() {
        return super.toString();
//        return String.format("%sTen ca si: %s\nSo luong bai hat: %d\n",
//                super.toString(),this.tenCS,this.soLuongBH);
    }
    @Override
    public String xuat() {
//        System.out.printf("\'%s\',\'%s\',%d", this.getMaDV(),this.getTenDV(),this.getGiaDV());
        return String.format("%d,\'%s\', %d",this.getMaDV(), this.tenCS,this.soLuongBH);
    }
    
//tương tác với mysql
    @Override
    public void readSQLShow() {
        super.readSQLShow();
        super.showSQL();
        String sql = "select * from dv_ca_si where MaDv = " + this.getMaDV() + ";";
        try {
            super.read(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        showSQL();
    }
    
    @Override
    public void addSQL() {
        super.addSQL();
        String sql = this.xuat();
        sql = "insert into dv_ca_si values (" + sql + ");";
        try {
            super.writeOrDelete(sql, "add");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void deleteSQL() {
        String sql = "delete from dv_ca_si where MaDv =  " + this.getMaDV()+";";
        try {
            super.writeOrDelete(sql, "delete");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        super.deleteSQL();
    }
    @Override
    public void editSQL() {
        Scanner s = new Scanner(System.in);
        super.editSQL(); 
        try{
            pStm = conn.prepareCall("update dv_ca_si set "
                    + "ThongTinCaSi = ? "
                    + "SoLuongBaiHat = ? "
                    + "where MaDv = ? ");
            System.out.println("Nhap vao ten ca si: ");
            pStm.setString(1, s.nextLine());
            s.nextLine();
            System.out.println("Nhap vao so luong bai hat: ");
            pStm.setInt(2, s.nextInt());
            pStm.setInt(3, this.getMaDV());
        } catch(SQLException e) {
            System.err.println("error");
        } finally {
            try {
                pStm.close();
            } catch (SQLException ex) {
                System.err.println("error in closing");
            }
        }
    }

    @Override
    public void showSQL() {
        try {
            System.out.println("|Ma dich vu   |Thong tin ca si      | So luong bai hat  |");
            System.out.println("|+-----------+|+--------------------|+-----------------+|");
            while(rs.next()) {
            System.out.printf("|%-13d|%-23s| %-19d|\n",
                    rs.getInt("MaDV"),
                    rs.getString("ThongTinCaSi"),
                    rs.getInt("SoLuongBaiHat"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    //Ham can xoa
    
//    @Override
//    public void addDVSQL(DichVu d) throws SQLException {
//        super.addDVSQL(d);
//        String sql2 = d.xuat();
//        sql2 = "insert into dv_ca_si values(" + sql2  + ")";
//        super.writeOrDelete(sql2, "add");
//    }
//
//    @Override
//    public void deleteDVSQL() throws SQLException {
//        super.deleteDVSQL(); 
//        String sql2 = "delete from dv_ca_si where MaDV= " + getSelected() +";" ;
//        super.writeOrDelete(sql2, "delete");
//    }
//    
////    @Override
////    public void readSQLShow() throws SQLException {
////        super.readSQLShow();
////        String sql = "select * from dv_Ca_Si";
////        super.read(sql);
////        showDV();
////    }
//
//    @Override
//    public void editSQL(DichVu d) throws SQLException {
//        Scanner s = new Scanner(System.in);
//        super.editSQL(d); 
//        try{
//            pStm = conn.prepareCall("update dv_ca_si set"
//                    + "ThongTinCaSi = ?"
//                    + "SoLuongBaiHat = ?"
//                    + "where MaDV = ?");
//            System.out.println("Nhap vao ten ca si: ");
//            pStm.setString(1, s.nextLine());
//            s.nextLine();
//            System.out.println("Nhap vao so luong bai hat: ");
//            pStm.setInt(2, s.nextInt());
//            pStm.setInt(3, d.getMaDV());
//        } catch(SQLException e) {
//            System.err.println("error");
//        } finally {
//            pStm.close();
//        }
//    }
//    
////    public void editCa(DvThueCS d) {
////        try{
////            pStm = conn.prepareCall("update dv_ca_si set"
////                    + "ThongTinCaSi = ?"
////                    + "SoLuongBaiHat = ?"
////                    + "where MaDV = ?");
////            pStm.setString(1, d.getTenCS());
////            pStm.setInt(2, d.getSoLuongBH());
////            pStm.setString(3, d.getMaDV());
////        } catch(SQLException e) {
////            System.err.println("error");
////        }
////    }
//
//    @Override
//    public void showDV() throws SQLException {
//        super.showDV();
//        System.out.println("|Ma dich vu   |Thong tin ca si      | So luong bai hat  |\n");
//        System.out.println("|+-----------+|+--------------------|+-----------------+|\n");
//        while(rs.next()) {
//            System.out.printf("|%-13s|%-23s| %-19d|\n",
//                    rs.getString("MaDV"),
//                    rs.getString("ThongTinCaSi"),
//                    rs.getInt("SoLuongBaiHat"));
//        }
//    }
    @Override
    public void showDV(int i) throws SQLException {
        if(rs.next()) {
            System.out.println("|Ma dich vu   | Thong tin ca si      | So luong bai hat  |\n");
            System.out.println("|+-----------+|+---------------------|+-----------------+|\n");
            System.out.printf("|%-13s| %-23s| %-19d|\n",
                    rs.getString("MaDV"),
                    rs.getString("ThongTinCaSi"),
                    rs.getInt("SoLuongBaiHat"));
            DVCaSiApi.setSelected(rs.getString("MaDV")) ;
        }
        
    }
    
    /**
     * @return the thongTinCS
     */
    public String getTenCS() {
        return tenCS;
    }

    /**
     * @param thongTinCS the thongTinCS to set
     */
    public void setTenCS(String thongTinCS) {
        this.tenCS = thongTinCS;
    }

    /**
     * @return the soLuongCS
     */
    public int getSoLuongBH() {
        return soLuongBH;
    }

    /**
     * @param soLuongBH the soLuongCS to set
     */
    public void setSoLuongBH(int soLuongBH) {
        this.soLuongBH = soLuongBH;
    }

    /**
     * Hàm xuất để truy xuất mysql
     */
    
    
    
}
