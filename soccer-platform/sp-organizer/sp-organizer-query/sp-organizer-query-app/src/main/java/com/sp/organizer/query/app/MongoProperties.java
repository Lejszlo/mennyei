package com.sp.organizer.query.app;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mongo")
@Component
@Getter
@Setter
public class MongoProperties {

    @NotBlank
    private String port;

    @NotBlank
    private String host;

    public String getFullPath() {
        return String.format("%s:%s", this.host, this.port);
    }
}
