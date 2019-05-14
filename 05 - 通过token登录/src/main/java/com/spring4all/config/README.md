SS的安全认证就是一个个filter联系起来的
HttpSecurity内部维护了一个fileter数组
每个filter都应该继承抽象了然后实现方法

AbstractAuthenticationProcessingFilter

有个抽象方法
public abstract Authentication attemptAuthentication(HttpServletRequest var1, HttpServletResponse var2) throws AuthenticationException, IOException, ServletException;
就看它了


有一个自带的子类，可以通过
UsernamePasswordAuthenticationFilter
    public UsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }
    
    getRequestParam
    getReq
实现的时候默认匹配这里

其实我也是不太喜欢这种，要是
不看源代码，也不知道他为什么就这样了

这个类默认

然后每个filter都有一个匹配规则，这是坑定的，越在前面的优先级
越高。。