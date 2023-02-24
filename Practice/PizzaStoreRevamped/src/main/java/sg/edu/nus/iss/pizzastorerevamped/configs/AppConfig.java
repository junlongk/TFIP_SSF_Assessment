package sg.edu.nus.iss.pizzastorerevamped.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.logging.Logger;

@Configuration
public class AppConfig {

    private final Logger logger = Logger.getLogger(AppConfig.class.getName());

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.database}")
    private int redisDatabase;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean("pizza")
    public RedisTemplate<String, String> redisTemplate() {
        // Creating redis config
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        // Set redis config parameters
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setDatabase(redisDatabase);

        // Set user & password only if they are not empty
        if(!redisUsername.isEmpty())
            config.setUsername(redisUsername);
        if(!redisPassword.isEmpty())
            config.setPassword(redisPassword);

        logger.info("REDIS CONNECTION: \nhost: %s, port: %d, database: %d, username: %b, password: %b"
                .formatted(redisHost, redisPort, redisDatabase, redisUsername.length() > 0, redisPassword.length() > 0));

        // Initiate jedisClient & jedisFactory
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();

        // Initiate redisTemplate and set params
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisFac);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
