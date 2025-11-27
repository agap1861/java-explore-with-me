package ewm.user.controller;


import ewm.exception.NotFoundException;
import ewm.user.domain.User;
import ewm.user.dto.NewUserRequest;
import ewm.user.dto.UserDto;
import ewm.user.mapper.UserDtoToDomain;
import ewm.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class UserAdminController {
    private final UserService userService;
    private final UserDtoToDomain mapper;

    @PostMapping
    public UserDto postUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        User user = userService.postUser(mapper.dtoToDomain(newUserRequest));
        return mapper.domainToDto(user);
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(defaultValue = "") List<Long> ids,
                                  @RequestParam(defaultValue = "0") Integer from, @RequestParam("10") Integer size) {
        List<User> users = userService.getUsers(ids, from, size);
        return users.stream()
                .map(mapper::domainToDto)
                .toList();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long userId) throws NotFoundException {
        userService.deleteUserById(userId);

    }
}
