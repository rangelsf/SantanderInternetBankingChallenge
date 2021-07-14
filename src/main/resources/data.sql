insert into clients (account_number,  balance,  birth_date,  exclusive_plan,  name) values  ('1', 300.50, parsedatetime('30/06/1995', 'dd/MM/yyyy'), true, 'Rangel1');
insert into clients (account_number,  balance,  birth_date,  exclusive_plan,  name) values ('2', 450.50, parsedatetime('25/02/1990', 'dd/MM/yyyy'), false, 'Rangel2');



insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (2.0,1, 298.5, 300.5, 0.0, 'W', '2021-07-08 15:12:49.094');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (101.0, 1, 197.5, 298.5, 0.0, 'W', '2021-07-08 15:12:52.625');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (150.25, 1, 347.75, 197.5, null, 'D', '2021-07-08 15:13:05.667');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (150.23, 1, 497.98, 347.75, null, 'D', '2021-07-08 15:13:08.59');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (180.23, 1, 678.21, 497.98, null, 'D', '2021-07-08 15:13:11.667');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (301.0, 1, 377.21, 678.21, 0.0, 'W', '2021-07-08 15:13:17.475');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (18000.23, 2, 18450.73, 450.5, null, 'D', '2021-07-08 15:13:33.228');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (20000.23, 2, 38450.96, 18450.73, null, 'D', '2021-07-08 15:13:38.374');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (20000.23, 2, 58451.19, 38450.96, null, 'D', '2021-07-08 15:13:39.614');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (301.0, 2, 58147.18, 58451.19, 3.01, 'W', '2021-07-08 15:13:42.578');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (302.0, 2, 57842.16, 58147.18, 3.02, 'W', '2021-07-08 15:13:45.802');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (500.0, 2, 57337.16, 57842.16, 5.0, 'W', '2021-07-08 15:13:52.521');
insert into transactions (amount, client_id, new_balance, old_balance, tax, type, updated_at) values (10000.0, 2, 47237.16, 57337.16, 100.0, 'W', '2021-07-08 15:13:57.739');




