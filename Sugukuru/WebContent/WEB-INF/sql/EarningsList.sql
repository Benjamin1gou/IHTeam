/*売上の一覧を取得する。なお、売上月は出荷日と同等と考える。*/
SELECT
 earning_id			/*売上ID*/
,order_id			/*受注ID*/
,customer_id		/*顧客ID*/
,employment_id		/*社員ID*/
,shipment_date		/*出荷日*/
,no_tax_total_fee	/*税抜合計金額*/
,tax_fee			/*消費税*/
,note				/*備考*/
,CASE
	WHEN SUBSTRING( earnings_table.customer_id, 1, 1) <=> '0'
		/* 法人 */ THEN
		(
			SELECT
				/* 法人略称を取得する */
				corporation_customer_master.abbreviation_name
			FROM
				corporation_customer_master
			WHERE
				corporation_customer_master.customer_id = earnings_table.customer_id
			ORDER BY generation DESC
		)
	ELSE
		/* 個人 */
		(
			SELECT
				/* 個人顧客名を取得する。 */
				personal_customer_master.customer_name
			FROM
				personal_customer_master
			WHERE
				personal_customer_master.customer_id = earnings_table.customer_id
		)
 END AS user_name														/* 顧客名 */
FROM
 earnings_table
 WHERE
/*if(ORDER_ID)start*/
 order_id	= ORDER_ID
/*if(ORDER_ID)end*/
/*if(CUSTOMER_ID)start*/
 AND
 customer_id = CUSTOMER_ID
/*if(CUSTOMER_ID)end*/
/*if(SHIPMENT_DATE)start*/
 AND
 shipment_date LIKE SHIPMENT_DATE
/*if(SHIPMENT_DATE)end*/
 ;
