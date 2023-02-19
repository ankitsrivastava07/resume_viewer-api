package resume_viewer.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import resume_viewer.dao.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    @Query(value = "{$or :[{email: ?0},{mobile: ?0}]}")
    UserEntity findByEmailORMobile(String email);

    UserEntity findByMobile(String mobile);

    @Query("{$and : [{$or : [{email:?0},{mobile:?0}]}, password: ?1]}")
    UserEntity findByEmailOrMobileAndPassword(String emailOrMobile, String password);

    UserEntity findByUsername(String username);
}
