package resume_viewer.dao.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Getter
@Setter
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    private Boolean isAccountExpired = false;
    private Boolean isAccountNonLocked = true;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private List<RoleEntity> roles;

    private List<FileAccessEntity> accessEntity;
}
