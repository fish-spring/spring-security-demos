package com.spring4all.config;

import com.alibaba.fastjson.JSONObject;
import com.spring4all.entity.User;
import com.spring4all.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义表单登录
 * 当然你也可以把这个类注册为Bean，不过记得给它无参构造函数
 *   然后这是个课后练习题，记得完成
 *
 * 首先他继承了GenericFilter，所以所在filter链中存在
 *   然后它继承了AbstractAuthenticationProcessingFilter，会让它处理登录认证的问题
 */
@Slf4j
public class CustomFromLoginFilter extends AbstractAuthenticationProcessingFilter {
    private UserRepository userRepository;

    /**
     *
     * @param defaultFilterProcessesUrl  只对匹配的路由做过滤处理
     * @param userRepository  查询用户信息的接口
     */
    CustomFromLoginFilter(String defaultFilterProcessesUrl, UserRepository userRepository) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.userRepository = userRepository;
    }

    /**
     * 用户登录时，经过当前过滤器则会调用这个方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 登录认证的信息
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException{
        log.info("当前url是: " + httpServletRequest.getRequestURL());
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        log.info(String.format("用户尝试登录username: %s, password: %s", username, password));

        log.info(JSONObject.toJSONString(userRepository));

        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null){
            log.warn("未查询到匹配用户用户");

            // 通过这个类的构造函数可以知道，只有两个参数时： authenticated == false
            return new UsernamePasswordAuthenticationToken(null, null);
        }
        log.info("查询到用户：" + user.toString());

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(username, password, simpleGrantedAuthorities);
    }


}
