insert into SAMPLE_CUSTOMER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, DISCOUNT) 
values ('1797f54d-5bec-87a6-4330-d958955743a2', 1, '2017-08-13 10:03:27', 'anonymous', '2017-08-13 10:03:27', null, null, null, 'Homer Simpson', null);

insert into SAMPLE_CUSTOMER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, DISCOUNT) 
values ('38986e42-9db4-b12f-0ee1-e1ad1b1fb14e', 1, '2017-08-13 10:03:46', 'anonymous', '2017-08-13 10:03:46', null, null, null, 'Marge Simpson', null);

insert into SAMPLE_ORDER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DATE_, CUSTOMER_ID, AMOUNT) 
values ('9246bff3-4e69-427b-e1e8-3296ee913be3', 1, '2017-08-13 10:25:31', 'anonymous', '2017-08-13 10:25:31', null, null, null, '2017-08-01', '1797f54d-5bec-87a6-4330-d958955743a2', 100.00);

insert into SAMPLE_ORDER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DATE_, CUSTOMER_ID, AMOUNT) 
values ('b00fe033-40f9-c70f-fc33-5452eeca6a45', 1, '2017-08-13 10:25:42', 'anonymous', '2017-08-13 10:25:42', null, null, null, '2017-08-02', '1797f54d-5bec-87a6-4330-d958955743a2', 200.00);

insert into SAMPLE_ORDER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DATE_, CUSTOMER_ID, AMOUNT) 
values ('612766ac-ceba-f528-df9a-754ec1923ef2', 1, '2017-08-13 10:25:52', 'anonymous', '2017-08-13 10:25:52', null, null, null, '2017-08-03', '38986e42-9db4-b12f-0ee1-e1ad1b1fb14e', 150.00);

insert into SAMPLE_ORDER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DATE_, CUSTOMER_ID, AMOUNT) 
values ('27f03850-12cc-7331-698a-5d4f451ffc4b', 1, '2017-08-13 10:26:03', 'anonymous', '2017-08-13 10:26:03', null, null, null, '2017-08-04', '38986e42-9db4-b12f-0ee1-e1ad1b1fb14e', 250.00);


insert into SEC_USER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, IP_MASK)
values ('aec21282-5f20-7da6-ec92-d65bc03686d7', 1, '2017-08-27 13:36:33', 'user', '2017-08-27 13:36:33', null, null, null, 'n.flanders', 'n.flanders', 'a751f1a6baeb22c5d725ca24648e9753dc39c09d', 'Ned Flanders', 'Ned', 'Flanders', null, null, null, 'en', null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null);

insert into SEC_USER (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, IP_MASK)
values ('4342b2a7-6a43-791c-1585-f3452fe7fcad', 1, '2017-08-27 13:40:24', 'user', '2017-08-27 13:40:24', null, null, null, 'l.leopard', 'l.leopard', '88a50ce796b06a38b7d33649dd02189c4a2d86ff', 'Lenny Leopard', 'Lenny', 'Leopard', null, null, null, 'en', null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null);

insert into SAMPLE_EMPLOYEE (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, ADDRESS, USER_ID)
values ('05ab3e7c-1f3c-b209-28ab-a64cf817e3f5', 1, '2017-08-27 13:36:33', 'user', '2017-08-27 13:36:33', null, null, null, 'Ned Flanders', '711-2880 Nulla St. Sprinfield', 'aec21282-5f20-7da6-ec92-d65bc03686d7');

insert into SAMPLE_EMPLOYEE (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, ADDRESS, USER_ID)
values ('a7af4766-971f-c830-d708-4878fd9c499c', 1, '2017-08-27 13:40:24', 'user', '2017-08-27 13:40:24', null, null, null, 'Lenny Leopard', '7292 Dictum Av., Springfield', '4342b2a7-6a43-791c-1585-f3452fe7fcad');
