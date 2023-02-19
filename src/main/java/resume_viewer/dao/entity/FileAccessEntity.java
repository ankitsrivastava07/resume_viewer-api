package resume_viewer.dao.entity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FileAccessEntity {
    private String fileName;
    private String isPublic;
    private String contentType;
    private int length;
    private Long fileSize;
    private String createDate;
    private String updateDate;
}
