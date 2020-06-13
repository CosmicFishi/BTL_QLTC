/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class QLHD extends Api {
    List<HoaDonThue> ds = new ArrayList<>();
    
    public void nhapHoaDon(Scanner scanner){
        int dem =0;
        while(true){
            ds.add(new HoaDonThue());
            System.out.println("==================================Nhâp hóa đơn: ");
            ds.get(dem).nhap(scanner);
            ds.get(dem).xuat();
            this.luuHoaDon( ds.get(dem) );
            
            System.out.println("Nhap 1 để thêm hóa đơn -1 để thoát");
            if (Integer.parseInt( scanner.nextLine() ) == -1) break;
            dem++;
        }
    }
    
    public Map demMonAnTrongQlMenu(QLMenu ql) {
        Map<Integer, Integer> m = new HashMap<>();
        int index = 0;
        for (Menu menu : ql.getQl()) {
            for (ThucAn h : menu.getDsAn().getDsThucAn()) {
                int n = h.getMa();
                if (m.get(n) == null) {
                    m.put(n, menu.getDsAn().getSlThucAn()[index]);
                } else {
                    m.put(n, m.get(n) + menu.getDsAn().getSlThucAn()[index]);
                }
                index++;
            }
            for (Map.Entry<Integer, Integer> hm : m.entrySet()) {
                m.put(hm.getKey(), hm.getValue()* menu.getSlMenu());
            }
        }
        return m;
    }
    public Map demThucUongTrongQlMenu(QLMenu ql) {
        Map<Integer, Integer> m = new HashMap<>();
        int index = 0;
        for (Menu menu : ql.getQl()) {
            for (ThucUong h : menu.getDsUong().getDsThucUong()) {
                int n = h.getMa();
                if (m.get(n) == null) {
                    m.put(n, menu.getDsUong().getSlThucUong()[index]);
                } else {
                    m.put(n, m.get(n) + menu.getDsUong().getSlThucUong()[index]);
                }
                index++;
            }
            for (Map.Entry<Integer, Integer> hm : m.entrySet()) {
                m.put(hm.getKey(), hm.getValue()* menu.getSlMenu());
            }
        }
        return m;
    }
    public void luuHoaDon(HoaDonThue hoaDon) {
        try {
            for(int i: hoaDon.getLuaChonDv()){
                String sqlDichVu = "insert into hoa_don_dv values (" + hoaDon.getMaHD() + ", "+ i +" )";
                super.writeOrDelete(sqlDichVu, " them dv");
            }
            
            String sqlHoaDon = "insert into hoa_don values (" + hoaDon.toString() + " )";
            super.writeOrDelete(sqlHoaDon, " luu hoa don.");
            
            Map<Integer, Integer> mAn = this.demMonAnTrongQlMenu(hoaDon.getDSmenu());
            for (Map.Entry<Integer, Integer> k : mAn.entrySet()) {
                String sqlMenu = "insert into hoa_don_thuc_an values (" + hoaDon.getMaHD() + ", "+ k.getKey()+ ", "+k.getValue()+" )";
                super.writeOrDelete(sqlMenu, " ds thuc an");
            }
            
            Map<Integer, Integer> mUong = this.demThucUongTrongQlMenu(hoaDon.getDSmenu());
            for (Map.Entry<Integer, Integer> k : mUong.entrySet()) {
                String sqlMenu = "insert into hoa_don_thuc_uong values (" + hoaDon.getMaHD() + ", "+ k.getKey()+ ", "+k.getValue()+" )";
                super.writeOrDelete(sqlMenu, " ds thuc an");
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
