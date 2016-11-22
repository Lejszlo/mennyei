package com.mennyei.publicweb.club.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Builder
@Value
public class ClubQuery {

    @Id
    private String id;

    private String shortName;

    private String fullName;

    @DBRef
    private Set<PlayerQuery> players;

}
