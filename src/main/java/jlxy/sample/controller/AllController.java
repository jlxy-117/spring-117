package jlxy.sample.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author yecq
 */
@Import({SampleController.class, HalfOrderController.class})
@ImportResource("classpath:applicationContext.xml")
public class AllController {

}
