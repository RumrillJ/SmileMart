package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import com.revature.models.DTOs.UserLoginDTO;
import com.revature.models.DTOs.UserRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationService.class)
@Slf4j
class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService authenticationService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @MockBean
    JwtService jwtService;

    @MockBean
    private UserDAO userDAO;


    @Test
    public void testRegisterUser_Success() throws Exception {
        // Create a valid UserRegistrationDTO
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("John Doe", "validPassword123!", "john.doe@email.com");

        // Mock userDAO.save() to return a User object
        User mockUser = new User();
        // mockUser.setName(userRegistrationDTO.getName());
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

    @Test
    public void testRegisterUser_InvalidPassword() throws Exception {
        // Create a UserRegistrationDTO with an invalid password (no special character)
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("John Doe", "invalidPassword", "john.doe@email.com");

        // Call the registerUser method
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));

    }

    @Test
    public void testRegisterUser_BlankEmail() throws Exception {
        // Create a UserRegistrationDTO with a blank email
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("John Doe", "validPassword123!", "");

        // Call the registerUser method
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));

    }

    @Test
    void testLoginUser_Success() throws Exception {
        // Create a valid UserLoginDTO
        UserLoginDTO userLoginDTO = new UserLoginDTO("john.doe@email.com", "validPassword123!");

        // Mock userDAO.save() to return a User object
        User mockUser = new User();
        mockUser.setEmail(userLoginDTO.getEmail());
        mockUser.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));
        mockUser.setRole(User.ROLE.USER);
        mockUser.setUserId(0);

        // Mocks userDAO.findByEmail() to return a User object
        Mockito.when(userDAO.findByEmail(Mockito.any())).thenReturn(Optional.of(mockUser));

        String token = jwtService.generateToken(mockUser);

        // Call the login method
        String result = authenticationService.login(userLoginDTO);

        // Assertions
        assertEquals(token, result);
    }

    @Test
    void testLoginUser_IncorrectPassword() throws Exception {
        // Create a valid UserLoginDTO with incorrect password.
        UserLoginDTO userLoginDTO = new UserLoginDTO("john.doe@email.com", "validPassword123!");

        // Mock userDAO.save() to return a User object
        User mockUser = new User();
        mockUser.setEmail(userLoginDTO.getEmail());
        mockUser.setPassword(passwordEncoder.encode("VeryDifferentPassword"));
        mockUser.setRole(User.ROLE.USER);
        mockUser.setUserId(0);

        // Mocks userDAO.findByEmail() and returns a User object.
        Mockito.when(userDAO.findByEmail(Mockito.any())).thenReturn(Optional.of(mockUser));

        // Assertion
        assertThrows(NoSuchElementException.class, () -> authenticationService.login(userLoginDTO));
    }

    @Test
    void testLoginUser_NoUser() throws Exception {
        // Create a valid UserLoginDTO with no such user
        UserLoginDTO userLoginDTO = new UserLoginDTO("john.doe@email.com", "validPassword123!");

        // To mock empty user
        Optional<User> mockUser = Optional.empty();

        // Mocks userDAO.findByEmail and returns an empty optional
        Mockito.when(userDAO.findByEmail(Mockito.any())).thenReturn(mockUser);

        // Assertion
        assertThrows(NoSuchElementException.class, () -> authenticationService.login(userLoginDTO));
    }
}