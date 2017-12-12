package com.sp.core.backend.web.resource;

public class IdResource {

    private String id;

    public IdResource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static IdResource of(String id) {
        return new IdResource(id);
    }
}
