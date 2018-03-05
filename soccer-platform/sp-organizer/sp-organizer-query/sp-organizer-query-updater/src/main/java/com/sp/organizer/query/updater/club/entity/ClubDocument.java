package com.sp.organizer.query.updater.club.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Identifiable;

@Document
@Builder
@Data
public class ClubDocument implements Identifiable<String> {

	@Id
    private String id;

    private String name;
    
    private String fullName;

    private String urlName;
    
}
