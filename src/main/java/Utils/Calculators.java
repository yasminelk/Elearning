/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author ISLEM
 */
public class Calculators {

    public static int timeToSeconds(String s) throws NumberFormatException {
        String[] time = s.split(":");
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60;
    }

    public static int calculateTotalPoints(String[] s) throws NumberFormatException {

        int points = 0;
        for (int i = 0; i < s.length; i++) {
            points = points + Integer.parseInt(s[i]);
        }
        return points;

    }

}
