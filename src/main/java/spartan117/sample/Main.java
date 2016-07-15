package spartan117.sample;

import spartan117.sample.controller.AllController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *
 * @author yecq
 */
@SpringBootApplication
@EnableRedisHttpSession
public class Main {
     public static void main(String[] args) {
        // 或者用maven打包成jar，用java -jar XXX.jar启动
        SpringApplication.run(AllController.class, args);
    }
}
