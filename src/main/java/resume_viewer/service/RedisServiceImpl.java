package resume_viewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    public static final String SESSION_TOKEN = "token";
    public static final String DOMAIN = "resume_viewer";
    public static final Integer TIME_DURATION = 30 * 60;

    @Override
    public String create(String key) {
        String hashKey = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
        redisTemplate.opsForValue().set(hashKey, key);
        redisTemplate.expire(hashKey, TIME_DURATION, TimeUnit.SECONDS);
        return hashKey;
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String update(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, (redisTemplate.getExpire(key) + TIME_DURATION), TimeUnit.SECONDS);
        return redisTemplate.opsForValue().get(key);
    }
}
