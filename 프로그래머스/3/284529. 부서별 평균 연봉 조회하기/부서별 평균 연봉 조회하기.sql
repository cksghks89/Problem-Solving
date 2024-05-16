select a.dept_id, a.dept_name_en, b.avg_sal
from hr_department a
join (select a.dept_id, round(avg(b.sal), 0) 'avg_sal'
        from hr_department a
        join hr_employees b on a.dept_id = b.dept_id
        group by a.dept_id) b on a.dept_id = b.dept_id
order by b.avg_sal desc;