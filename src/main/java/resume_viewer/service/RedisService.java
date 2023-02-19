package resume_viewer.service;

public interface RedisService {
    String create(String key);

    String get(String key);
    String update(String key, String value);
}
