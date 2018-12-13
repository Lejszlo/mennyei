package com.sp.organizer.api.command;

import com.sp.organizer.api.value.OrganizerInfo;
import lombok.*;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CreateOrganizerCommand extends OrganizerCommand {

    @NonNull
    OrganizerInfo organizerInfo;

}
