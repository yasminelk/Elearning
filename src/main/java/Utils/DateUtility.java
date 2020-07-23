/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ISLEM
 */
public class DateUtility {
    
    public static Date stringToDateTime(String s) throws ParseException{
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = format.parse(s);
        return date;
    }
    
    public static long getCurrentDateMs() {
        return new Date().getTime();
    }
    
}
