package be.iccbxl.pid.youforbel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

// @Configuration = classe de configuration Spring (chargée au démarrage)
@Configuration
public class ThymeleafConfiguration {

    // @Bean = objet créé et géré par Spring (injectable si besoin)
    @Bean
    public LayoutDialect thymeleafDialect() {
        // Active le système de layouts Thymeleaf Layout Dialect
        return new LayoutDialect();
    }
}