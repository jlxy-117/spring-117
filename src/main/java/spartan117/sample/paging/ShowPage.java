/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页显示 传入list显示分页后的list
 *
 * @author turkeylock
 */
public class ShowPage {

    private int totalData;

    public ShowPage(int rowCount) {
        this.totalData = rowCount;
    }

    //返回当前页list数据和总页数
    public List<Map<String, Object>> getPage(List<Map<String, Object>> PageList, int page) {
        List<Map<String, Object>> clist = PageList;//将查询结果存放在List集合里  
        Pagination pagination = new Pagination(clist.size());
        //设置当前页  
        pagination.setCurPage(page); //page参数，代表当前页数  
        //获得分页大小  
        int pagesize = pagination.getPageSize();
        //获得分页数据在list集合中的索引  
        int firstIndex = (page - 1) * pagesize;
        int toIndex = page * pagesize;
        if (toIndex > clist.size()) {
            toIndex = clist.size();
        }
        if (firstIndex > toIndex) {
            firstIndex = 0;
            pagination.setCurPage(1);
        }
        //截取数据集合，获得分页数据  
        List<Map<String, Object>> courseList = clist.subList(firstIndex, toIndex);
        return courseList;
    }

    //返回总页数
    public static int getTotalPage(int rowCount) {
        Pagination p = new Pagination(rowCount);
        return p.getPageCount();
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getTotalData() {
        return totalData;
    }
}
