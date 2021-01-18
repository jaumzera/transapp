-- Default account
insert into accounts (id, document_number, name, available_credit_limit)
values (nextval('account_id_seq'), '22233333311', 'Déx Mi', '1000.00');

-- Operation types
insert into operation_types (id, description, type)
values (nextval('operation_type_id_seq'), 'COMPRA A VISTA', 'DECREASE');
insert into operation_types (id, description, type)
values (nextval('operation_type_id_seq'), 'COMPRA PARCELADA', 'DECREASE');
insert into operation_types (id, description, type)
values (nextval('operation_type_id_seq'), 'SAQUE', 'DECREASE');
insert into operation_types (id, description, type)
values (nextval('operation_type_id_seq'), 'PAGAMENTO', 'INCREASE');
