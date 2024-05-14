select hour(datetime) hour, count(*) count
from animal_outs
where 9 <= hour(datetime) and hour(datetime) < 20
group by hour(datetime)
order by hour;