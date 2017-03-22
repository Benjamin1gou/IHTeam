/*指定顧客の最終請求IDを取得する。*/
SELECT
 settlement_table.settlement_id
 FROM
 settlement_table
 WHERE
 settlement_table.customer_id = CUSTOMER_ID
 ORDER BY settlement_table.settlement_id DESC
 LIMIT 1;