/*決済IDから請求情報を取得する。*/
SELECT
 settlement_table.settlement_id															/*請求ID*/
,settlement_table2.settlement_id														/*前回請求ID*/
,settlement_table.request_date															/*請求日*/
,settlement_table.customer_id															/*顧客ID*/
,corporation_customer_master.customer_name												/*正式顧客名*/
,IFNULL(settlement_table2.total_fee,0)+IFNULL(settlement_table2.consumption_tax,0)
+IFNULL(settlement_table2.over_price,0) AS prev_settlement								/*前回請求額*/
,SUM(IFNULL(payment_table.paid_price,0)) AS prev_paid									/*前回入金額*/
,settlement_table.over_price															/*繰り越し金*/
,settlement_table.total_fee																/*税抜合計*/
,settlement_table.consumption_tax														/*消費税*/
 FROM settlement_table
 LEFT OUTER JOIN settlement_table settlement_table2
 ON settlement_table.prev_settlement_id=settlement_table2.settlement_id
 LEFT OUTER JOIN payment_table
 ON payment_table.settlement_id = settlement_table2.settlement_id
 INNER JOIN corporation_customer_master
 ON corporation_customer_master.customer_id=settlement_table.customer_id
 WHERE
 settlement_table.settlement_id= SETTLEMENT_ID
 GROUP BY settlement_table.settlement_id