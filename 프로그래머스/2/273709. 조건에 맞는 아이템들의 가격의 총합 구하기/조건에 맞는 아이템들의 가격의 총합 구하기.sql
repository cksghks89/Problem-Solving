select sum(price) 'TOTAL_PRICE'
from item_info
where rarity = 'legend';