CREATE TABLE log_records
(
    id             UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    timestamp      TIMESTAMP not null,
    level_id       BIGINT REFERENCES log_levels (id) ON DELETE CASCADE,
    application_id BIGINT REFERENCES applications (id) ON DELETE CASCADE,
    revision       TEXT DEFAULT NULL,
    thread         TEXT      NOT NULL,
    file           TEXT      NOT NULL,
    line           INT       NOT NULL,
    message        TEXT DEFAULT NULL,
    exception      TEXT DEFAULT NULL
);

CREATE INDEX ix_log_records_application_id ON log_records (application_id);
CREATE INDEX ix_log_records_level_id ON log_records (level_id);
CREATE INDEX ix_log_records_revision ON log_records (revision);
