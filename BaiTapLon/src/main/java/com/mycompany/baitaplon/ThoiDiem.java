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
    }, CHIEU {
        @Override
        public int layTien() {
            return 15000;
        }
    }, TOI {
        @Override
        public int layTien() {
            return 20000;
        }
    };
    public abstract int layTien();
}
