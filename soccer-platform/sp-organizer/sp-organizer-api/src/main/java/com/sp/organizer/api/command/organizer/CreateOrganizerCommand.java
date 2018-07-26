package com.sp.organizer.api.command.organizer;

import com.sp.organizer.api.value.organizer.OrganizerInfo;
import lombok.*;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CreateOrganizerCommand extends OrganizerCommand {

    @NonNull
    OrganizerInfo organizerInfo;

}
