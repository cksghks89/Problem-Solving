-- 코드를 입력하세요
SELECT *
FROM CAR_RENTAL_COMPANY_CAR c
WHERE INSTR(c.OPTIONS, '네비게이션') > 0
ORDER BY car_id desc;