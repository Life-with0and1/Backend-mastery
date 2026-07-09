    package com.example.user.service;

    import com.example.user.dto.UserDTO;
    import com.example.user.dto.UserRegisterEvent;
    import com.example.user.exception.EmailAlreadyExistsException;
    import com.example.user.model.User;
    import com.example.user.producer.UserEventProducer;
    import com.example.user.repository.UserRepository;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final UserEventProducer userEventProducer;

        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserEventProducer userEventProducer){
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.userEventProducer = userEventProducer;
        }


        public User signUp(UserDTO userDto){
            if (userRepository.existsByEmail(userDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            User savedUser = userRepository.save(user);

            UserRegisterEvent event = new UserRegisterEvent(
                    savedUser.getId(),
                    savedUser.getEmail(),
                    savedUser.getName()
            );
            userEventProducer.publishUserRegister(event);
            return savedUser;
        }
    }
