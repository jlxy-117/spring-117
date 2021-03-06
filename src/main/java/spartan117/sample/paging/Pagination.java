/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.paging;

/**
 * 传入总数据量,计算页数
 * @author turkeylock
 */
public class Pagination {
    private int curPage;             //当前页  
    private int pageCount;           //总页数  
    private int rowsCount;           //总行数  
    private int pageSize=10;         //每页多少条数据  
      
      
      
    public Pagination(int rows){      
        this.setRowsCount(rows);  
        if(this.rowsCount % this.pageSize == 0){  
            this.pageCount=this.rowsCount / this.pageSize;  
        }  
        else if(rows<this.pageSize){  
            this.pageCount=1;  
        }  
        else{  
            this.pageCount=this.rowsCount / this.pageSize +1;  
        }  
    }  
    
      
      
    public int getCurPage() {  
        return curPage;  
    }  
    public void setCurPage(int curPage) {  
        this.curPage = curPage;  
    }  
    public int getPageCount() {  
        return pageCount;  
    }  
    public void setPageCount(int pageCount) {  
        this.pageCount = pageCount;  
    }  
    public int getPageSize() {  
        return pageSize;  
    }  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
    public int getRowsCount() {  
        return rowsCount;  
    }  
    public void setRowsCount(int rowsCount) {  
        this.rowsCount = rowsCount;  
    }  
}
