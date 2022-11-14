package com.ilearning.common.canal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConfigurationProperties(prefix = "canal")
@Data
public class CanalInstanceProperties {


    /**
     * canal server地址
     */
    private String serverAddress;

    /**
     * canal server端口
     */
    private Integer serverPort;

    /**
     * canal 监听实例
     */
    private Set<String> instance;


}
