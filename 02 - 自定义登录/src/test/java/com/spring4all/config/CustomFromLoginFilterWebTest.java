package com.spring4all.config;

import com.spring4all.ApplicationTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;

public class CustomFromLoginFilterWebTest extends ApplicationTest {

    @Test
    public void attemptAuthentication() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/login")
                .param("username", "Jon")
                .param("password", "123456");

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print());
    }
}