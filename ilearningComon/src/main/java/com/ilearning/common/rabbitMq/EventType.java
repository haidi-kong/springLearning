package com.ilearning.common.rabbitMq;

public enum EventType  {
    INSERT("INSERT", 1),
    UPDATE("UPDATE", 2),
    DELETE("DELETE", 3),
    CREATE("CREATE", 4),
    ALTER("ALTER", 5),
    ERASE("ERASE", 6),
    QUERY("QUERY", 7),
    TRUNCATE("TRUNCATE", 8),
    RENAME("RENAME", 9),
    CINDEX("CINDEX", 10),
    DINDEX("DINDEX", 11),
    GTID("GTID", 12),
    XACOMMIT("XACOMMIT", 13),
    XAROLLBACK("XAROLLBACK", 14),
    MHEARTBEAT("MHEARTBEAT", 15);

    private final String typeName;
    private final int value;



    private EventType(String typeName, int value) {
        this.typeName = typeName;
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getValue() {
        return value;
    }

    public static EventType getEventByTypeName(String typeName) {
        for (EventType eventType : EventType.values()) {
            if (eventType.getTypeName().equals(typeName)) {
                return eventType;
            }
        }
        return null;
    }
}
