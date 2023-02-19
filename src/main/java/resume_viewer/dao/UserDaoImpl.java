package resume_viewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import resume_viewer.dao.entity.UserEntity;
import resume_viewer.dao.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public UserEntity getUserInfoByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    public UserEntity findByEmailORMobile(String emailOrMobile) {
        return userRepository.findByEmailORMobile(emailOrMobile);
    }

    @Override
    public UserEntity findByEmailOrMobileAndPassword(String emailOrMobile, String password) {
        Criteria criteria[] = new Criteria[2];
        criteria[0] = new Criteria().orOperator(Criteria.where("mobile").is(emailOrMobile))
                .is(emailOrMobile).and("password").is(password);
        criteria[1] = new Criteria().orOperator(Criteria.where("email").is(emailOrMobile))
                .is(emailOrMobile).and("password").is(password);

        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(criteria));
        UserEntity entity = mongoTemplate.findOne(query, UserEntity.class);
        return entity;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
