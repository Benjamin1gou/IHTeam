SELECT 
order_table.order_id,
order_table.costomer_name,
 CASE
	WHEN SUBSTRING( order_table.customer_id, 1, 1) <=> '0'
  THEN
		(
			SELECT
				corporation_customer_master.customer_name_kana
			FROM
				corporation_customer_master
			WHERE
				corporation_customer_master.customer_id = order_table.customer_id
			ORDER BY generation DESC
		)
	ELSE
		(
			SELECT
				personal_customer_master.customer_name_kana
			FROM
				personal_customer_master
			WHERE
				personal_customer_master.customer_id = order_table.customer_id
		)
 END AS user_name_kana,
 order_table.order_date
 FROM
 order_table;
 
 