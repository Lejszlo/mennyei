package com.mennyei.publicweb.club.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Document
public class PlayerQuery {

    private String name;

    private Integer number;

    private String birthday;

}
