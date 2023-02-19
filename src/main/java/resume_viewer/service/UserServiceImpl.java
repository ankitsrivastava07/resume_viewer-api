package resume_viewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import resume_viewer.dao.UserDao;
import resume_viewer.dao.entity.RoleEntity;
import resume_viewer.dao.entity.UserEntity;
import resume_viewer.dto.ApiResponseDto;
import resume_viewer.dto.CreateUserDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RedisService redisService;

    @Override
    public ApiResponseDto findByMobileOrEmail(String mobile) {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        UserEntity user = userDao.findByEmailORMobile(mobile);

        if (user != null) {

            Map<String, Object> data = new HashMap<>();
            data.put("Name", user.getFirstName());
            data.put("Email", user.getEmail());

            data.put("Mobile", user.getMobile());
            data.put("Role", user.getRoles());
            apiResponseDto.setData(data);

            apiResponseDto.setMsg("Success");
            apiResponseDto.setStatus(Boolean.TRUE);

            return apiResponseDto;
        }
        apiResponseDto.setMsg("Success");
        apiResponseDto.setStatus(Boolean.TRUE);
        return apiResponseDto;
    }

    public UserEntity findByEmailOrMobileAndPassword(String email, String password) {
        return userDao.findByEmailOrMobileAndPassword(email, password);
    }

    @Override
    public ApiResponseDto createUser(CreateUserDto createUserDto) {

        if (isEmailAlreadyExist(createUserDto.getEmail())) {
            ApiResponseDto dto = new ApiResponseDto();
            dto.setStatus(Boolean.FALSE);
            dto.setStatusCode(400);
            dto.setMsg("Email already exist");
            return dto;
        }
        if (isUsernameAlreadyExist(createUserDto.getUsername())) {
            ApiResponseDto dto = new ApiResponseDto();
            dto.setStatusCode(400);
            dto.setStatus(Boolean.FALSE);
            dto.setMsg("Username already exist");
            return dto;
        }
        UserEntity entity = new UserEntity();
        entity.setPassword(encodeRawPassword(createUserDto.getPassword()));
        entity.setEmail(createUserDto.getEmail());
        entity.setUsername(createUserDto.getUsername());
        entity.setFirstName(createUserDto.getFirstName());
        entity.setLastName(createUserDto.getLastName());
        entity.setMobile(createUserDto.getMobile());

        RoleEntity role = new RoleEntity();
        role.setRole("USER");

        entity.setRoles(Arrays.asList(role));
        UserEntity user = userDao.save(entity);
        ApiResponseDto dto = new ApiResponseDto();
        dto.setStatus(Boolean.TRUE);
        dto.setMsg("Account created successfully");
        return dto;
    }

    private String encodeRawPassword(String rawPsd) {
        return new BCryptPasswordEncoder().encode(rawPsd);
    }

    public boolean isEmailAlreadyExist(String email) {
        return userDao.findByEmailORMobile(email) != null;
    }

    public boolean isUsernameAlreadyExist(String username) {
        return userDao.findByUsername(username) != null;
    }
}
