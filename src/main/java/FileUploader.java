import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {
  public static void main(String[] args)
          throws IOException, NoSuchAlgorithmException, InvalidKeyException, ServerException, InsufficientDataException, ErrorResponseException, InvalidResponseException, XmlParserException, InternalException {
    try {
      // Create a minioClient with the MinIO server playground, its access key and secret key.
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint("http://obs.cn-north-4.myhuaweicloud.com")
              .credentials("TTEZE3JFWYJPCWY1KAB2", "dI3VwGMnwZ18msKnnkuNkm8cWNFW42ZKg20uekac")
              .build();

      // Make 'asiatrip' bucket if not exist.
      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket("icos-twin-camera").build());
      if (!found) {
        // Make a new bucket called 'asiatrip'.
        minioClient.makeBucket(MakeBucketArgs.builder().bucket("icos-twin-camera").build());
      } else {
        System.out.println("Bucket 'icos-twin-camera' already exists.");
      }

      // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
      // 'asiatrip'.
      minioClient.uploadObject(
          UploadObjectArgs.builder()
              .bucket("icos-twin-camera")
              .object("/1/2/WechatIMG790.jpeg")
              .filename("/Users/shankai/Desktop/WechatIMG790.jpeg")
              .build());
      System.out.println(
          "/Users/shankai/Desktop/WechatIMG790.jpeg is successfully uploaded as "
              + "object 'WechatIMG790.jpeg' to bucket 'icos-twin-camera'.");
    } catch (MinioException e) {
      e.printStackTrace();
//      System.out.println("Error occurred: " + e);
//      System.out.println("HTTP trace: " + e.httpTrace());
    }
  }
}