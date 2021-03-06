package payment.infra.aws;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Configuration
public class AwsProducers {

  @Value("${payment.queue}")
  private String paymentQueue;

  @Value("${order.queue}")
  private String orderQueue;

  @Value("${payment.bucket}")
  private String bucket;

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

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).withRegion(Regions.US_EAST_1).build();
  }

  @Bean
  public DynamoDBMapper dynamoDBMapper() {
    return new DynamoDBMapper(amazonDynamoDB());
  }

  @Bean(name = "paymentQueue")
  public String paymentQueue(){
    return this.paymentQueue;
  }

  @Bean(name = "orderQueue")
  public String orderQueue(){
    return this.orderQueue;
  }

  @Bean(name = "bucket")
  public String bucket(){
    return this.bucket;
  }

}
