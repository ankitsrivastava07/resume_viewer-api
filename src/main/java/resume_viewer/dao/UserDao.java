package resume_viewer.dao;

import resume_viewer.dao.entity.UserEntity;

public interface UserDao {

    UserEntity getUserInfoByMobile(String mobile);

    UserEntity findByEmailORMobile(String emailOrMobile);

    UserEntity findByEmailOrMobileAndPassword(String emailOrMobile, String password);

    UserEntity save(UserEntity user);

    UserEntity findByUsername(String username);
}
