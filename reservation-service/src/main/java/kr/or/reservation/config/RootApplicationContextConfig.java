package kr.or.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 여기서 Service / DAO에 대한 정보를 알려주면 된다.   
// DataSource , Transaction 에 대한 설정들 ....... 
@Configuration
@ComponentScan(basePackages = {
        "kr.or.reservation.dao",
        "kr.or.reservation.service",
        "kr.or.reservation.serviceImpl"
})
@Import({DbConfig.class}) // DBConfig 를 설정한다.
public class RootApplicationContextConfig {
}