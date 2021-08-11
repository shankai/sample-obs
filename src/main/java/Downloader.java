import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Downloader {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://icos-registry.obs.cn-north-4.myhuaweicloud.com/hk_isvd/2VT842-E79336772/0b208ae0-fbab-4e87-a55b-c67980c35383")
                .method("GET", null)
                .addHeader("Authorization", "OBS TTEZE3JFWYJPCWY1KAB2:nj7VLIDC3ape9TGOkN4TR7jFZvQ=")
                .addHeader("Date", "Wed, 14 Apr 2021 06:16:23 GMT")
                .build();
        Response response = client.newCall(request).execute();
        byte[] ss = response.body().bytes();
        bytesToFile(ss, "./", "a.jpeg");
    }

    public static File bytesToFile(byte[] bytes, String outPath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(outPath);
            if (!dir.exists() && dir.isDirectory()) { //判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(outPath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }
}
