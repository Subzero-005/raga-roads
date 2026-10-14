CREATE TABLE IF NOT EXISTS bookings (
    booking_id      SERIAL PRIMARY KEY,
    concert_id      BIGINT       NOT NULL,
    artist_id       BIGINT       NOT NULL,
    booking_status  VARCHAR(50)  NOT NULL,
    strategy_applied VARCHAR(50) NOT NULL,
    booking_date    DATE         NOT NULL,
    rejection_reason VARCHAR(500)
);
