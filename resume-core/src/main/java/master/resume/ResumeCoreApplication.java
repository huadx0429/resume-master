package master.resume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("master.resume.mapper")
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
@EnableAsync
public class ResumeCoreApplication {

  public static void main(String[] args) {
      SpringApplication.run(ResumeCoreApplication.class, args);
  }
}
