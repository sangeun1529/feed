package com.itsdcode.feed.format;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@ToString
public class KakaoFormat {

    private String method;
    private String url;
    private String authorization;
    private String receiverUuids;
    private String title;
    private String description;
    private String imageUrl;

    private int imageWidth;
    private int imageHeight;
    private String webUrl;
    private String mobileUrl;
    private int likeCount;
    private int commentCount;
    private int sharedCount;

    public Map<String,String> kakaoJsonString() {
        String templeteObj = "{\"object_type\": \"feed\"," +
                "\"content\": {" +
                "\"title\": %s," +
                "\"description\": %s," +
                "\"image_url\": %s," +
                "\"image_width\": %d," +
                "\"image_height\": %d," +
                "\"link\": {" +
                "\"web_url\": %s," +
                "\"mobile_web_url\": %s," +
                "}}," +
                "\"social\": {" +
                "\"like_count\": %d," +
                "\"comment_count\": %d," +
                "\"shared_count\": %d," +
                "}," +
                "\"buttons\": [{" +
                "\"title\": \"웹으로 이동\"," +
                "\"link\": {" +
                "\"web_url\": %s," +
                "\"mobile_web_url\": %s" +
                "}},}]}";
        templeteObj = String.format(templeteObj, title, description, imageUrl, imageWidth, imageHeight,
                webUrl, webUrl, likeCount, commentCount, sharedCount, webUrl, webUrl);


        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("method", method);
        hashMap.put("url", url);
        hashMap.put("authorization", authorization);
        hashMap.put("receiver_uuids", receiverUuids);
        hashMap.put("template_object", templeteObj);

        return hashMap;
    }

}


