USE `db-ms-quote`;

CREATE TABLE IF NOT EXISTS `quote`
(
    quote_id          BINARY(16)                                           NOT NULL PRIMARY KEY,
    name              VARCHAR(255)                                         NOT NULL,
    description       TEXT,
    price             DECIMAL(10, 2)                                       NOT NULL,
    type              ENUM ('FLAT_RATE', 'HOURLY_RATE')                    NOT NULL,
    scheduled_hours   INT                                                  NOT NULL,
    taken_hours       INT                                                  NOT NULL,
    quote_status      ENUM ('PENDING', 'CONFIRMED', 'REFUSED', 'CANCELED') NOT NULL,
    prestation_status ENUM ('PENDING', 'DONE', 'CANCELED')                 NOT NULL,
    paiment_status    ENUM ('PENDING', 'PAID', 'REFUNDED', 'CANCELED')     NOT NULL,
    created_at        datetime DEFAULT NOW()                               NULL,
    updated_at        datetime DEFAULT NOW()                               NULL
);
