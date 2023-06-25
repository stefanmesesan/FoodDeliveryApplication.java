package com.example.licenta;

import com.example.licenta.model.User;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.UserDTO;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.UserService;
import com.example.licenta.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.licenta.utils.Constants.TEST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;
    private User user;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    public void initialize() {
        openMocks(this);

        user = createUser();

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void findByUserEmailTest() {
        when(userRepository.findUserByEmail("email")).thenReturn(Optional.of(createUser()));
        UserDTO result = userService.findByUserEmail("email");
        assertThat(result).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUserById(TEST_ID);
        verify(userRepository).deleteById(TEST_ID);
    }

    private User createUser() {
        User user = new User();
        user.setId(TEST_ID);
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setEmail("email");
        user.setPassword("password");
        user.setAddress("address");
        user.setRole(UserRole.CUSTOMER);
        return user;
    }
}
