package ru.pavlov.tech_services_app.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pavlov.tech_services_app.users.dto.CreateUserRequestDto;
import ru.pavlov.tech_services_app.users.dto.UserResponseDto;
import ru.pavlov.tech_services_app.users.mapper.UserMapper;
import ru.pavlov.tech_services_app.users.model.User;
import ru.pavlov.tech_services_app.users.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.toModel(createUserRequestDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).get();
        return userMapper.toDto(user);
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

}
