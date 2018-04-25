package com.sp.core.backend.web.resource;

import java.util.UUID;

public class IdResource {

    private UUID id;

    public IdResource(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static IdResource of(UUID id) {
        return new IdResource(id);
    }
}
