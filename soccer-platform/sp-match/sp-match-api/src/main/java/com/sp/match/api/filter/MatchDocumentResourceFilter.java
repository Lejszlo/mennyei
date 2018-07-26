package com.sp.match.api.filter;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class MatchDocumentResourceFilter {

    List<String> matchIds;

    boolean played;

}
