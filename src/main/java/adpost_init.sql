INSERT INTO app_user(pk_user_id, email, password)
	VALUES(null, 'admin@email', 'administrator');
INSERT INTO user_detail(pk_user_detail_id, first_name)
	VALUES(null, 'Administrator Account');
INSERT INTO AppUser_roles
	VALUES(1,'ROLE_USER'),(1,'ROLE_ADMIN');
INSERT INTO menu(pk_menu_id, menu_type, menu_name,url, label,icon)
	VALUES(1, 'HOME', 'vehicles', '#', 'vehicles','ic_drive_eta_black_48dp.png'),
		(2, 'HOME', 'property', '#', 'property','ic_business_black_48dp.png'),
		(3, 'HOME', 'furniture', '#', 'furniture','ic_event_seat_black_36dp.png'),
		(4, 'HOME', 'accomodation', '#', 'accomodation','ic_hotel_black_36dp.png'),
		(5, 'HOME', 'pets', '#', 'pets','ic_pets_black_48dp.png'),
		(6, 'HOME', 'fitness', '#', 'fitness','ic_fitness_center_black_36dp.png'),
		(7, 'HOME', 'resturants', '#', 'resturants','ic_local_dining_black_36dp.png'),
		(8, 'HOME', 'sports', '#', 'sports','ic_pool_black_48dp.png'),
		(9, 'SIDEBAR', 'home', '#','home',''),
		(10, 'SIDEBAR', 'my account', '#','account',''),
		(11, 'SIDEBAR', 'manage', '#','manage','');
INSERT INTO sub_menu(pk_menu_id, menu_name,label, fk_menu_id)
	VALUES(null, 'dashboard', 'dashboard', 9),
		(null, 'profile','profile', 10),
		(null, 'preferences','preferences', 10),
		(null, 'communication','communication', 10),
		(null, 'activity', 'activity', 10),
		(null, 'users', 'users', 11),
		(null, 'menus', 'menus', 11),
		(null, 'posts', 'posts', 11);