package com.iflytek.message.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author llchen12
 * @date 2018/5/11
 */
@Configuration
@Slf4j
public class AppConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //config.setCharset(Charset.forName("UTF-8"));
        config.setFeatures(Feature.SupportNonPublicField);
        config.setSerializerFeatures(SerializerFeature.SkipTransientField);
        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        log.info("fastjson注入成功");
        converters.add(fastJsonHttpMessageConverter);
    }
}
