package com.cenmo.mogu.portal.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Cenmo
 * @Date 2020-10-09 2020/10/9
 */
@Configuration
//@EnableWebMvc
public class mvcConfig implements WebMvcConfigurer {

    /**
     * 直接添加映射路径
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/jsp/index");
    }

    @Bean
    public GlobalExceptionDisposer globalExceptionHandler(){
        // 全局异常处理类
        return new GlobalExceptionDisposer();
    }

    /**
     * 配置jsp视图解析器
     * @return
     */
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver(){
////        // 视图解析器：controller中返回的视图将自动添加以下前缀和后缀
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//////        internalResourceViewResolver.setPrefix("");
//        internalResourceViewResolver.setSuffix(".jsp");
//        return internalResourceViewResolver;
//    }

    /**
     * 配置资源映射
     * @param
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/test/**") // 配置mapping
//                .addResourceLocations("classpath:/static/test/")
//                .setCachePeriod(31556926);
//    }

    /**
     * 配置json格式数据消息转化器，@Bean方式
     * 也可以@EnableWebMvc+重写configureMessageConverters方法，但加了@EnableWebMvc注解后静态资源无法访问
     * @return
     */
    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverters(){

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        // 配置序列化进行格式化json数据
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //全局时间配置
//        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        config.setCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(config);

        // 解决中文乱码
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.valueOf("application/json;charset=UTF-8"));
//        converter.setSupportedMediaTypes(fastMediaTypes);

        return new HttpMessageConverters(converter);
    }

}
