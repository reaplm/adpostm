INSERT INTO app_user(pk_user_id, email, password)
	VALUES(null, 'admin@email', 'administrator');
INSERT INTO user_detail(pk_user_detail_id, first_name)
	VALUES(null, 'Administrator Account');
INSERT INTO AppUser_roles
	VALUES(1,'ROLE_USER'),(1,'ROLE_ADMIN');
INSERT INTO menu(pk_menu_id, menu_type, menu_name,url, label)
	VALUES(1, 'HOME', 'vehicles', '#', 'vehicles'),
		(2, 'HOME', 'property', '#', 'property'),
		(3, 'HOME', 'furniture', '#', 'furniture'),
		(4, 'SIDEBAR', 'home', '#','home'),
		(5, 'SIDEBAR', 'my account', '#','account'),
		(6, 'SIDEBAR', 'manage', '#','manage');
INSERT INTO sub_menu(pk_sub_menu_id, sub_menu_name,url, fk_menu_id)
	VALUES(null, 'dashboard', '#', 4),
		(null, 'profile', '#', 5),
		(null, 'preferences', '#', 5),
		(null, 'communication', '#', 5),
		(null, 'activity', '#', 5),
		(null, 'users', '#', 6),
		(null, 'menus', '#', 6),
		(null, 'posts', '#', 6);