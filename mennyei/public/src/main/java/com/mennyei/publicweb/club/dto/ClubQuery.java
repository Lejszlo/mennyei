package com.mennyei.publicweb.club.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Value
public class ClubQuery {

    @Id
    private String id;

}
