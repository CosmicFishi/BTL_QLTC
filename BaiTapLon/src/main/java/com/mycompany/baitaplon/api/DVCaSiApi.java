/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DichVu;
import com.mycompany.baitaplon.DvThueCS;
import static com.mycompany.baitaplon.api.Api.conn;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DVCaSiApi extends DVApi {

    @Override
    public void addDV(DichVu d) throws SQLException {
        super.addDV(d);
        String sql2 = d.xuat();
        sql2 = "insert into dv_ca_si values(" + sql2  + ")";
        super.writeOrDelete(sql2, "add");
    }

    @Override
    public void deleteDV() throws SQLException {
        super.deleteDV(); 
        String sql2 = "delete from dv_ca_si where MaDV= " + getSelected() +";" ;
        super.writeOrDelete(sql2, "delete");
    }
    
    @Override
    public void readShow() throws SQLException {
        super.readShow();
        String sql = "select * from dv_Ca_Si";
        super.read(sql);
        showDV();
    }

    @Override
    public void edit(DichVu d) throws SQLException {
        Scanner s = new Scanner(System.in);
        super.edit(d); 
        try{
            pStm = conn.prepareCall("update dv_ca_si set"
                    + "ThongTinCaSi = ?"
                    + "SoLuongBaiHat = ?"
                    + "where MaDV = ?");
            System.out.println("Nhap vao ten ca si: ");
            pStm.setString(1, s.nextLine());
            s.nextLine();
            System.out.println("Nhap vao so luong bai hat: ");
            pStm.setInt(2, s.nextInt());
            pStm.setString(3, d.getMaDV());
        } catch(SQLException e) {
            System.err.println("error");
        } finally {
            pStm.close();
        }
    }
    
//    public void editCa(DvThueCS d) {
//        try{
//            pStm = conn.prepareCall("update dv_ca_si set"
//                    + "ThongTinCaSi = ?"
//                    + "SoLuongBaiHat = ?"
//                    + "where MaDV = ?");
//            pStm.setString(1, d.getTenCS());
//            pStm.setInt(2, d.getSoLuongBH());
//            pStm.setString(3, d.getMaDV());
//        } catch(SQLException e) {
//            System.err.println("error");
//        }
//    }

    @Override
    public void showDV() throws SQLException {
        super.showDV();
        System.out.println("|Ma dich vu   |Thong tin ca si      | So luong bai hat  |\n");
        System.out.println("|+-----------+|+--------------------|+-----------------+|\n");
        while(rs.next()) {
            System.out.printf("|%-13s|%-23s| %-19d|\n",
                    rs.getString("MaDV"),
                    rs.getString("ThongTinCaSi"),
                    rs.getInt("SoLuongBaiHat"));
        }
    }
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
    
    
    
}
