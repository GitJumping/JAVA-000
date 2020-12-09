package main.code;

import main.code.config.TxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TxConfig.class)
public class XAApplication {
    public static void main(String[] args) {
        SpringApplication.run(XAApplication.class, args);
    }
}
