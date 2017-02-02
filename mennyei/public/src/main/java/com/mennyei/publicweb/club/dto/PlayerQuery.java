package com.mennyei.publicweb.club.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Document
@Builder
@Data
public class PlayerQuery {

    @Id
    private String id;

    private String name;

    private Integer number;
    
    private Integer age;

    private String birthday;
    
    private String nationality;
    
    @DBRef
    private ClubQuery club;

}
