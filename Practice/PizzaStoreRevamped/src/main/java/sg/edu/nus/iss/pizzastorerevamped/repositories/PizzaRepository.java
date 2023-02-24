package sg.edu.nus.iss.pizzastorerevamped.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.pizzastorerevamped.models.Order;
import sg.edu.nus.iss.pizzastorerevamped.utils.PizzaUtil;

import java.util.Optional;

@Repository
public class PizzaRepository {

    @Autowired @Qualifier("pizza")
    private RedisTemplate<String, String> redisTemplate;

    // Takes in: Order object
    // Saves to redis as <orderId (String), order (object converted to json to String)>
    public void saveOrder(Order order) {
        redisTemplate.opsForValue().set(order.getOrderId(), PizzaUtil.orderToStr(order));
    }

    // Takes in: orderId (String)
    // Retrieves from redis as order (String convert to json to Order object)
    public Optional<Order> getOrder(String orderId) {
        String str = redisTemplate.opsForValue().get(orderId);

        if ((null == str) || (str.trim().length() == 0))
            return Optional.empty();

        return Optional.of(PizzaUtil.strToOrder(str));
    }

}
