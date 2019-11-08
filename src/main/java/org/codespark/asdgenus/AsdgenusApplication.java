package org.codespark.asdgenus;

import org.codespark.asdgenus.utils.FileStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperty.class})
public class AsdgenusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsdgenusApplication.class, args);
    }

}
