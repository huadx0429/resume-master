package master.resume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("master.resume")
//@EnableTransactionManagement
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
public class ResumeMasterApplication {

  public static void main(String[] args) {
      SpringApplication.run(ResumeMasterApplication.class, args);
  }
}
