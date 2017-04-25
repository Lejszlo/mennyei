package com.mennyei.configurations;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by lejsz on 2017. 04. 23..
 */
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mongodb";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("192.168.0.101:27017");
    }
}
