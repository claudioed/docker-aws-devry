package order.infra.aws;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Configuration
public class AwsProducers {

  @Bean
  public AmazonSNS amazonSNSClient() {
    return AmazonSNSClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).withRegion(
        Regions.US_EAST_1).build();
  }

  @Bean
  public AmazonSQS amazonSQSClient() {
    return AmazonSQSClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).withRegion(Regions.US_EAST_1).build();
  }

  @Bean
  public AmazonS3 amazonS3Client() {
    return AmazonS3ClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).withRegion(Regions.US_EAST_1).build();
  }

}
