package resume_viewer.service;

import resume_viewer.dto.ApiResponseDto;
import resume_viewer.dto.LoginRequestDto;

public interface AuthenticationService {
    ApiResponseDto authenticate(LoginRequestDto loginRequestDto);
}
