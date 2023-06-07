package com.inventoryms.ims.userTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import com.inventoryms.ims.models.Role;
import com.inventoryms.ims.models.User;
import com.inventoryms.ims.repository.UserRepository;
import com.inventoryms.ims.repository.RoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testRoleAssignment() {

        Role role = new Role("STOCK CLERK");
        Role savedRole = roleRepository.save(role);

        // Create a user
        User user = new User("Basswell", savedRole);

        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElse(null);

        assertNotNull(savedUser);
        assertEquals(1, savedUser.getRole().size());
        assertTrue(savedUser.getRole().contains(savedRole));
    }

    @Test
    public void createUser() {
        Role role = new Role("SHIPPING CLERK");
        User user = new User("Joshua", role);

        assertEquals("Joshua", user.getName());
        assertEquals(1, user.getRole().size());
        assertTrue(user.getRole().contains(role));
    }

    @Test
    public void testUserEquality() {
        Role shipping = new Role("SHIPPING CLERK");
        Role receiving = new Role("RECEIVING CLERK");
        User user1 = new User("Mwala", shipping);
        User user2 = new User("Ziwelanji", receiving);

        assertNotEquals(user1, user2);
    }
}
