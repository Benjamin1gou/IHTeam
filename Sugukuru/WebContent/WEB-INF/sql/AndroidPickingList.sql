SELECT
  a.puroduct_id,
  b.puroduct_name,
  c.rack_name,
  (SELECT d.stock_amount 
    FROM stock_table d 
    WHERE d.puroduct_id = b.puroduct_id 
    ORDER BY d.entry_date 
    DESC LIMIT 1) AS stock_amount, 
  a.necessary_amount,
  a.picked_amount,
  a.inspected_amount,
  (CASE
    WHEN a.necessary_amount <= a.picked_amount AND a.necessary_amount <= a.inspected_amount
      THEN '検品済'
    WHEN a.necessary_amount <= a.picked_amount
      THEN 'ピッキング済'
    ELSE '未作業'
  END) AS state, 
  (SELECT f.amount
  	FROM product_unit_table f
  	WHERE a.puroduct_id = f.puroduct_id
  	ORDER BY f.puroduct_id DESC
  	LIMIT 1) as unit 
FROM 
  working_table a 
 INNER JOIN puroduct_master b ON a.puroduct_id = b.puroduct_id
 INNER JOIN rack_master c ON b.rack_id = c.rack_id 
/*if(PRODUCT_ID)start*/
 WHERE a.puroduct_id = PRODUCT_ID
/*if(PRODUCT_ID)end*/
/*if(STATE)start*/
 HAVING state = STATE
/*if(STATE)end*/
;