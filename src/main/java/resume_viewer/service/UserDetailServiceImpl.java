package resume_viewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import resume_viewer.dao.entity.UserEntity;
import resume_viewer.dao.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetail loadUserByUsername(String username) {
         UserEntity userEntity = userRepository.findByUsername(username);
         UserDetail userDetail = new UserDetail(userEntity);
        return userDetail;
    }
}
