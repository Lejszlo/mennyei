package com.mennyei.publicweb.club.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Identifiable;

import java.util.ArrayList;
import java.util.List;

@Document
@Builder
@Data
public class ClubQuery implements Identifiable<String> {

	@Id
    private String id;

    private String name;
    
    private String fullName;

    private String urlName;
    
    @Singular
    @DBRef(lazy = true)
    private List<PlayerQuery> players = new ArrayList<>();

}
