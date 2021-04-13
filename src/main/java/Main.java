import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class Main {
    static String SK = "dI3VwGMnwZ18msKnnkuNkm8cWNFW42ZKg20uekac";
    static String objectKey = "tile39proxy.yml";

    // canonical_string = '{ "expiration": "2021-04-21T12:00:00.000Z", "conditions": [ { "bucket": "icos-registry" }, [ "eq", "$x-obs-acl", "public-read-write" ], [ "eq", "$key", "tile39proxy.yml" ] ] }'

    public static void main(String[] args) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {

        String policyBase64 = PolicySignature.policyBase64("icos-registry", objectKey);
        String signatureBase64 =  PolicySignature.signature(policyBase64, SK);

        System.out.println(policyBase64);
        System.out.println(signatureBase64);

    }
}
