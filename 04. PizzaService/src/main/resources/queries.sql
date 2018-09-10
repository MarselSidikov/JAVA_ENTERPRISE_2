select name, order_user.user_name
from pizza
       join order_pizza o on pizza.id = o.pizza_id
       join (select first_name as user_name, o.id as order_id
             from pizza_user
                    join order_table o on pizza_user.id = o.client_id
             ORDER BY user_name) as order_user on order_user.order_id = o.order_id;
