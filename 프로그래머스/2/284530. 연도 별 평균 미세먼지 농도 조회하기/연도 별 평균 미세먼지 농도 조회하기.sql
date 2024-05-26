select  year(YM) 'YEAR', 
        round(avg(PM_VAL1), 2) 'PM10', 
        round(avg(PM_VAL2), 2) as 'PM2.5'
from air_pollution
where LOCATION2 = '수원'
group by year(YM)
order by year(YM);