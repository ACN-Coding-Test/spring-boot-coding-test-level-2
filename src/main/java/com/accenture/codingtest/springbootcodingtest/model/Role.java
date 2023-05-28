package com.accenture.codingtest.springbootcodingtest.model;

public enum Role {
    PROJECT_OWNER("Project Owner", "ROLE_PROJECT_OWNER"),
    ADMIN("Admin", "ROLE_ADMIN"),
    TEAM_MEMBER("Team Member", "ROLE_TEAM_MEMBER");

    private final String roleName;
    private final String authority;

    Role(String roleName, String authority) {
        this.roleName = roleName;
        this.authority = authority;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getAuthority() {
        return authority;
    }
}
