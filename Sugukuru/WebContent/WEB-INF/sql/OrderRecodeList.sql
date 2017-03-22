/* 受注テーブルから受注データを取得する。 */
/* 発送状態、請求状態の条件はPGで制御する。 */
SELECT
 order_table.order_id																		/*受注ID*/
,estimates_id																				/* 見積ID */
,order_table.customer_id																	/*顧客ID*/
																							/* 顧客ごとの名称を取得する。 */
,CASE
	WHEN SUBSTRING( order_table.customer_id, 1, 1) <=> '0'
		/* 法人 */ THEN
		(
			SELECT
				/* 法人略称を取得する */
				corporation_customer_master.abbreviation_name
			FROM
				corporation_customer_master
			WHERE
				corporation_customer_master.customer_id = order_table.customer_id
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
				personal_customer_master.customer_id = order_table.customer_id
		)
END AS user_name
,order_table.order_date															/* 受注日時 */
,IFNULL(shipment_table.shipment_flg,0)   													/* 出荷フラグ */
,settlement_table.settlement_id													/* 請求ID */
 FROM
	order_table
 LEFT OUTER JOIN
	shipment_table
 ON order_table.order_id = shipment_table.order_id
 INNER JOIN
	customers_distinction_master
 ON order_table.customer_id = customers_distinction_master.customer_id
/* log 2016/11/03 決済テーブルは受注テーブルとのリレーションでは？*/
 LEFT OUTER JOIN
	settlement_table
 ON order_table.settlement_id = settlement_table.settlement_id
 WHERE
/*if(CUSTOMER_ID)start*/
	/* 顧客ID */
	order_table.customer_id = CUSTOMER_ID
/*if(CUSTOMER_ID)end*/
/*if(ORDER_DATE)start*/
 AND
	/* 受注日時 */
	order_table.order_date = ORDER_DATE
/*if(ORDER_DATE)end*/
;