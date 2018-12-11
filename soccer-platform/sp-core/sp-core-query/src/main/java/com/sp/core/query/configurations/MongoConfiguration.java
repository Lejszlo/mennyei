package com.sp.core.query.configurations;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mongodb";
    }

    @Override
    public Mongo mongo() {
        return new MongoClient("192.168.1.2:27017");
    }
}
