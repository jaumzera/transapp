-- Default account
insert into accounts (id, document_number, name)
values (nextval('account_id_seq'), '22233333311', 'Déx Mi');

-- Operation types
insert into operation_types (id, description)
values (nextval('operation_type_id_seq'), 'COMPRA A VISTA');
insert into operation_types (id, description)
values (nextval('operation_type_id_seq'), 'COMPRA PARCELADA');
insert into operation_types (id, description)
values (nextval('operation_type_id_seq'), 'SAQUE');
insert into operation_types (id, description)
values (nextval('operation_type_id_seq'), 'PAGAMENTO');
