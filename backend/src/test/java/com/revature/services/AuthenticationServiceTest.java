package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.models.dtos.UserRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationService.class)
@Slf4j
class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private UserDAO userDAO;


    @Test
    public void testRegisterUser_Success() throws Exception {
        // Create a valid UserRegistrationDTO
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("John Doe", "validPassword123!", "john.doe@email.com");

        // Mock userDAO.save() to return a User object
        User mockUser = new User();
        mockUser.setName(userRegistrationDTO.getName());
        mockUser.setEmail(userRegistrationDTO.getEmail());
        mockUser.setPassword(userRegistrationDTO.getEmail());
        mockUser.setRole(User.ROLE.USER);
        mockUser.setUserId(0);

        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(mockUser);

        // Call the registerUser method
        String result = authenticationService.registerUser(userRegistrationDTO);

        // Assertions
        assertEquals("User " + userRegistrationDTO.getName() + " was registered successfully!", result);
    }

    @Test
    public void testRegisterUser_BlankName() throws Exception {
        // Create a UserRegistrationDTO with a blank name
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("", "validPassword123!", "john.doe@email.com");

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));
    }

    @Test()
    public void testRegisterUser_InvalidPassword() throws Exception {
        // Create a UserRegistrationDTO with an invalid password (no special character)
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("John Doe", "invalidPassword", "john.doe@email.com");

        // Call the registerUser method
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));

    }

    @Test()
    public void testRegisterUser_BlankEmail() throws Exception {
        // Create a UserRegistrationDTO with a blank email
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("John Doe", "validPassword123!", "");

        // Call the registerUser method
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));

    }
}