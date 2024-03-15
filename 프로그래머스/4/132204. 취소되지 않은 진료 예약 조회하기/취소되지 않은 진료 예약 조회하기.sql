select apnt_no, pt_name, p.pt_no, d.mcdp_cd, dr_name, apnt_ymd
from (patient as p inner join appointment as a on p.pt_no = a.pt_no) inner join doctor as d on a.mddr_id = d.dr_id
where date_format(apnt_ymd, '%Y-%m-%d') = '2022-04-13' and d.mcdp_cd = 'CS' and apnt_cncl_yn = 'N'
order by apnt_ymd;