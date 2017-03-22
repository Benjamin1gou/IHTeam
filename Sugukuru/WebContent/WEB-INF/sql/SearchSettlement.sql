/*請求済み情報を取得する。*/
SELECT
 settlement_table.settlement_id					/*請求ID*/
,settlement_table.customer_id					/*顧客ID*/
,settlement_table.request_date					/*請求日*/
,settlement_table.total_fee						/*合計金額*/
,settlement_table.consumption_tax				/*消費税*/
,settlement_table.request_date					/*請求日*/
,settlement_table.payment_date					/*入金日*/
,corporation_customer_master.abbreviation_name	/*法人略称*/
,corporation_customer_master.recall_manner		/*回収方法*/
FROM
 settlement_table
 INNER JOIN corporation_customer_master
 ON settlement_table.customer_id=corporation_customer_master.customer_id
 WHERE
/*if(CUSTOMER_ID)start*/
  settlement_table.customer_id= CUSTOMER_ID
/*if(CUSTOMER_ID)end*/
  AND
/*if(REQUEST_DATE)start*/
  settlement_table.request_date = REQUEST_DATE
/*if(REQUEST_DATE)end*/

 ORDER BY settlement_table.request_date DESC
 ;