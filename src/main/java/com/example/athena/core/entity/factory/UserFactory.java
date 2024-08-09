package com.example.athena.core.entity.factory;

import com.example.athena.core.entity.*;

public class UserFactory {
    public static User createAdmin(
            String name,
            String lastName,
            String email,
            String password)
    { return new Admin(name, lastName, password, email); }

    public static User createManager(
            String name,
            String lastName,
            String email,
            String password
    ){ return new Manager(name, lastName, password, email); }

    public static User createCollaborator(
            String name,
            String lastName,
            String email,
            String password
    ){ return new Collaborator(name, lastName, password, email); }

    public static User createIntern(
            String name,
            String lastName,
            String email,
            String password
    ){ return new Intern(name, lastName, password, email); }

}
