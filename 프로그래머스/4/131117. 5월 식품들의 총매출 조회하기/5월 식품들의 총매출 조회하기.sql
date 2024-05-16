select a.product_id, a.product_name, a.price * b.sum_amount as 'total_sales'
from food_product a
join (select a.product_id, sum(b.amount) 'sum_amount'
        from food_product a
        join food_order b on a.product_id = b.product_id
        where year(b.produce_date) = 2022 and month(b.produce_date) = 5
        group by a.product_id) b on a.product_id = b.product_id
order by total_sales desc, a.product_id asc;