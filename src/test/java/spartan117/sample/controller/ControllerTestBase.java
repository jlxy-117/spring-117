package spartan117.sample.controller;

import spartan117.sample.controller.AllController;
import spartan117.sample.SpringBootTestBase;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * 所有controller测试类继承此类
 * @author yecq
 */
@SpringApplicationConfiguration(classes = AllController.class)
public class ControllerTestBase extends SpringBootTestBase {

    @Override
    protected int getPort() {
        return 9091;
    }
}
