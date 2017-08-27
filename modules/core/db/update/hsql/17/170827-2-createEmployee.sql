alter table SAMPLE_EMPLOYEE add constraint FK_SAMPLE_EMPLOYEE_USER foreign key (USER_ID) references SEC_USER(ID);
