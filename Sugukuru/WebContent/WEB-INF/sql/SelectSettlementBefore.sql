/*請求ごとの前回入金額、繰り越し金を取得する；。*/
 SELECT
 settlement_table.customer_id				/*顧客ID*/
,corporation_customer_master.customer_name
,settlement_table.settlement_id				/*請求ID*/
,settlement_table.over_price				/*繰り越し金*/
,settlement_table.total_fee					/*請求額税抜*/
,settlement_table.consumption_tax			/*請求税*/
,IFNULL((
  SELECT
    SUM(payment_table.paid_price)
    FROM payment_table
    WHERE
    payment_table.settlement_id=settlement_table.settlement_id
 ),0)	AS prev_pay								/*前回入金額*/
,
 (
     SELECT
     (IFNULL(settlement_table.over_price,0)+IFNULL(settlement_table.consumption_tax,0)+IFNULL(settlement_table.total_fee,0))
    -
 IFNULL((
 	SELECT
     SUM(payment_table.paid_price)
     FROM
     payment_table
     WHERE
     payment_table.settlement_id=settlement_table.settlement_id

),0)
 ) AS diffre								/*前回入金額と請求総合計差*/
 , (settlement_table.over_price+settlement_table.consumption_tax+settlement_table.total_fee)
FROM
 settlement_table
 INNER JOIN corporation_customer_master
 ON settlement_table.customer_id=corporation_customer_master.customer_id
 WHERE
 settlement_table.settlement_id=
 (
  SELECT
     settlement_table2.prev_settlement_id
     FROM settlement_table settlement_table2
     WHERE
     settlement_table2.settlement_id=4
 )