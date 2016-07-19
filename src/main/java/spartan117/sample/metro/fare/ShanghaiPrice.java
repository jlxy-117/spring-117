/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.metro.fare;

/**
 * 上海计价
 * @author turkeylock
 */
public class ShanghaiPrice implements IFigureOutPrice {

    @Override
    public float getPrice(String start, String end) {
        Subway sw = new Subway();
        sw.initCity("上海");
        float distance = sw.getDistance(sw.calculate(new Station(start),new Station(end)));
        if (distance == -1) {
            return -1;          //无站点信息
        } else if (distance <= 6) {
            return 3;
        } else {
            return 4 + (int) (distance - 6) / 10;
        }
    }
}
