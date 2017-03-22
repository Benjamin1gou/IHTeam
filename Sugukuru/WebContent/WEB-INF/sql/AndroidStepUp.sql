UPDATE 
 order_details_table a 
INNER JOIN 
 order_table b ON a.order_id = b.order_id 
SET 
/*if(STEP)start*/ 
 step = STEP 
/*if(STEP)end*/ 
WHERE 
/*if(SHIPPING_DATE)start*/ 
 b.shipping_schedule_date = SHIPPING_DATE 
/*if(SHIPPING_DATE)end*/ 
/*if(PRODUCT_ID)start*/ 
 AND a.puroduct_id = PRODUCT_ID 
/*if(PRODUCT_ID)end*/ 
;