package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.Role;
import org.springframework.lang.NonNull;

public interface RoleRepositorySql2o {

    Role getByName(@NonNull String role);

}
