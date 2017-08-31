INSERT INTO app_user(pk_user_id, email, password)
	VALUES(null, 'admin@email', 'administrator');
INSERT INTO user_detail(pk_user_detail_id, first_name)
	VALUES(null, 'Administrator Account');
INSERT INTO AppUser_roles
	VALUES(1,'ROLE_USER'),(1,'ROLE_ADMIN');
	