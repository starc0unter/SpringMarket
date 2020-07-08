package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.Role;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.RoleRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class RoleRepositoryImpl implements RoleRepositorySql2o {
    private final Sql2o sql2o;

    private static final String GET_BY_NAME = "select * from roles where name = :name";

    @Autowired
    public RoleRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Role getByName(String role) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_BY_NAME)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .addParameter("name", role)
                    .executeAndFetchFirst(Role.class);
        }
    }
}
