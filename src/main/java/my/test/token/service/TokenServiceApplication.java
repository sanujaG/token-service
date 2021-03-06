package my.test.token.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Sanuja_G
 *   @CreatedTime on 19-10-2021 04:32:22 PM
 */

@EnableEurekaClient
@SpringBootApplication
public class TokenServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TokenServiceApplication.class, args);
    }
}
