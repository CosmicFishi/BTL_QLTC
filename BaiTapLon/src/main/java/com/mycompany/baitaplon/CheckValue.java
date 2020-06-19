/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public abstract class CheckValue {

    private static final Pattern VALID_DATE_REGEX
            = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NAME_REGEX
            = Pattern.compile("[A-Za-z0-9_]{1,45}", Pattern.CASE_INSENSITIVE);

    public static void checkDay(String data) throws Exception {
        Matcher matcher = VALID_DATE_REGEX.matcher(data);
        if (matcher.find() == false) {
            throw new Exception("Nhap sai du lieu ngay thang.");
        }
    }

    public static void checkName(String data) throws Exception {
        Matcher matcher = VALID_NAME_REGEX.matcher(data);
        if (matcher.find() == false) {
            throw new Exception("Name khong co cac ki tu dac biet.");
        }
    }

    public static void checkSoAm(int so) throws Exception {
        if (so < 0) {
            throw new Exception("Nhap so khong duoc am.");
        }
    }

    public static boolean firstName(String firstName) {
        return firstName.matches("[A-Z][a-z]*");
    }
}
