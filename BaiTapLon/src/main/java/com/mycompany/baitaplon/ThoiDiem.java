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
public enum ThoiDiem {
    SANG {
        @Override
        public int layTien() {
            return 10000;
        }
        @Override
        public String layTD() {
            return "Sang";
        }
    }, CHIEU {
        @Override
        public int layTien() {
            return 15000;
        }

        @Override
        public String layTD() {
            return "Chieu";
        }
    }, TOI {
        @Override
        public int layTien() {
            return 20000;
        }

        @Override
        public String layTD() {
            return "Toi";
        }
    };
    public abstract int layTien();
    /**
     * 
     * @return Lấy thời điểm thuê
     */
    public abstract String layTD();
}
