select a.category, sum(sales) 'TOTAL_SALES'
from book a
join book_sales b
on a.book_id = b.book_id
where sales_date like '2022-01%'
group by a.category
order by a.category asc;
