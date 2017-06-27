package payment.infra.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Configuration
public class ObjectMapperProducer {

  @Bean
  public ObjectMapper objectMapper(){
    return new ObjectMapper();
  }

}
