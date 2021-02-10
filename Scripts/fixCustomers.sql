-- ensure all customer rows have CREATED, UPDATED and VERSION columns set
UPDATE CUSTOMER SET CREATED=CURRENT_TIMESTAMP, UPDATED=CURRENT_TIMESTAMP, VERSION=1;

SELECT * FROM CUSTOMER;