
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TestHttpGetClient {
    public static void main(String[] args) {

        HttpGet httpGet = new HttpGet("http://localhost:8801");
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {

            System.out.println("状态是:" + response.getStatusLine());
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                System.out.println("内容为:" + EntityUtils.toString(responseEntity, "UTF-8"));
            }
        } catch (IOException e) {
            System.out.println("error is :" + response.getStatusLine());
            e.printStackTrace();
        }
    }
}
