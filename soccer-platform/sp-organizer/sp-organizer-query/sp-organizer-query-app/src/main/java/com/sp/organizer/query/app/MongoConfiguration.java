package com.sp.organizer.query.app;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

@EnableConfigurationProperties
public class MongoConfiguration extends AbstractMongoConfiguration {

    private final MongoProperties mongoProperties;

    @Autowired
    public MongoConfiguration(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Override
    protected String getDatabaseName() {
        return "mongodb";
    }

    @Override
    public Mongo mongo() {
        return new MongoClient(mongoProperties.getFullPath());
    }
}
