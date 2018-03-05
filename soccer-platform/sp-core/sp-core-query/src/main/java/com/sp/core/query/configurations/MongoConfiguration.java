package com.sp.core.query.configurations;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mongodb";
    }

    @Override
    public Mongo mongo() {
        return new MongoClient("192.168.0.101:27017");
    }
}
