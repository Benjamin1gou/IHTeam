SELECT
	order_table.order_id												/* 受注ID */
,	order_table.customer_id												/* 顧客ID */
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
 END AS user_name														/* 顧客名 */
,order_details_table.num												/* 受注詳細行番号 */
,order_details_table.puroduct_id										/* 商品番号 */
,order_details_table.puroduct_name										/* 商品名 */
,order_details_table.price												/* 商品単価 */
,order_details_table.consumption_tax									/* 消費税 */
,order_details_table.amount												/* 数量 */
,order_details_table.step												/* 作業ステップ */
,order_table.delivery_date												/* 納品日 */
,order_details_table.product_delivered_flg								/* 納品受領フラグ */
,order_details_table.note												/* 備考 */
,DATE_FORMAT(order_table.order_date,'%Y-%m-%d %k:%i:%s')				/* 受注日時 */
,DATE_FORMAT(order_table.shipment_date,'%Y-%m-%d %k:%i:%s')				/* 出荷日時 */
 FROM
 order_table
 INNER JOIN order_details_table
 ON order_table.order_id = order_details_table.order_id
 WHERE
/*if(ORDER_ID)start*/
 order_table.order_id = ORDER_ID
/*if(ORDER_ID)end*/
 AND
/*if(CUSTOMER_ID)start*/
 order_table.customer_id = CUSTOMER_ID
/*if(CUSTOMER_ID)end*/
 AND
/*if(DELIVERY_DATE)start*/
 order_table.delivery_date = DELIVERY_DATE
/*if(DELIVERY_DATE)end*/


;