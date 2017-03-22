/*請求詳細画面に表示する入金SQLを生成する。*/
SELECT
 payment_table.num
,payment_table.payment_date
,payment_table.payment_way
,payment_table.paid_price
 FROM
 payment_table
 WHERE
 payment_table.settlement_id = SETTLEMENT_ID
;
