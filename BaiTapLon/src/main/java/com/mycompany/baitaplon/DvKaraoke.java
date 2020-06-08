/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import static com.mycompany.baitaplon.api.DVApi.getSelected;
import com.mycompany.baitaplon.api.DVKaraokeApi;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DvKaraoke extends DichVu {
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
        return super.toString();
        //return String.format("%sKhoang thoi gian: %s",super.toString(),this.KhoangTG); 
    }
    @Override
        public String xuat() {
//            System.out.printf("\'%s\',\'%s\',%d", this.getMaDV(),this.getTenDV(),this.getGiaDV());
            return String.format("%d',\'%s\'",this.getMaDV(),this.KhoangTG);
        }
        
        
        
//phần tương tác với mysql
    @Override
    public void readSQLShow() throws SQLException {
        super.readSQLShow();
        String sql = "select * from dv_karaoke";
        super.read(sql);
        showDV();
    }
    
    /**
     * Thêm dịch vụ karaoke
     * @param d: dịch vụ karaoke
     * @throws SQLException 
     */
    @Override
    public void addDVSQL(DichVu d) throws SQLException {
        super.addDVSQL(d);
        String sql2 = d.xuat();
        sql2 ="insert into dv_karaoke values(" + sql2 + ")";
        super.writeOrDelete(sql2, "add"); // thêm mã và khoảng thời gian thuê vào bảng karaoke
    }

    @Override
    public void deleteDVSQL() throws SQLException {
        super.deleteDVSQL();
        String sql2  =  "delete from dv_karaoke where MaDV= " + getSelected()  + ";";
        super.writeOrDelete(sql2, "delete");
    }

    @Override
    public void editSQL(DichVu d) throws SQLException {
        Scanner s = new Scanner(System.in);
        super.editSQL(d); 
        try{
            pStm = conn.prepareCall("update dv_karaoke set"
                    + "KhoangThoiGianThue = ?"
                    + "where MaDV = ?");
            System.out.println("Nhap vao khoang thoi gian thue: ");
            pStm.setString(1, s.nextLine() );
            pStm.setInt(2, d.getMaDV());
        } catch(SQLException e) {
            System.err.println("error");
        } finally {
            pStm.close();
        }
    }

    
//    public void editKa(DvKaraoke d) {
//        try{
//            pStm = conn.prepareCall("update dv_karaoke set"
//                    + "KhoangThoiGianThue = ?"
//                    + "where MaDV = ?");
//            pStm.setString(1, d.getKhoangTG());
//            pStm.setString(2, d.getMaDV());
//        } catch(SQLException e) {
//            System.err.println("error");
//        }
//    }
   
    @Override
    public void showDV() throws SQLException {
        System.out.println("Ma dich vu       | Khoang thoi gian thue    |Gia dich vu \n");
        System.out.println("+---------------+|+------------------------+|+-----------+\n");
        while(rs.next()) {
            System.out.printf("|%-17d| %-25s| %-12d|\n",
                    rs.getInt("MaDV"),
                    rs.getString("KhoangThoiGianThue"),
                    rs.getInt("giaDichVu"));
        }
    }
    @Override
    public void showDV(int i) throws SQLException {
        if(rs.next()) {
            System.out.println("Ma dich vu        | Khoang thoi gian thue    |Gia dich vu \n");
            System.out.println("+----------------+|+------------------------+|+-----------+\n");
            System.out.printf("|%-17d| %-25s| %-12d|\n",
                    rs.getInt("MaDV"),
                    rs.getString("KhoangThoiGianThue"),
                    rs.getInt("giaDichVu"));
            DVKaraokeApi.setSelected(rs.getString("MaHD"));
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
