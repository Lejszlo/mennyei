package com.hajdu.sp.competition.update.value.competition.organizer;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Organizer {

    @NonNull
    String name;

    @NonNull
    String phoneNumber;

    @NonNull
    String email;

}
