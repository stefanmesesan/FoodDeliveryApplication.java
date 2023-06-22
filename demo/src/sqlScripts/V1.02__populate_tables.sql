insert into users values
('56b8a248-3784-4c7e-9ac9-524857e5b9a1', 'Popescu', 'Dan', 'poescu.dan@yahoo.com', '0749312456', 'Adresa 1', 'CUSTOMER', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('56b8a248-3784-4c7e-9ac9-524857e5b9a2', 'Popescu', 'Danniela', 'poescu.daniela@yahoo.com', '0749354456', 'Adresa 2', 'CUSTOMER', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('56b8a248-3784-4c7e-9ac9-524857e5b9a3', 'Gogonel', 'Ion', 'gogonel.ion@yahoo.com', '0749312456', 'Adresa 3', 'CUSTOMER', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('56b8a248-3784-4c7e-9ac9-524857e5b9a5', 'Pop', 'Ana', 'pop.ana@yahoo.com', '0743542456', 'Adresa 4', 'CUSTOMER', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('56b8a248-3784-4c7e-9ac9-52457e5b9a34', 'Gigel', 'Frone', 'gigel.frone@yahoo.com', '0749342356', 'Adresa 5', 'CUSTOMER', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('56b8a248-3784-4c7e-9ac9-524857e5b9a7', 'Maria', 'Vasile', 'maria.vasile@yahoo.com', '074345456', 'Adresa 6', 'RESTAURANT_OPERATOR', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('56b8a248-3784-4c7e-9ac9-524857e5b9a8', 'Grigore', 'Dan', 'grigore.dan@yahoo.com', '0749456456', 'Adresa 7', 'RESTAURANT_OPERATOR', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('c99fb8d4-1e12-4f06-b60e-1ff2a3c850f5', 'Smith', 'John', 'john.smith@example.com', '0749354456', 'Strada Principală nr. 10', 'DELIVERY_GUY','$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa' ),
('3a6fc680-4787-4eae-a82b-6383bb6f78ce', 'Johnson', 'Sarah', 'sarah.johnson@example.com', '0749354456', 'Strada Secundară nr. 5', 'DELIVERY_GUY', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa'),
('5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', 'Brown', 'Gabriel', 'gabriel.brown@example.com', '0749354451', 'Strada Principală nr. 106', 'ADMIN', '$2a$10$01NqRR0PbISjR00CgL68B.CesnNTgub1amjbwkPhM/mM5f/c/Mbwa');


INSERT INTO restaurants VALUES
('90ed98df-d743-4d60-a5a1-6a460a08c982', 'Restaurant Roata', 'Adresa 1', '1234567890', 4.5, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', false),
('14a8624d-09c1-4f2a-a650-239d10d62b53', 'Samsara Foodhouse', 'Adresa 2', '0987654321', 3.8, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', true),
('c0f09b2e-209f-46c4-998b-1c75dbdbf526', 'Casa Boema', 'Adresa 3', '9876543210', 4.2, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', false),
('23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 'Restaurant Via', 'Adresa 4', '4567890123', 4.0, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', false),
('a24fbdd4-3ec6-4f79-ae7b-3dfecb2d1795', 'Ciao New York', 'Adresa 5', '9870123456', 3.5, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', true),
('4c75f256-d89c-48a1-8d84-1f8c6b7e2df8', ' Tortelli Pasta Bar', 'Adresa 6', '5432109876', 4.7, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', false),
('f8eb334f-9b11-442b-a305-6b70eb0f5f9c', ' Makeba', 'Adresa 7', '0123456789', 3.9, '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', false),
('877097db-3f41-4ad3-8e79-9c46c2218e3b', 'Jaxx ', 'Adresa 8', '6789012345', 4.1,  '5c9d7d37-94d9-4f7f-8a61-7c6d0ebd4d0e', true);


INSERT INTO reviews VALUES
('0d2c6b5f-55e0-4f1b-b006-81f5830e1830', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 4.5, 'Great food and service!', '2023-06-21'),
('2e8e8195-7a88-45f2-a6ed-117e4a1b7c69', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 3.8, 'Average experience.', '2023-06-20'),
('7548a8b9-8e32-4023-bec9-13c8434a9257', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 4.2, 'Highly recommended!', '2023-06-19'),
('1f9d5d0e-853e-4d1a-9f5d-2640745f3e3b', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 4.0, 'Good value for money.', '2023-06-18'),
('d51eac4e-51fb-46b1-ae50-6b99e4382930', '56b8a248-3784-4c7e-9ac9-524857e5b9a1', '23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 3.5, 'Could be better.', '2023-06-17'),
('f6e59e88-c5e0-4b63-983f-ba81d2a86718', '56b8a248-3784-4c7e-9ac9-524857e5b9a1', '23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 4.7, 'Excellent service!', '2023-06-16'),
('9733d9d1-392a-4464-a116-d48a22be5e6f', '56b8a248-3784-4c7e-9ac9-524857e5b9a7', 'f8eb334f-9b11-442b-a305-6b70eb0f5f9c', 3.9, 'Decent food.', '2023-06-15'),
('b37798b6-5c7d-4af3-aeab-3500e2079138', '56b8a248-3784-4c7e-9ac9-524857e5b9a7', 'f8eb334f-9b11-442b-a305-6b70eb0f5f9c', 4.1, 'Nice ambiance.', '2023-06-14'),
('e84db9a0-3c6c-4c91-93a3-7ccbcfa71b3f', '56b8a248-3784-4c7e-9ac9-524857e5b9a1', 'f8eb334f-9b11-442b-a305-6b70eb0f5f9c', 4.3, 'Friendly staff.', '2023-06-13'),
('0d35f327-086e-4d8a-b6b0-0139e1fc9bfb', '56b8a248-3784-4c7e-9ac9-524857e5b9a7', '14a8624d-09c1-4f2a-a650-239d10d62b53', 3.7, 'Mediocre experience.', '2023-06-12');

INSERT INTO orders (id, user_id, restaurant_id, status, total_price, created_at)
VALUES ('f9c13eae-8f3c-4b87-87a3-16e5c120c7d7', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 'NEW', 25.99, '2023-06-21'),
       ('a60b3f7d-86fd-4d4e-935a-20c4c45d7c9e', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 'ORDER_RECEIVED', 45.75, '2023-06-20'),
       ('9b49a1d0-7450-4a3d-9793-c16b7b976ba8', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 'ORDER_CANCELED', 15.50, '2023-06-19'),
       ('86428c8a-78da-41be-9c34-880605f24c0e', '56b8a248-3784-4c7e-9ac9-524857e5b9a1', '23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 'DELIVERED', 10.99, '2023-06-18'),
       ('0db4f23e-45f3-42a9-8936-20f37d6e3c47', '56b8a248-3784-4c7e-9ac9-524857e5b9a1', '23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 'ON_ITS_WAY', 32.20, '2023-06-17'),
       ('23662a2a-0b89-4fc5-816b-3e0e7f88e52a', '56b8a248-3784-4c7e-9ac9-524857e5b9a1', '23e34c0f-6b67-49a1-bb8d-8e669f03b5f6', 'DELIVERED', 28.75, '2023-06-16'),
       ('34d8d36c-29f9-4c72-983d-040823098c6b', '56b8a248-3784-4c7e-9ac9-524857e5b9a7', '14a8624d-09c1-4f2a-a650-239d10d62b53', 'ON_ITS_WAY', 19.99, '2023-06-15'),
       ('e08e8f86-3e0b-40c4-88b6-0e9f8f8e4609', '56b8a248-3784-4c7e-9ac9-524857e5b9a7', '14a8624d-09c1-4f2a-a650-239d10d62b53', 'ORDER_CANCELED', 8.50, '2023-06-14'),
       ('a9d899e1-46e5-4e08-9304-77c38e9a2fc7', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 'ORDER_RECEIVED', 12.99, '2023-06-13'),
       ('55f2b8d6-9979-412e-84d5-29d0e912f94d', '56b8a248-3784-4c7e-9ac9-524857e5b9a5', '90ed98df-d743-4d60-a5a1-6a460a08c982', 'NEW', 37.45, '2023-06-12');

INSERT INTO menu_items (id, name, description, price, spicy, vegan, vegetarian, restaurant_id)
VALUES ('e66abf34-8626-4a49-b0e6-3b38b22e2d6a', 'Item 1', 'Description 1', 12.99, true, false, true, '90ed98df-d743-4d60-a5a1-6a460a08c982'),
       ('a19a5db6-4833-4d68-9422-77b2a5c0369d', 'Item 2', 'Description 2', 9.99, false, true, true, '90ed98df-d743-4d60-a5a1-6a460a08c982'),
       ('8245c4d2-29d3-4e57-967b-5fcf39e78b2f', 'Item 3', 'Description 3', 14.50, true, false, false, '90ed98df-d743-4d60-a5a1-6a460a08c982'),
       ('51a716de-8e8f-439a-9f0d-6656772e6b1d', 'Item 4', 'Description 4', 8.75, false, true, true, '14a8624d-09c1-4f2a-a650-239d10d62b53'),
       ('c3c8c73d-1026-4a37-bae0-d3b89d84390c', 'Item 5', 'Description 5', 17.99, true, true, false, '14a8624d-09c1-4f2a-a650-239d10d62b53'),
       ('f1e0f97e-dc74-4b39-8be0-13a04c789e86', 'Item 6', 'Description 6', 11.25, false, false, true, '14a8624d-09c1-4f2a-a650-239d10d62b53'),
       ('b2fe9a51-3e56-4b0e-9763-70899e5d6767', 'Item 7', 'Description 7', 9.99, true, true, true, '14a8624d-09c1-4f2a-a650-239d10d62b53'),
       ('d5f9541e-5e7a-4e11-9edc-7e3d54c6077e', 'Item 8', 'Description 8', 13.50, false, false, false, 'a24fbdd4-3ec6-4f79-ae7b-3dfecb2d1795'),
       ('4f88f4c9-174a-4b8d-bf02-7d8ce9e8e9f1', 'Item 9', 'Description 9', 10.99, true, false, true, 'a24fbdd4-3ec6-4f79-ae7b-3dfecb2d1795'),
       ('8bda5d1c-8233-4d79-bc85-d6b8f547aeb7', 'Item 10', 'Description 10', 16.75, false, true, false, 'a24fbdd4-3ec6-4f79-ae7b-3dfecb2d1795');


INSERT INTO order_items (id, order_id, menu_item_id, quantity)
VALUES ('830dd3df-70b6-416b-983d-327e65aeef65', 'f9c13eae-8f3c-4b87-87a3-16e5c120c7d7', 'e66abf34-8626-4a49-b0e6-3b38b22e2d6a', 2),
       ('4717e0d2-2381-4ad2-bd9a-5964db759239', 'f9c13eae-8f3c-4b87-87a3-16e5c120c7d7', 'a19a5db6-4833-4d68-9422-77b2a5c0369d', 1),
       ('f8a24a5e-af68-4e83-9a68-2e6bcb59c350', 'f9c13eae-8f3c-4b87-87a3-16e5c120c7d7', '8245c4d2-29d3-4e57-967b-5fcf39e78b2f', 3),
       ('e61e9ef9-9017-4c6a-9a02-08a4e0eb08c7', 'f9c13eae-8f3c-4b87-87a3-16e5c120c7d7', '51a716de-8e8f-439a-9f0d-6656772e6b1d', 2),
       ('1b3f8c7f-5246-47d3-bb61-0d58e6353445', '34d8d36c-29f9-4c72-983d-040823098c6b', 'c3c8c73d-1026-4a37-bae0-d3b89d84390c', 1),
       ('8e072e8d-8433-4b6a-8b76-53cbf6c641c9', '34d8d36c-29f9-4c72-983d-040823098c6b', 'f1e0f97e-dc74-4b39-8be0-13a04c789e86', 2),
       ('d8ad29ef-8a5c-4e36-9eac-800b3e3c043a', '34d8d36c-29f9-4c72-983d-040823098c6b', 'b2fe9a51-3e56-4b0e-9763-70899e5d6767', 1),
       ('9cb0f7c5-d69e-4d60-b7a9-9a2631ce27be', '34d8d36c-29f9-4c72-983d-040823098c6b', 'd5f9541e-5e7a-4e11-9edc-7e3d54c6077e', 3),
       ('b7300e6a-27da-4c23-9c77-29294c878924', '55f2b8d6-9979-412e-84d5-29d0e912f94d', '4f88f4c9-174a-4b8d-bf02-7d8ce9e8e9f1', 2),
       ('6d422b4b-8292-4d42-bb85-4a72a7e0c5d7', '55f2b8d6-9979-412e-84d5-29d0e912f94d', '8bda5d1c-8233-4d79-bc85-d6b8f547aeb7', 1);




