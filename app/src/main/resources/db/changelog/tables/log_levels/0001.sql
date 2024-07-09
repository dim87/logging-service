CREATE TABLE log_levels
(
    id    BIGSERIAL PRIMARY KEY,
    code  TEXT NOT NULL,
    title TEXT NOT NULL

);

CREATE UNIQUE INDEX ux_log_levels_code ON applications (code);

INSERT INTO log_levels (id, code, title)
values (1, 'ERROR', 'Error'),
       (2, 'WARN', 'Warning'),
       (3, 'INFO', 'Info'),
       (4, 'DEBUG', 'Debug');
