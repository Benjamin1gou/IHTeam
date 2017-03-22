SELECT
  a.necessary_amount,
  a.picked_amount,
  a.inspected_amount,
  (
  SELECT
    b.stock_amount
  FROM
    stock_table b
  WHERE
    a.puroduct_id = b.puroduct_id
  ORDER BY
    b.entry_date DESC
  LIMIT 1
) stock
 FROM
  working_table a
 WHERE
/*if(PRODUCT_ID)start*/
  a.puroduct_id = PRODUCT_ID
/*if(PRODUCT_ID)end*/
;
