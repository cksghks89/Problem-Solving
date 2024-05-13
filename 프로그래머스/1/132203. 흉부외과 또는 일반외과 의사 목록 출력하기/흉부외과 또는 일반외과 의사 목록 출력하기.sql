select DR_NAME, DR_ID, MCDP_CD, date_format(hire_ymd, '%Y-%m-%d')
from doctor
where mcdp_cd = 'CS' or mcdp_cd = 'GS'
order by hire_ymd desc, dr_name asc;