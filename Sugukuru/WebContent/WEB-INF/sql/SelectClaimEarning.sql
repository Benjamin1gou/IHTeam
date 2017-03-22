/*指定請求IDが格納されている売上詳細を取得する。*/
SELECT
 earnings_detail_table.earning_id			/*売上ID*/
,earnings_detail_table.num					/*売上明細ID*/
,earnings_detail_table.puroduct_id			/*商品ID*/
,earnings_detail_table.puroduct_name		/*商品名*/
,earnings_detail_table.price				/*単価*/
,earnings_detail_table.tax_fee				/*消費税*/
,earnings_detail_table.sold_amount			/*数量*/
,DATE_FORMAT(order_table.order_date,'%Y-%m-%d')		/*受注日時*/
,earnings_table.order_id					/*受注ID*/
 FROM
 earnings_table
 INNER JOIN earnings_detail_table
 ON earnings_table.earning_id=earnings_detail_table.earning_id
 INNER JOIN order_table
 ON order_table.order_id=earnings_table.order_id
 WHERE
 earnings_table.settlement_id=SETTLEMENT_ID
;