package com.hajdu.sp.competition.update.value.match;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class MatchDocumentResourceFilter {

    List<String> matchIds;

    boolean played;

}
