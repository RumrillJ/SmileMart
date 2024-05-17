package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.models.dtos.OutgoingUserDTO;
import com.revature.models.dtos.UserLoginDTO;
import com.revature.models.dtos.UserRegistrationDTO;
import io.jsonwebtoken.Jwts;
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

import javax.crypto.SecretKey;
import java.util.Date;
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

    private final SecretKey KEY = Jwts.SIG.HS256.key().build();

    public String generateToken(User user) {
        return Jwts.builder()
                // subject = username, email, userid or something we can look up a unique user
                .subject(user.getUsername())
                .issuedAt(new Date())
                .signWith(KEY)
                .compact();
    }

    @MockBean
    private JwtService jwtService;


    @MockBean
    private UserDAO userDAO;


    @Test
    public void testRegisterUser_Success() throws Exception {
        // Create a valid UserRegistrationDTO
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "John",
                "Doe",
                "P@ssw0rd",
                "johndoe@email.com",
                "johndoe",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789
        );

        // Mock userDAO.save() to return a User object
        User mockUser = new User();
        mockUser.setUserId(0);
        mockUser.setFirstName(userRegistrationDTO.getFirstName());
        mockUser.setLastName(userRegistrationDTO.getLastName());
        mockUser.setPassword(userRegistrationDTO.getPassword());
        mockUser.setEmail(userRegistrationDTO.getEmail());
        mockUser.setUsername(userRegistrationDTO.getUsername());
        mockUser.setRole(User.ROLE.USER);
        mockUser.setAddress(userRegistrationDTO.getAddress());
        mockUser.setCity(userRegistrationDTO.getCity());
        mockUser.setState(userRegistrationDTO.getState());
        mockUser.setZip(userRegistrationDTO.getZip());
        mockUser.setCountry(userRegistrationDTO.getCountry());
        mockUser.setPhoneNumber(userRegistrationDTO.getPhoneNumber());

        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(mockUser);

        // Call the registerUser method
        String result = authenticationService.registerUser(userRegistrationDTO);

        // Assertions
        assertEquals("User " + userRegistrationDTO.getFirstName() + " " + userRegistrationDTO.getLastName() + " was registered successfully!", result);

    }

    @Test
    public void testRegisterUser_BlankName() throws Exception {
        // Create a UserRegistrationDTO with a blank name
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "",
                "",
                "P@ssw0rd",
                "johndoe@email.com",
                "johndoe",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789
        );

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));
    }

    @Test
    public void testRegisterUser_InvalidPassword() throws Exception {
        // Create a UserRegistrationDTO with an invalid password (no special character)
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "John",
                "Doe",
                "invalidPassword",
                "johndoe@email.com",
                "johndoe",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789
        );

        // Call the registerUser method
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));

    }

    @Test
    public void testRegisterUser_BlankEmail() throws Exception {
        // Create a UserRegistrationDTO with a blank email
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "John",
                "Doe",
                "P@ssw0rd",
                "",
                "johndoe",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789
        );

        // Call the registerUser method
        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(userRegistrationDTO));

    }

    @Test
    void testLoginUser_Success() throws Exception {
        // Create a valid UserLoginDTO
        UserLoginDTO userLoginDTO = new UserLoginDTO("johndoe", "validPassword123!");
            String password = passwordEncoder.encode(userLoginDTO.getPassword());
        // Mock userDAO.save() to return a User object
        User mockUser = new User(
                0,
                "John",
                "Doe",
                User.ROLE.USER,
                "johndoe",
                password,
                "johndoe@email.com",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789
        );

        // Mocks userDAO.findByUsername() to return a User object
        Mockito.when(userDAO.findByUsername(Mockito.any())).thenReturn(Optional.of(mockUser));

        String token = generateToken(mockUser);

        Mockito.when(jwtService.generateToken(Mockito.any())).thenReturn(token);

        OutgoingUserDTO outUser = new OutgoingUserDTO(
                mockUser.getUserId(),
                "John",
                "Doe",
                User.ROLE.USER,
                "johndoe",
                "johndoe@email.com",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789,
                token
        );
        // Call the login method
        String result =  authenticationService.login(userLoginDTO).toString();

        // Assertions
        assertEquals(outUser.toString(), result);
    }

    @Test
    void testLoginUser_IncorrectPassword() throws Exception {
        // Create a valid UserLoginDTO with incorrect password.
        UserLoginDTO userLoginDTO = new UserLoginDTO("johndoe", "validPassword123!");

        // Mock userDAO.save() to return a User object
        User mockUser = new User(
                0,
                "John",
                "Doe",
                User.ROLE.USER,
                "johndoe",
                "IncorrectPassword",
                "johndoe@email.com",
                "123 yr st",
                "ny",
                "ny",
                12345,
                "us",
                123456789
        );


        // Mocks userDAO.findByEmail() and returns a User object.
        Mockito.when(userDAO.findByUsername(Mockito.any())).thenReturn(Optional.of(mockUser));

        // Assertion
        assertThrows(NoSuchElementException.class, () -> authenticationService.login(userLoginDTO));
    }

    @Test
    void testLoginUser_NoUser() throws Exception {
        // Create a valid UserLoginDTO with no such user
        UserLoginDTO userLoginDTO = new UserLoginDTO("johndoe", "validPassword123!");

        // To mock empty user
        Optional<User> mockUser = Optional.empty();

        // Mocks userDAO.findByEmail and returns an empty optional
        Mockito.when(userDAO.findByUsername(Mockito.any())).thenReturn(mockUser);

        // Assertion
        assertThrows(NoSuchElementException.class, () -> authenticationService.login(userLoginDTO));
    }
}