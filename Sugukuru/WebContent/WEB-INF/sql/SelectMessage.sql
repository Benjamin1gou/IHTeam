SELECT
 message_id				/* メッセージID */
,message				/* メッセージ */
 FROM
 messages_master
 WHERE
 message_id LIKE MESSAGE_ID
 ;