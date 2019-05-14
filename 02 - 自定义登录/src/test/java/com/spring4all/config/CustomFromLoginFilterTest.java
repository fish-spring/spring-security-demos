package com.spring4all.config;

import com.alibaba.fastjson.JSONObject;
import com.spring4all.ApplicationTest;
import com.spring4all.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import static org.junit.Assert.*;

public class CustomFromLoginFilterTest extends ApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void wrongUser() throws Exception{
        doRequest("Jon", "123", "/user");
    }

    /**
     * 正确的用户名密码，将会被其中的Filter认证
     * @throws Exception
     */
    @Test
    public void rightUser() throws Exception{
        doRequest("Jon", "123456", "/user");
    }

    @Test
    public void otherUrl() throws Exception{
        doRequest("Jon", "123456", "/aaaaabbbbb");
    }

    /**
     * 这个filter的url匹配规则不会生效，因为它是被我们直接调用测试的
     *   没有经过servlet容器
     * @param username
     * @param password
     * @param url
     * @throws Exception
     */
    private void doRequest(String username, String password, String url) throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.example.com");
        request.setRequestURI(url);
        request.setParameter("username", username);
        request.setParameter("password", password);


        MockHttpServletResponse response = new MockHttpServletResponse();

        CustomFromLoginFilter filter = new CustomFromLoginFilter("/login",userRepository);
        Authentication authentication = filter.attemptAuthentication(request, response);

        System.out.println(JSONObject.toJSONString(authentication));
        // {"authenticated":true,"authorities":[{"authority":"USER"}],
        //    "credentials":"123456","name":"Jon","principal":"Jon"}

    }
}