INSERT INTO app_user(pk_user_id, email, password)
	VALUES(null, 'admin@email', 'administrator');
INSERT INTO user_detail(pk_user_detail_id, first_name)
	VALUES(null, 'Administrator Account');
INSERT INTO AppUser_roles
	VALUES(1,'ROLE_USER'),(1,'ROLE_ADMIN');
INSERT INTO menu(pk_menu_id, menu_type, menu_name, label,icon, fk_menu_id)
	VALUES(1, 'HOME', 'vehicles', 'vehicles','ic_drive_eta_black_48dp.png',null),
		(2, 'HOME', 'property', 'property','ic_business_black_48dp.png',null),
		(3, 'HOME', 'furniture','furniture','ic_event_seat_black_36dp.png',null),
		(9, 'SIDEBAR', 'home', 'home',null,null),
		(10, 'SIDEBAR', 'my account','account',null,null),
		(11, 'SIDEBAR', 'manage','manage',null,null),
		(null, 'SUBMENU', 'dashboard', 'dashboard', null, 9),
		(null, 'SUBMENU','profile','profile', null, 10),
		(null, 'SUBMENU','preferences','preferences', null, 10),
		(null, 'SUBMENU','communication','communication', null, 10),
		(null, 'SUBMENU','activity', 'activity', null, 10),
		(null, 'SUBMENU','users', 'users', null, 11),
		(null, 'SUBMENU','menus', 'menus', null, 11),
		(null, 'SUBMENU','posts', 'posts', null, 11);
