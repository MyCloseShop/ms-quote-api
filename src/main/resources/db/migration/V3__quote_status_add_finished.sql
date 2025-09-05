USE
`db-ms-quote`;

-- Update quote_status to include FINISHED
ALTER TABLE `quote`
    MODIFY `quote_status` ENUM ('PENDING', 'CONFIRMED', 'REFUSED', 'CANCELED', 'FINISHED') NOT NULL;
