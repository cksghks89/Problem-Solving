select floor(price / 10000) * 10000 as 'PRICE_GROUP', count(*) 'PRODUCTS'
from product
group by floor(price / 10000)
order by PRICE_GROUP asc;
