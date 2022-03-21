package com.example.demo.utils;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.response.PostPhoneAuthRes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.demo.config.BaseResponseStatus.FAILED_TO_SEND_PHONE_AUTH;
import static com.example.demo.config.Constant.*;

/**
 * sens 가이드 : https://api.ncloud-docs.com/docs/ai-application-service-sens-smsv2#%EB%A9%94%EC%8B%9C%EC%A7%80%EB%B0%9C%EC%86%A1
 */

@Service
public class SmsAuthService {
    public PostPhoneAuthRes sendPhoneAuth(String toPhone) throws BaseException {
        int authNumber = (int) (Math.random() * (99999 - 10000 + 1)) + 10000; // 인증번호 난수 생성
        String accessKey = ACCESS_KEY;
        String serviceId = SERVICE_ID;
        String method = "POST";
        String timestamp = Long.toString(System.currentTimeMillis());

        String requestURL = "https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages";

        JSONObject bodyJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();

        toJson.put("to", toPhone); // 메시지 수신자
        toArr.add(toJson);

        bodyJson.put("type", "sms");
        bodyJson.put("contentType", "comm");
        bodyJson.put("countryCode", "82");
        bodyJson.put("from", SENDER_NUMBER);
        bodyJson.put("content", "idus 인증번호\n" + authNumber);
        bodyJson.put("messages", toArr);

        String body = bodyJson.toJSONString();
//        System.out.println("body: : " + body);

        try {
            URL url = new URL(requestURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json; charset=utf-8");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(timestamp));
            con.setRequestMethod(method); // POST 방식
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
//            System.out.println("responseCode : " + responseCode);
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            con.disconnect();
            if (responseCode != 202) { // 정상이 아니라면
                throw new BaseException(FAILED_TO_SEND_PHONE_AUTH);
            }
            return new PostPhoneAuthRes(toPhone, String.valueOf(authNumber));
        } catch (Exception ignored) {
            throw new BaseException(FAILED_TO_SEND_PHONE_AUTH);
        }
    }
    private static String makeSignature(String timestamp) throws BaseException {
        String encodeBase64String = "";
        String space = " "; // one space
        String newLine = "\n"; // new line
        String method = "POST";
        String url = String.format("/sms/v2/services/%s/messages", SERVICE_ID);
        String secretKey = SECRET_KEY;

        String message = new StringBuilder().append(method).append(space).append(url).append(newLine)
                .append(timestamp).append(newLine).append(ACCESS_KEY).toString();

        try {
            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = java.util.Base64.getEncoder().encodeToString(rawHmac);
            return encodeBase64String;
        } catch (Exception ignored) {
            throw new BaseException(FAILED_TO_SEND_PHONE_AUTH);
        }
    }
}