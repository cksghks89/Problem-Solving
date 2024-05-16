select 
    car_id,
    max(case when datediff(start_date, '2022-10-16 00:00:00') <= 0 and datediff('2022-10-16 00:00:00', end_date) <= 0
        then '대여중'
        else '대여 가능'
    end) 'availability'
from car_rental_company_rental_history
group by car_id
order by car_id desc;