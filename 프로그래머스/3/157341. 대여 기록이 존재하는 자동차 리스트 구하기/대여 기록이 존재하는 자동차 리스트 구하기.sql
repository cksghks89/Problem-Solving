-- 코드를 입력하세요
SELECT DISTINCT c.car_id
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
JOIN CAR_RENTAL_COMPANY_CAR c
ON h.CAR_ID = c.CAR_ID
WHERE c.car_type = '세단' and MONTH(h.start_date) = 10
ORDER BY c.car_id desc