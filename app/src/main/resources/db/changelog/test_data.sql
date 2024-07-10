insert into applications (id, code)
values (1, 'logging-service'),
       (2, 'api-service');


INSERT
INTO log_records (timestamp, level_id, application_id, revision, thread, file, line,
                  message, exception)
VALUES (CURRENT_TIMESTAMP, 1, 1, 'rev-123', 'main-thread', 'ExampleClass.java', 42,
        'This is a test log message', 'NullPointerException'),
       (CURRENT_TIMESTAMP, 2, 2, NULL, 'background-thread', 'BackgroundProcess.java', 101,
        'Background process initiated', NULL),
       (CURRENT_TIMESTAMP, 3, 1, 'rev-124', 'service-thread', 'ServiceHandler.java', 58,
        'Service request processed successfully', NULL);
