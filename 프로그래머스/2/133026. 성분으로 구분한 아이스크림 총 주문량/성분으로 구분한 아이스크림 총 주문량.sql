select b.ingredient_type, sum(a.total_order) 'total_order'
from first_half a
join icecream_info b on a.flavor = b.flavor
group by b.ingredient_type
order by sum(a.total_order);