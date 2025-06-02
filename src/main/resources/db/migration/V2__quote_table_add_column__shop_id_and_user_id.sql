USE `db-ms-quote`;

ALTER TABLE `quote`
    ADD COLUMN `shop_id` BINARY(16) NOT NULL AFTER `quote_id`,
    ADD COLUMN `user_id` BINARY(16) NOT NULL AFTER `shop_id`;
