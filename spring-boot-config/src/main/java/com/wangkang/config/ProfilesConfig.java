package com.wangkang.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:43 2018/12/25
 * @Modified By:
 */
@Service
@Profile("dev")
@Data
public class ProfilesConfig {

    @Value("${profiles}")
    private String profiles;
}
