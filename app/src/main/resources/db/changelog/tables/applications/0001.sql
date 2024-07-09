CREATE TABLE applications
(
    id    BIGSERIAL PRIMARY KEY,
    code  TEXT NOT NULL,
    title TEXT NOT NULL
);

CREATE UNIQUE INDEX ux_applications_code ON applications (code);
