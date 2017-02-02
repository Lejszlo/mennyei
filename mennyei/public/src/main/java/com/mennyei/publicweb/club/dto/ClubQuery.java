package com.mennyei.publicweb.club.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document
@Builder
@Data
public class ClubQuery {

	@Id
    private String id;

    private String name;
    
    private String fullName;

    private String urlName;

}
