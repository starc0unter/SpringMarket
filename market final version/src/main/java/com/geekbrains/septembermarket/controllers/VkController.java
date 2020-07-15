package com.geekbrains.septembermarket.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/vk")
public class VkController {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("7194773")
    private String clientId;

    @Value("tO7yreTAsITtbqPhei5x")
    private String clientSecret;

    @GetMapping("/callback")
    @ResponseBody
    public String callback(@RequestParam(name = "code") String code) {
        String requestUrl = "https://oauth.vk.com/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&redirect_uri=http://localhost:8189/market/vk/callback&code=" + code;
        ResponseEntity<String> accessTokenEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(accessTokenEntity.getBody()).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();

        String userIds = jsonObject.get("user_id").toString();
        System.out.println(userIds);
//        String getFriendsRequest = "https://api.vk.com/method/friends.get?user_ids=" + userIds + "&access_token=" + accessToken + "&v=5.103";
        String getNameRequest = "https://api.vk.com/method/users.get?user_ids=" + userIds + "&access_token=" + accessToken + "&fields=last_name&v=5.103";
        ResponseEntity<String> userInfoEntity = restTemplate.exchange(getNameRequest, HttpMethod.GET, null, String.class);
        System.out.println(userInfoEntity.getBody());
        JsonObject jsonUserInfo = parser.parse(userInfoEntity.getBody()).getAsJsonObject().get("response").getAsJsonArray().get(0).getAsJsonObject();

        return "Вы зашли как: " + jsonUserInfo.get("last_name").getAsString() + " " + jsonUserInfo.get("first_name").getAsString();
    }

    @GetMapping("/")
    public String showVkPage(Model model) {
        model.addAttribute("oauthUrl", "https://oauth.vk.com/authorize?client_id=" + clientId + "&display=page&redirect_uri=http://localhost:8189/market/vk/callback&scope=friends&response_type=code&v=5.103");
        return "vk-demo";
    }
}
