/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.service;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spartan117.sample.metro.pass.HalfOrderDaoMySQL;

/**
 *
 * @author SONY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HalfOrderDaoMySQLTest {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private HalfOrderDaoMySQL hodm;
    
    @Test
    public void test1(){
        hodm.GetIn("111", "南京站");
        List ret = jdbc.queryForList("select * from temp where id=111");
        assertThat(ret.size(),is(1));
    }
    
    @Test
    public void test2(){
        assertThat(hodm.GetOut("111"),is("南京站"));
    }
    
    @Test
    public void test3(){
        hodm.ClearAfterOut("111");
        List ret = jdbc.queryForList("select * from temp where id=111");
        assertThat(ret.size(),is(0));
    }
    
    @Test
    public void test4(){
        hodm.GetIn("222", "南京站");
        assertThat(hodm.check("222"),is(true));
    }
}
