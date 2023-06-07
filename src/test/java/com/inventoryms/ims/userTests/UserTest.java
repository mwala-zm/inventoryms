package com.inventoryms.ims.userTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import com.inventoryms.ims.models.Role;
import com.inventoryms.ims.models.User;
import com.inventoryms.ims.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRoleAssignment() {

        Role role = new Role(Role.STOCK_CLERK);

        User user = new User("John Doe",  role);

        user.setRole(role);

        userRepository.save(user);


        User savedUser = userRepository.findById(user.getId()).orElse(null);

        assertEquals(Role.STOCK_CLERK, savedUser.getRole().getName());
    }
}
