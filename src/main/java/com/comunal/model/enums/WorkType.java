package com.comunal.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum WorkType {
    ELECTRIC(1L, "Електрині послуги"),
    PLUMBING(2L, "Сантехніні послуги"),
    GAS(3L, "Газопостачання"),
    LOCKSMITH_SERVICES(4L, "Слесарські роботи"),
    JOBBING(5L, "Дрібна робота"),
    HEATING(6L, "Опалювальні роботи");

    private static final Map<Long, WorkType> byId = new HashMap<Long, WorkType>();
    static {
        for (WorkType e : WorkType.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    public static WorkType getById(Long id) {
        return byId.get(id);
    }

    private Long id;
    private String name;

    WorkType(Long id, String name) {
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
