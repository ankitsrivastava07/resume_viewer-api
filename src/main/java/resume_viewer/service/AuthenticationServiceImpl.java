package resume_viewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import resume_viewer.dto.ApiResponseDto;
import resume_viewer.dto.LoginRequestDto;
import java.util.HashMap;
import java.util.Map;
@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserDetailServiceImpl userDetailService;
    @Autowired
    RedisService redisService;
    @Override
    public ApiResponseDto authenticate(LoginRequestDto loginRequestDto) {
        UserDetail userDetail = userDetailService.loadUserByUsername(loginRequestDto.getUsername());
        ApiResponseDto responseDto = new ApiResponseDto();
        String psd = loginRequestDto.getPassword();
        Map<String, Object> info = new HashMap<>();
        if (userDetail.getUser() == null) {
            responseDto.setStatus(Boolean.FALSE);
            responseDto.setMsg("Account not found");
            return responseDto;
        }
        if (new BCryptPasswordEncoder().matches(psd, userDetail.getPassword())) {
            String token = redisService.create(loginRequestDto.getUsername());
            info.put("Name", userDetail.getName());
            info.put("Token", token);
            responseDto.setData(info);
            responseDto.setMsg("Success");
            responseDto.setStatus(Boolean.TRUE);

            UsernamePasswordAuthenticationToken token1 =
                    new UsernamePasswordAuthenticationToken(userDetail.getEmail(), psd, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token1);
            return responseDto;
        }
        responseDto.setStatus(Boolean.FALSE);
        responseDto.setMsg("Invalid email or mobile and password");
        return responseDto;
    }
}
