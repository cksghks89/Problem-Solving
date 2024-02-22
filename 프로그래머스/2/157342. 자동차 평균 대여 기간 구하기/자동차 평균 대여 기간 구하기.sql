-- 코드를 입력하세요
SELECT c.car_id CAR_ID, ROUND(AVG(DATEDIFF(c.end_date, c.start_date) + 1), 1) 'AVERAGE_DURATION'
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY c
GROUP BY c.car_id
HAVING AVG(DATEDIFF(c.end_date, c.start_date)) + 1 >= 7
ORDER BY AVERAGE_DURATION desc, car_id desc