package spartan117.sample.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import spartan117.sample.controllerAdmin.AdminLoginController;
import spartan117.sample.controllerAdmin.CheckLoginInfoController;
import spartan117.sample.controllerAdmin.AdminUserController;

/**
 *
 * @author yecq
 */
@Import({LoggedController.class, SimpleController.class, UserController.class, HalfOrderMySQLController.class,AdminLoginController.class,CheckLoginInfoController.class,AdminUserController.class,PhoneCheckController.class})
@ImportResource("classpath:applicationContext.xml")
public class AllController {

}
