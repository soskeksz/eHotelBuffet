package com.codecool.ehotel.model;

import java.util.Random;

public enum FullName {
    JOHN_DOE("John Doe"),
    JANE_SMITH("Jane Smith"),
    MICHAEL_JOHNSON("Michael Johnson"),
    EMILY_WILLIAMS("Emily Williams"),
    DAVID_BROWN("David Brown"),
    OLIVIA_JONES("Olivia Jones"),
    WILLIAM_TAYLOR("William Taylor"),
    SOPHIA_ANDERSON("Sophia Anderson"),
    JAMES_MARTINEZ("James Martinez"),
    ISABELLA_THOMPSON("Isabella Thompson");

    private final String fullName;
    private static final Random PRNG = new Random();

    FullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }
    public static FullName getRandomName()  {
        FullName[] types = values();
        return types[PRNG.nextInt(types.length)];
    }
}
