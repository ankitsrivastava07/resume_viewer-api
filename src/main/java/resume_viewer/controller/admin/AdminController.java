package resume_viewer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import resume_viewer.service.AuthenticationService;
import resume_viewer.service.UserService;
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

   @GetMapping("/{mobile}")
    public ResponseEntity<?> getUserInfoByMobile(@PathVariable String mobile) {
        return new ResponseEntity<>(userService.findByMobileOrEmail(mobile),HttpStatus.OK);
    }
}
