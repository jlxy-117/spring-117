/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.metro.fare;

/**
 * 南京计价
 * @author turkeylock
 */
public class NanjingPrice implements IFigureOutPrice {

    @Override
    public float getPrice(String start, String end) {
        Subway sw = new Subway();
        sw.initCity("南京");
        float distance = sw.getDistance(sw.calculate(new Station(start),new Station(end)));
   
        if (distance == -1) {
            return -1;
        } else if (distance <= 10) {
            return 2;
        } else if (distance <= 16) {
            return 3;
        } else if (distance <= 22) {
            return 4;
        } else if (distance <= 30) {
            return 5;
        } else if (distance <= 38) {
            return 6;
        } else if (distance <= 48) {
            return 7;
        } else if (distance <= 58) {
            return 8;
        } else if (distance <= 70) {
            return 9;
        } else {
            return 10 + (int) (distance - 70) / 14;
        }

    }

}
