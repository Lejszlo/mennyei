package com.sp.organizer.api.value;

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
