select a.name, a.datetime
from animal_ins a
left join animal_outs b on a.animal_id = b.animal_id
where b.datetime is null
order by a.datetime asc
limit 0, 3;
