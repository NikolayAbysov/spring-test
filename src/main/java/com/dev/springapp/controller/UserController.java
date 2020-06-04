package com.dev.springapp.controller;

import com.dev.springapp.dto.UserResponseDto;
import com.dev.springapp.model.User;
import com.dev.springapp.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/inject")
    public String inject() {
        userService.add(new User("reimu.hakurei@i.ua", "123"));
        userService.add(new User("remilia.scarlet@i.ua", "123"));
        return "Users added";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.get(userId);
        return getUserDto(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserResponseDto> getAll() {
        return getAllDto();
    }

    private UserResponseDto getUserDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    private List<UserResponseDto> getAllDto() {
        return userService.listUsers()
                .stream()
                .map(this::getUserDto)
                .collect(Collectors
                        .toList());
    }
}
