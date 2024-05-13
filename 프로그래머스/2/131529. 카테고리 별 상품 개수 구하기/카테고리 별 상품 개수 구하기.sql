select substring(product_code, 1, 2) 'CATEGORY', count(*) 'PRODUCTS'
from product
group by substring(product_code, 1, 2)
order by substring(product_code, 1, 2) asc;