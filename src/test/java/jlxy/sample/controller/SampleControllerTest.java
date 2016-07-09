package jlxy.sample.controller;

import java.util.List;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author yecq
 */
public class SampleControllerTest extends ControllerTestBase {

    @Test
    public void test_do_getStudentList() {
        String url = getUrlPrefix() + "/get_student_list";
        List ret = this.rest.postForObject(url, null, List.class);
        assertThat(ret.size(), is(2));
    }
}
