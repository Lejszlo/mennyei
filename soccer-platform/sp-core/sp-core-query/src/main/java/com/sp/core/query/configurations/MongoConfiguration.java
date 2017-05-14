package com.sp.core.query.configurations;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by lejsz on 2017. 04. 23..
 */
@EnableConfigurationProperties
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return "mongodb";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoProperties.getFullPath());
    }
}
