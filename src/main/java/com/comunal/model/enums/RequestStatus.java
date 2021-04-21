package com.comunal.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum RequestStatus {
    NEW(1L, "На розгляді"),
    REJECTED(2L, "Відмова"),
    DONE(3L, "Готово");

    private static final Map<Long, RequestStatus> byId = new HashMap<Long, RequestStatus>();
    static {
        for (RequestStatus e : RequestStatus.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    public static RequestStatus getById(Long id) {
        return byId.get(id);
    }

    private Long id;
    private String name;

    RequestStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
