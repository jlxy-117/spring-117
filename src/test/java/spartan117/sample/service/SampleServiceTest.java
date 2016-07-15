package spartan117.sample.service;

import spartan117.sample.service.SampleService;
import java.util.List;
import java.util.Map;
import spartan117.sample.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author yecq
 */
public class SampleServiceTest extends TestBase {

    @Autowired
    private SampleService ss;

    @Test
    public void test_add() {
        int a = 3 + 4;
        assertThat(a, is(7));
    }

    @Test
    public void test_multi() {
        double b = 3.0 * 4.1;
        assertThat(b, closeTo(12.3, 1));
    }

    // 测试抛出异常
    @Test
    public void test_exception() {
        this.expectedEx.expect(IllegalStateException.class);
        this.expectedEx.expectMessage("产生未知错误");
        ss.exception();
    }

    // 测试数据库的各个行为
    @Test
    public void test_getStudentList() {
        List list = ss.getStudentList();
        assertThat(list.size(), is(2));
        Map stu1 = (Map) list.get(0);
        assertThat(stu1.get("id")+"", is("2"));
        assertThat(stu1.get("name")+"", is("小明"));
        assertThat(stu1.get("depart")+"", is("信工"));
    }

    @Test
    public void test_addStudent() {
        ss.addStudent("00004", "李小华", "商学院");
        List ret = this.jdbc.queryForList("select * from student");
        assertThat(ret.size(), is(3));
    }
}
