package com.spicis;

import com.spicis.logger.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@MapperScan(basePackages = {"com.spicis.dao"})
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(scanBasePackages = "com.spicis")
@EnableScheduling
public class ApplicationMain {

    public static void main(String[] args) {
        LogFactory.getInfoLogger().logInfo("========== start log ==========");
        try {
            SpringApplication.run(ApplicationMain.class, args);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("ApplicationMain exception", e);
        }
    }
}
