INSERT INTO USERS (id, first_name, last_name) VALUES ('1', 'John', 'Doe');
INSERT INTO USERS (id, first_name, last_name) VALUES ('2', 'Jane', 'Doe');


INSERT INTO LOCATIONS (id, city, state) VALUES ('1', 'Portland', 'Oregon');
INSERT INTO LOCATIONS (id, city, state) VALUES ('2', 'Boise', 'Idaho');

INSERT INTO USER_HAS_LOCATIONS (id, user_id, location_id) VALUES ('1', '1', '1');
INSERT INTO USER_HAS_LOCATIONS (id, user_id, location_id) VALUES ('2', '2', '1');
INSERT INTO USER_HAS_LOCATIONS (id, user_id, location_id) VALUES ('3', '2', '2');