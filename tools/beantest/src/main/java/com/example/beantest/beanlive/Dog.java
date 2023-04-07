package com.example.beantest.beanlive;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisBetterConfigImpl;

import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Component
@Scope
public class Dog {

    public String name;


    public Dog() {
        System.out.println(name);
        System.out.println("dog constructor...");
    }

    // 在对象创建完成并且属性赋值完成之后调用
    @PostConstruct
    public void init() { // 在这儿打个断点调试一下
        System.out.println("dog...@PostConstruct...");
    }

    // 在容器销毁（移除）对象之前调用
    @PreDestroy
    public void destory() {
        System.out.println("dog...@PreDestroy...");
    }





//    @Autowired
//    private WeChatMiniProgramAccountDTO accountDTO;
//
//
//    @Resource
//    private StringRedisTemplate redisTemplate;
//    public RedisTemplateWxRedisOps redisTemplateWxRedisOps() {
//        return new RedisTemplateWxRedisOps(redisTemplate);
//    }
//
//    public WxMaService initMiniProgramService() {
//        WxMaService wxMaService = new WxMaServiceImpl();
//        WxMaRedisBetterConfigImpl config = new WxMaRedisBetterConfigImpl(redisTemplateWxRedisOps(), "MINI_PROGRAM_TOKEN_PREFIX");
//        config.setAppid(accountDTO.getAppId());
//        config.setSecret(accountDTO.getAppSecret());
//        config.setToken(accountDTO.getToken());
//        config.setAesKey(accountDTO.getAesKey());
//        wxMaService.setWxMaConfig(config);
//        return wxMaService;
//    }
//
//    @Bean
//    public WxMaService wxMaService() {
//        return initMiniProgramService();
//    }

}
