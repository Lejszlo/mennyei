package com.mennyei.publicweb.club.dto;

import java.util.HashSet;
import java.util.Set;

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

    private String shortName;

    private String fullName;

    private String urlName;

    @DBRef
    @Singular
    private Set<PlayerQuery> players = new HashSet<>();

}
