package spartan117.sample.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author yecq
 */
@Import({LoggedController.class, SimpleController.class, UserController.class, HalfOrderMySQLController.class})
@ImportResource("classpath:applicationContext.xml")
public class AllController {

}
