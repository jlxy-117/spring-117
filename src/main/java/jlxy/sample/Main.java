package jlxy.sample;

import jlxy.sample.controller.AllController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author yecq
 */
@SpringBootApplication

public class Main {
     public static void main(String[] args) {
        // 或者用maven打包成jar，用java -jar XXX.jar启动
        SpringApplication.run(AllController.class, args);
    }
}
