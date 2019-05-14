package com.spring4all.config;

import com.alibaba.fastjson.JSONObject;
import com.spring4all.ApplicationTest;
import com.spring4all.service.UserService;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CustomJSONLoginFilterTest extends ApplicationTest {
    @Autowired
    private UserService userService;

    @Test
    public void attemptAuthentication() throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.example.com");
        request.setRequestURI("/user");

        Map<String, String> map = new HashMap<>();
        map.put("username", "Jon");
        map.put("password", "123456");
        request.setContent(JSONObject.toJSONString(map).getBytes());

        MockHttpServletResponse response = new MockHttpServletResponse();

        CustomJSONLoginFilter filter = new CustomJSONLoginFilter("/user",userService);
        Authentication authentication = filter.attemptAuthentication(request, response);

        System.out.println(JSONObject.toJSONString(authentication));
        // {"authenticated":true,"authorities":[{"authority":"USER"}],
        //    "credentials":"123456","name":"Jon","principal":"Jon"}

    }
}
