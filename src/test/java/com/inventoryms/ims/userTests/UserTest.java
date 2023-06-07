package com.inventoryms.ims.userTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

        Role role = new Role(Role.STOCK_CLERK);
        Role savedRole = roleRepository.save(role);

        // Create a user
        User user = new User("Basswell", role);
        user.setRole(savedRole);

        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElse(null);

        // Test the role assignment
        assertEquals(Role.STOCK_CLERK, savedUser.getRole().getName());
    }

    @Test
    public void createUser() {
        Role role = new Role(Role.RECEIVING_CLERK);
        User user = new User("Joshua", role);

        assertEquals("Joshua", user.getName());
        assertEquals(role, user.getRole());
    }

    @Test
    public void testUserEquality() {
        Role role1 = new Role(Role.SHIPPING_CLERK);
        Role role2 = new Role(Role.RECEIVING_CLERK);
        User user1 = new User("Mwala", role1);
        User user2 = new User("Ziwelanji", role2);

        assertNotEquals(user1, user2);
    }
}
