package com.sp.organizer.api.event.organizer;

import com.sp.organizer.api.value.organizer.OrganizerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class OrganizerCreated implements OrganizerEvent {

    OrganizerInfo organizerInfo;
}
