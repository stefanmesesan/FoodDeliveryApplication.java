CREATE TABLE users (id UUID PRIMARY KEY, firstName TEXT, lastName TEXT, email TEXT, phoneNumber TEXT, address TEXT, role VARCHAR, password VARCHAR);

CREATE TABLE restaurants (id UUID PRIMARY KEY, name TEXT, address TEXT, phoneNumber TEXT, rating FLOAT, addedBy UUID REFERENCES users(id));

CREATE TABLE menu_items (id UUID PRIMARY KEY, name TEXT, description TEXT, price FLOAT, spicy BOOLEAN, vegan BOOLEAN, vegetarian BOOLEAN, restaurant_id UUID REFERENCES restaurants(id));

CREATE TABLE orders (id UUID PRIMARY KEY, user_id UUID REFERENCES users(id), restaurant_id UUID REFERENCES restaurants(id), status VARCHAR, total_price FLOAT, created_at TEXT);

CREATE TABLE order_items (id UUID PRIMARY KEY, order_id UUID REFERENCES orders(id), menu_item_id UUID REFERENCES menu_items(id), quantity INT);

CREATE TABLE reviews (id UUID PRIMARY KEY, user_id UUID REFERENCES users(id), restaurant_id UUID REFERENCES restaurants(id), rating FLOAT, comment TEXT, created_at TEXT);

CREATE TABLE token (id UUID PRIMARY KEY, token TEXT, token_type TEXT, revoked BOOLEAN, expired BOOLEAN, user_id UUID REFERENCES users(id) );