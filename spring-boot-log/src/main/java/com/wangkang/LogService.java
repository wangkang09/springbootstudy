package com.wangkang;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:13 2018/12/17
 * @Modified By:
 */
@Service
@Slf4j
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    // private static final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(LogService.class);
    public void log1() {
        logger.error("error-log1");
        logger.info("info-log1");
        logger.debug("debug-log1");
        logger.warn("warn-log1");
        log.error("error-@slf4j-log1");
    }

    //log4j
    public void log2() {
//        log4j.error("error-log2");
//        log4j.info("info-log2");
//        log4j.debug("debug-log2");
//        log4j.warn("warn-log2");
    }

    public void log3() {
//        log.error("error-log3");
//        log.info("info-log3");
//        log.debug("debug-log3");
//        log.warn("warn-log3");
    }
}
