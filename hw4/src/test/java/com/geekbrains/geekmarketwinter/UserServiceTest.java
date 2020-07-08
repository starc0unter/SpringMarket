package com.geekbrains.geekmarketwinter;

import com.geekbrains.geekmarketwinter.entites.SystemUser;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.repositories.UserRepository;
import com.geekbrains.geekmarketwinter.services.MailService;
import com.geekbrains.geekmarketwinter.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailService mailService;

    @Test
    public void userServiceTest() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("user1");

        boolean isCreated = userService.save(systemUser);

        Assert.assertTrue(isCreated);

        Mockito.verify(userService, Mockito.times(1)).save(systemUser);
        Mockito.verify(mailService, Mockito.times(1))
                .sendMail(ArgumentMatchers.eq(systemUser.getEmail()), ArgumentMatchers.eq("2"), ArgumentMatchers.anyString());
    }

    @Test
    public void addUserFailTest() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("Michael");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findOneByUserName("Michael");

        boolean isCreated = userService.save(systemUser);

        Assert.assertFalse(isCreated);


        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }
}
