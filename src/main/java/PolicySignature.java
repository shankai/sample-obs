import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PolicySignature {
    public static String signature(String policyBase64, String sk) throws NoSuchAlgorithmException, InvalidKeyException {

        // signature
        SecretKeySpec signingKey = new SecretKeySpec(sk.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);

        byte[] macResult = mac.doFinal(policyBase64.getBytes(StandardCharsets.UTF_8));
        String sigResult = Base64.getEncoder().encodeToString(macResult);
//        System.out.println(sigResult);
        return sigResult;

    }

    public static String policyBase64(final String bucketName, final String objectKey) throws JsonProcessingException {
        // EXPIRE TIME
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 10);// 10分钟后的时间
        String expire = sf.format(nowTime.getTime());
//        System.out.println(expire);

        // POLICY
        Map<String, Object> policy = new HashMap<>();
        policy.put("expiration", expire);

        Map<String, String> bucket = new HashMap<>();
        bucket.put("bucket", bucketName);

        String[] acl = new String[]{"eq", "$x-obs-acl", "public-read-write"};
        String[] targetkey = new String[]{"eq", "$key", objectKey};

        List<Object> items = new ArrayList<Object>();
        items.add(bucket);
        items.add(acl);
        items.add(targetkey);

        policy.put("conditions", items);

        // TO STRING
        ObjectMapper objectMapper = new ObjectMapper();
        String policyStr = objectMapper.writeValueAsString(policy);
//        System.out.println(policyStr);

        String policyBase64 = Base64.getEncoder().encodeToString(policyStr.getBytes(StandardCharsets.UTF_8));
//        System.out.println(policyBase64);

        return policyBase64;
    }
}
