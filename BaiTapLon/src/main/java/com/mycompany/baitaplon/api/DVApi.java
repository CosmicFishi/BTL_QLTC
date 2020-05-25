/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DVApi extends Api{
    
    protected static String selected;
    
    public void Xuat() throws SQLException {
        String sql = "select * from dv";
        super.read(sql);
    }
    
    //tạo ra thêm một lớp API cho Dv ca sĩ với Dv karaoke
}
