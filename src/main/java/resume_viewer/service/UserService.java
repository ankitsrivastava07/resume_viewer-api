package resume_viewer.service;
import resume_viewer.dao.entity.UserEntity;
import resume_viewer.dto.ApiResponseDto;
import resume_viewer.dto.CreateUserDto;
public interface UserService {
    ApiResponseDto findByMobileOrEmail(String mobile);
    UserEntity findByEmailOrMobileAndPassword(String emailOrMobile, String password);
    ApiResponseDto createUser(CreateUserDto createUserDto);
}
