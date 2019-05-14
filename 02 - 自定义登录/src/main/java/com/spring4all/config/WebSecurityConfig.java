package com.spring4all.config;

import com.spring4all.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserRepository userRepository;

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("正在注册配置文件");
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        // 注册自定义的Filter
        //   filter是由先后顺序的，前面的匹配后，就不会调用后面的了
        http.addFilterAt(customFromLoginFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 自定义认证过滤器
     *   可以尝试把 /login 修改为 /login2，你还能通过前端的页面登录吗？
     *     如果不能，我们应该如何修正前端代码，试一下
     */
    private CustomFromLoginFilter customFromLoginFilter() {
        return new CustomFromLoginFilter("/login", userRepository);
    }

}
