SELECT H.CAR_ID, IF(R.CAR_ID IS NULL, '대여 가능', '대여중') AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H LEFT JOIN (SELECT DISTINCT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY WHERE '2022-10-16' BETWEEN START_DATE AND END_DATE) AS R ON H.CAR_ID = R.CAR_ID
GROUP BY H.CAR_ID
ORDER BY H.CAR_ID DESC;