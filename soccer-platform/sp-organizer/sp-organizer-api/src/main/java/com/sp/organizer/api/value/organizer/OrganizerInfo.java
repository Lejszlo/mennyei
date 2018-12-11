package com.sp.organizer.api.value.organizer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class OrganizerInfo {

    String name;

    String phoneNumber;

    String email;
}
