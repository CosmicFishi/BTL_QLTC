/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.List;

/**
 *
 * @author Admin
 */
public class DanhSachThucUong {
    protected List<ThucUong> dsThucUong;
    protected int[] slThucUong;
    
    public DanhSachThucUong(List<ThucUong> dsThucUong, int[] slUong) {
        this.dsThucUong = dsThucUong;
        this.slThucUong = slUong;
    }
}
