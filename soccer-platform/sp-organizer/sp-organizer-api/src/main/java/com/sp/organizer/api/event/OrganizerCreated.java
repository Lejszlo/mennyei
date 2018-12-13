package com.sp.organizer.api.event;

import com.sp.organizer.api.value.OrganizerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class OrganizerCreated implements OrganizerEvent {

    OrganizerInfo organizerInfo;
}
