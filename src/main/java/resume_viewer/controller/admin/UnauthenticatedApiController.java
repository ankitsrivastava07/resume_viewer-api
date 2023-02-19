package resume_viewer.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resume_viewer.dto.ApiResponseDto;
import resume_viewer.dto.CreateUserDto;
import resume_viewer.dto.LoginRequestDto;
import resume_viewer.service.AuthenticationService;
import resume_viewer.service.UserService;

@RequestMapping("/unauthenticated/api")
@RestController
public class UnauthenticatedApiController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authenticationService.authenticate(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> register(@RequestBody @Valid @Validated CreateUserDto createUserDto) {
        ApiResponseDto apiResponseDto = userService.createUser(createUserDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.valueOf(apiResponseDto.getStatusCode()));
    }
}
