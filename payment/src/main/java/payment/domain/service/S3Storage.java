package payment.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import payment.domain.model.Payment;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class S3Storage {

  private final AmazonS3 s3;

  @Autowired
  public S3Storage(AmazonS3 s3) {
    this.s3 = s3;
  }

  public void storage(@NonNull Payment payment){

  }

}
