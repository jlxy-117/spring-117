package spartan117.sample.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import spartan117.sample.controllerAdmin.userController;

/**
 *
 * @author yecq
 */
@Import({LoggedController.class, SimpleController.class, UserController.class, HalfOrderMySQLController.class,userController.class})
@ImportResource("classpath:applicationContext.xml")
public class AllController {

}
