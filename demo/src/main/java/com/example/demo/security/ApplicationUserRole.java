package com.example.demo.security;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ , STUDENT_WRITE , COURSES_READ , COURSES_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ  , COURSES_READ ));

    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
