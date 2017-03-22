/*入金ファイルからDBに登録するSQL*/
INSERT INTO payment_table
(
   payment_table.settlement_id
  ,payment_table.payment_deadline
  ,payment_table.payment_date
  ,payment_table.payment_way
  ,payment_table.paid_price
)VALUES(
	SETTLEMENT_ID
    ,DEAD_LINE
   	,PAYMENT_DATE
    ,PAYMENT_WAY
    ,PRICE
);