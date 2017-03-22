/*請求を行う顧客を一覧で表示する。その後、印刷と請求済みに変えていく。*/
/*また請求を行う売上は決算IDがnullのもの。*/
SELECT
 earnings_table.customer_id										/*顧客ID*/
,IFNULL(
      (
       SELECT
          SUM(earnings_table.no_tax_total_fee)
       FROM
          earnings_table earnings_table2
       WHERE
          earnings_table.settlement_id IS NULL
       AND
          earnings_table2.customer_id=earnings_table.customer_id
  		)
   ,0)															/*税抜合計金額*/
,IFNULL(
      (
       SELECT
          SUM(earnings_table.tax_fee)
       FROM
          earnings_table earnings_table2
       WHERE
          earnings_table.settlement_id IS NULL
       AND
          earnings_table2.customer_id=earnings_table.customer_id
 		)
  ,0)															/*税合計金額*/
,corporation_customer_master.abbreviation_name					/*略称*/
,corporation_customer_master.cut_off_date						/*締日*/
,corporation_customer_master.recall_manner						/*回収方法*/
,IFNULL((
 SELECT
	IFNULL((settlement_table.total_fee+settlement_table.consumption_tax+settlement_table.over_price),0)
	-
	IFNULL(SUM(payment_table.paid_price),0)
 FROM
 	settlement_table
 LEFT OUTER JOIN payment_table
 ON payment_table.settlement_id=settlement_table.settlement_id
 WHERE
  settlement_table.settlement_id =
  (SELECT
   	settlement_table2.settlement_id
   FROM
   	settlement_table settlement_table2
   WHERE
    settlement_table.customer_id=settlement_table2.customer_id
   ORDER BY settlement_table2.settlement_id DESC
   LIMIT 1
   )
 AND
 	settlement_table.customer_id = earnings_table.customer_id
 GROUP BY settlement_table.customer_id
 ),0) AS diffre								/*前回入金額と請求総合計差：所謂*/
 ,corporation_customer_master.corporation_name						/*正式法人顧客名*/
 FROM
 earnings_table
 INNER JOIN corporation_customer_master
 ON earnings_table.customer_id = corporation_customer_master.customer_id
 WHERE
 ( earnings_table.settlement_id IS NULL
    OR
    (
    SELECT SUM((total_fee + consumption_tax))
    FROM
      settlement_table
    WHERE
      settlement_table.customer_id = earnings_table.customer_id AND settlement_table.payment_date IS NULL
  ) IS NOT NULL
  )
  AND
/*if(CUSTOMER_ID)start*/
 earnings_table.customer_id = CUSTOMER_ID
/*if(CUSTOMER_ID)end*/
/*if(CUT_DATE)start*/
  AND
 corporation_customer_master.cut_off_date = CUT_DATE
/*if(CUT_DATE)end*/

 GROUP BY earnings_table.customer_id
 ;