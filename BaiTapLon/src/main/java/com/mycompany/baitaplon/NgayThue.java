/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

/**
 *
 * @author Admin
 */
public enum NgayThue {
    NgayThuong {
        @Override
        public int layTien() {
            return 10000;
        }
    }, Bay_ChuNhat {
        @Override
        public int layTien() {
            return 20000;
        }
    };
    
    public abstract int layTien();
}
