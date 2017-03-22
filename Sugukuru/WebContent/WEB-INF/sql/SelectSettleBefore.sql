/*顧客ごとの前回請求額と入金額を取得する。*/
SELECT
 SUM(settlement_table.total_fee+settlement_table.consumption_tax) AS sett /*前回請求額*/
,IFNULL(
 SUM(payment_table.paid_price),0) payment								/*前回入金額*/
,settlement_table.settlement_id											/*前回請求ID*/
 FROM
 settlement_table
 LEFT OUTER JOIN payment_table
 ON payment_table.settlement_id=settlement_table.settlement_id
 WHERE
 settlement_table.settlement_id =
 (
  SELECT
     settlement_table2.settlement_id
     FROM settlement_table settlement_table2
     WHERE
     	settlement_table.customer_id=settlement_table2.customer_id
     ORDER BY settlement_table2.settlement_id DESC
     LIMIT 0,1
 )
 AND
 settlement_table.customer_id = CUSTOMER_ID
 GROUP BY settlement_table.customer_id

;