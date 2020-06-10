package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.User;
import org.springframework.lang.NonNull;

public interface UserRepositorySql2o {
    User getUser(@NonNull String userName);

    void save(@NonNull User user);
}
