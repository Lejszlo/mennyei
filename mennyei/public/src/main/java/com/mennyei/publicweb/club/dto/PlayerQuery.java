package com.mennyei.publicweb.club.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Document
@Builder
@Value
public class PlayerQuery {

    @Id
    private String id;

    private String name;

    private Integer number;
    
    private Integer age;

    private String birthday;

}
