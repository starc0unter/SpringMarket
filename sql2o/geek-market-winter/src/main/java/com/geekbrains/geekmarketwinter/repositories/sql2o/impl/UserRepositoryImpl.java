package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.entites.Role;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.UserRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.geekbrains.geekmarketwinter.entites.User;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepositorySql2o {
    private final Sql2o sql2o;

    private static final String SELECT_USER_QUERY = "select id, username, password, first_name, last_name, email from users where username = :u_name";
    private static final String SAVE_USER = "insert into users (username, password, first_name, last_name, email) values (:username, :password, :first_name, :last_name, :email)";
    private static final String SAVE_ROLES = "insert into users_roles (user_id, role_id) values (:user_id, :role_id)";
    private static final String GET_ROLES = "select r.id, r.name from roles r join users_roles u on r.id = u.role_id where user_id = :user_id";


    @Autowired
    public UserRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public User getUser(@NonNull String userName) {
        try (Connection connection = sql2o.open()) {
            final User user = connection.createQuery(SELECT_USER_QUERY, false)
                    .addParameter("u_name", userName)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(User.class);

            return setupRoles(connection, user);
        }
    }

    public void save(@NonNull User user) {
        try (Connection connection = sql2o.open()) {
            final Long id = connection.createQuery(SAVE_USER, true)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .addParameter("username", user.getUserName())
                    .addParameter("password", user.getPassword())
                    .addParameter("first_name", user.getFirstName())
                    .addParameter("last_name", user.getLastName())
                    .addParameter("email", user.getEmail())
                    .executeUpdate()
                    .getKey(Long.class);

            user.getRoles().forEach(role -> saveRoles(connection, id, role));
        }
    }

    private void saveRoles(@NonNull Connection connection, @NonNull Long userId, @NonNull Role role) {
        connection.createQuery(SAVE_ROLES, true)
                .addParameter("user_id", userId)
                .addParameter("role_id", role.getId())
                .executeUpdate();
    }

    private User setupRoles(@NonNull Connection connection, @NonNull User user) {
        final List<Role> roles = connection.createQuery(GET_ROLES, true)
                .setColumnMappings(User.COLUMN_MAPPINGS)
                .addParameter("user_id", user.getId())
                .executeAndFetch(Role.class);
        user.setRoles(roles);
        return user;
    }
}