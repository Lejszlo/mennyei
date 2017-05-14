package com.sp.core.query.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by lejsz on 2017. 04. 27..
 */
@ConfigurationProperties(prefix = "mongo")
@Component
@Getter
@Setter
public class MongoProperties {

    private String port;

    private String host;

    public String getFullPath() {
        return String.format("%s:%s", host, port);
    }
}
