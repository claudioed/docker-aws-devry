package order.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import lombok.NonNull;
import lombok.SneakyThrows;
import order.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class S3Storage {

  private final AmazonS3 s3;

  private final String bucket;

  private final ObjectMapper objectMapper;

  @Autowired
  public S3Storage(AmazonS3 s3, @Qualifier("bucket") String bucket, ObjectMapper objectMapper) {
    this.s3 = s3;
    this.bucket = bucket;
    this.objectMapper = objectMapper;
  }

  @SneakyThrows
  public void storage(@NonNull Order order){
    final ObjectMetadata metadata = new ObjectMetadata();
    s3.putObject(new PutObjectRequest(
        this.bucket, order.getId(), new ByteArrayInputStream(this.objectMapper.writeValueAsString(order).getBytes()),metadata));
  }

}
