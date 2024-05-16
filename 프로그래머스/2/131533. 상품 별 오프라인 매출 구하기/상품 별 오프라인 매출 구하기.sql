select a.PRODUCT_CODE, sum(sales_amount) * a.price as 'SALES'
from product a
join offline_sale b on a.product_id = b.product_id
group by a.product_code
order by sales desc, product_code;