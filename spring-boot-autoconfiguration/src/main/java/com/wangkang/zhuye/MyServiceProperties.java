package com.wangkang.zhuye;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:00 2019/2/15
 * @Modified By:
 */
@Data
@ConfigurationProperties(prefix = "spring101")
public class MyServiceProperties {
        /**

         * user name

         */

        private String name;

        /**

         * user age *Should between 1 and 120

         */

        private Integer age;

        /**

         * determine the service version you want use

         */

        private String version;
}
