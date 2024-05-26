select id,
    case when SIZE_OF_COLONY <= 100
         then 'LOW'
         when SIZE_OF_COLONY between 100 and 1000
         then 'MEDIUM'
         else 'HIGH'
    end as 'SIZE'
from ecoli_data
order by id;