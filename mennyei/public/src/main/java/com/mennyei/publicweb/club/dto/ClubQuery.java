package com.mennyei.publicweb.club.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Document
@Builder
@Data
public class ClubQuery {

	@Id
    private String id;

    private String name;
    
    private String fullName;

    private String urlName;
    
    @Singular
    @DBRef
    private List<PlayerQuery> players;

}
