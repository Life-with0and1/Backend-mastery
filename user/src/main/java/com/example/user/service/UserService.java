    package com.example.user.service;

    import com.example.user.config.JwtService;
    import com.example.user.dto.*;
    import com.example.user.exception.EmailAlreadyExistsException;
    import com.example.user.exception.InvalidCredentialsException;
    import com.example.user.model.User;
    import com.example.user.producer.UserEventProducer;
    import com.example.user.repository.UserRepository;
    import jakarta.validation.constraints.Null;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.Optional;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final UserEventProducer userEventProducer;
        private final JwtService jwtService;

        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserEventProducer userEventProducer, JwtService jwtService){
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.userEventProducer = userEventProducer;
            this.jwtService = jwtService;
        }


        public AuthResponse signUp(UserDTO userDto){
            Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
            if(existingUser.isPresent()){
                throw new EmailAlreadyExistsException("Email already exists");
            }

            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            User savedUser = userRepository.save(user);

            String token = jwtService.generateToken(savedUser.getEmail());

            UserRegisterEvent event = new UserRegisterEvent(
                    savedUser.getId(),
                    savedUser.getEmail(),
                    savedUser.getName()
            );
            userEventProducer.publishUserRegister(event);
            return new AuthResponse(
                    token,
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail()
            );
        }

        public LoginResponse login(LoginDTO loginDTO){
            Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
            if(user.isEmpty()){
                throw new InvalidCredentialsException("Invalid user credentials");
            }

            String email = user.get().getEmail();
            String hashedPassword = user.get().getPassword();

            if (!passwordEncoder.matches(loginDTO.getPassword(), hashedPassword)) {
                throw new InvalidCredentialsException("Invalid user credentials");
            }

            String token = jwtService.generateToken(user.get().getEmail());

            return new LoginResponse(token);
        }
    }
