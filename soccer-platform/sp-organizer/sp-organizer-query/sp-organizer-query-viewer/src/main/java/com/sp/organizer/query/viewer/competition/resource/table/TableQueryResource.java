package com.sp.organizer.query.viewer.competition.resource.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import com.sp.organizer.query.updater.competition.entity.TableRowQuery;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class TableQueryResource extends ResourceSupport {

    private List<TableRowQuery> rows;
}
