insert into roles(id,role_name)
VALUES(nextval('roles_id_seq'),'USER'),
      (nextval('roles_id_seq'),'ADMIN'),
      (nextval('roles_id_seq'),'SERVICE-DSK'),
      (nextval('roles_id_seq'),'FINANCE-ADM'),
      (nextval('roles_id_seq'),'CEO'),
      (nextval('roles_id_seq'),'JOKER-CEO');

insert into users_employees(employee_number,first_name,last_name,username,password,dob,address,phone_numb,email_address,bank_account,function)
VALUES(nextval('users_employees_employee_number_seq'),'Guus','Meeuwis','GuusMeeuwis','$2a$12$bPwSAoWb.Qq35isohKZyl.8XAfmrsW8K9qu9E8IpYFhAD4kXnVfRS','1972-03-23', 'Muziekweg 38 Eindhoven', '0622772277', 'guus_meeuwis@komwegaan.nl','NL25INGB0003123456','CEO');
--
INSERT INTO users_employees_roles (roles_id,users_employees_employee_number)
VALUES (5, 1);

insert into users_employees(employee_number,first_name,last_name,username,password,dob,address,phone_numb,email_address,bank_account,function)
VALUES(nextval('users_employees_employee_number_seq'),'Guus','Geluk','GuusGeluk','$2a$12$bPwSAoWb.Qq35isohKZyl.8XAfmrsW8K9qu9E8IpYFhAD4kXnVfRS','1978-04-23', 'geluksvogel 38 Utrecht', '0622772277', 'guus_geluk@komwegaan.nl','NL25INGB0003123456','JOKER-CEO');
--
INSERT INTO users_employees_roles (roles_id,users_employees_employee_number)
VALUES (6, 2);


insert into products(id,name,category,price_in_eur,packed_per_unit,in_stock,description,short_description)
VALUES(nextval('products_id_seq'), 'Tenderloin beef', 'meat', '52.00', '1 kg', '12', 'Irish blackangus beef of the hare delivered in whole-peaces of plus,minus 1 kg','Premium quality tenderloin 1kg'),
      (nextval('products_id_seq'), 'Chicken breast', 'meat', '12.25', '1 kg', '16', 'organic free-range chicken breast directly from our local farmer packed per 1 kg','Local chicken breast 1kg'),
      (nextval('products_id_seq'), 'Salmon side', 'fish', '32.14', 'c.a 1,2 kg', '9', 'Wild sockeye-salmon from the seas of Norway deliverd per side with cleanscaled skin,(MSC seal of approval)','Salmon side scaly'),
      (nextval('products_id_seq'), 'Sea bass', 'fish', '28.79', 'c.a 1 kg', '7', 'c.a 1 kg day-fresh sea bass (8 pcs fillets) high quality, carefully cleaned and scaled by us (MSC seal of approval)','Sea bass fillet c.a 125gr a peace'),
      (nextval('products_id_seq'), 'Vine tomatoes', 'vegitables', '3.59', '1 kg', '21', 'Premium biological vine tomatoes from matina bio pomodoro packed per box of 1 kg','Vino-tomatoes bio 1kg'),
      (nextval('products_id_seq'), 'Zucchini', 'vegitables', '5.20', '5 pcs', '31', 'Premium biological green zucchini from italian breed packed per box of 5 pcs','5 pcs zucchini italiano'),
      (nextval('products_id_seq'), 'Basil', 'herbs & spices', '2.29', '20 gr', '11', 'fresh bunch of basil stacked per 20 grams fresh from our farmers''greenhouses','fresh bunch of basil 20gr'),
      (nextval('products_id_seq'), 'Curly parsley', 'herbs & spices', '1.79', '20 gr', '18', 'fresh bunch of curley-parsley stacked per 20 grams fresh from our farmers''greenhouses','fresh bunch of parsley 20gr');

insert into customers(id,company,address,first_name,last_name,dob,phone_number,email_address,bank_account)
VALUES(nextval('customers_id_seq'),'Restaurant GOUD','Lloydstraat 204 Rotterdam', 'Herman', 'de Blijker', '1971-01-18', '0611223344', 'herman@kitchen.nl', 'NLINGB0003992567'),
      (nextval('customers_id_seq'),'Jamie''s Deli', '1118 AX Schiphol Amsterdam', 'Jamie', 'Oliver', '1978-11-23', '0677789855', 'jamie@cremebrulee.nl', 'NLINGB0021222567'),
      (nextval('customers_id_seq'),'La Rive', 'professor tulpplein 1 Amsterdam', 'Robert', 'Kranenborg','1964-03-01', '0622223334', 'robert@michellin.com', 'NLINGB0003277767');
--
-- insert into orders(id,customer_id,total_price_in_eur,order_date,order_time)
-- VALUES(nextval('orders_id_seq'), '1','165.47','2023-07-17','23:04');
--
--   insert into order_item_lines(id,order_id,product_id,quantity)
--     VALUES(nextval('order_item_lines_id_seq'),'1','1','3'),
--           (nextval('order_item_lines_id_seq'),'1','5','2'),
--           (nextval('order_item_lines_id_seq'),'1','7','1');