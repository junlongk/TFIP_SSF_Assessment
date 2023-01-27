package sg.edu.nus.iss.app.pizza_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.pizza_store.model.CustomerOrder;

@Service
public class PizzaRedis {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save(final CustomerOrder order) {
        
    }
}
