select p.product_id as product_id, product_name, sum(amount * price) as total_sales
from food_product as p  inner join food_order as o on p.product_id = o.product_id
where produce_date like "2022-05-%"
group by product_id
order by total_sales desc, product_id;