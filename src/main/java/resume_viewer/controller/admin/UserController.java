package resume_viewer.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import resume_viewer.dto.LoginRequestDto;
import resume_viewer.service.AuthenticationService;
@RequestMapping("/api/v1/user")
@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authenticationService.authenticate(loginRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
