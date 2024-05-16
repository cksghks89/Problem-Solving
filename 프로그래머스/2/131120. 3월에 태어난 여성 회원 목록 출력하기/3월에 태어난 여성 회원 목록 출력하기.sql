select MEMBER_ID, MEMBER_NAME, GENDER, date_format(date_of_birth, '%Y-%m-%d') 'DATE_OF_BIRTH'
from member_profile
where month(date_of_birth) = 3
    and TLNO is not null
    and gender = 'W'
order by member_id;