INSERT INTO app_user(pk_user_id, email, password,reg_dt)
	VALUES(null, 'admin@email', (select MD5('administrator') from dual),
		(select sysdate() from dual));
INSERT INTO user_detail(pk_user_detail_id, first_name)
	VALUES(null, 'Administrator Account');
INSERT INTO AppUser_roles
	VALUES(1,'ROLE_USER'),(1,'ROLE_ADMIN');
INSERT INTO address(pk_address_id, post_addr1, state, fk_detail_id)
	VALUES(null, 'P O Box2361','gaborone', 1);
INSERT INTO menu(pk_menu_id, menu_type, menu_name, label,icon, fk_menu_id)
	VALUES(1, 'HOME', 'vehicles', 'vehicles','car.png',null),
		(2, 'HOME', 'property', 'property','building.png',null),
		(3, 'HOME', 'pets','pets','paw-print.png',null),
		(4, 'HOME', 'Electronics', 'electronics','responsive.png',null),
		(5, 'HOME', 'home & garden', 'home&garden','outdoor-fence.png',null),
		(6, 'HOME', 'sports & leisure','sports&leisure','jet-ski.png',null),
		(7, 'HOME', 'cosmetics','cosmetics','cosmetics.png',null),
		(8, 'HOME', 'fashion','fashion','shirt.png',null),
		(9, 'HOME', 'travel','travel','bed.png',null),
		(10, 'HOME', 'jobs','jobs','briefcase.png',null),
		(11, 'HOME', 'events','events','concert.png',null),
		(12, 'HOME', 'services', 'services','settings.png',null),
		(13, 'SIDEBAR', 'home', 'home',null,null),
		(14, 'SIDEBAR', 'my account','account',null,null),
		(15, 'SIDEBAR', 'manage','manage',null,null),
		(null, 'SUBMENU', 'cars', 'cars', null, 1),
		(null, 'SUBMENU', 'motocycles', 'motocycles', null, 1),
		(null, 'SUBMENU', 'farm vehicles', 'farmvehicles', null, 1),
		(null, 'SUBMENU', 'auto parts', 'autoparts', null, 1),
		(null, 'SUBMENU', 'house for rent', 'houseforrent', null, 2),
		(null, 'SUBMENU', 'house to wanted', 'housetoshare', null, 2),
		(null, 'SUBMENU', 'house for sale', 'houseforsale', null, 2),
		(null, 'SUBMENU', 'commercial for sale', 'commercialforsale', null, 2),
		(null, 'SUBMENU', 'plot for sale', 'plotforsale', null, 2),
		(null, 'SUBMENU', 'cats', 'cats', null, 3),
		(null, 'SUBMENU', 'dogs', 'dogs', null, 3),
		(null, 'SUBMENU', 'pets lost & found', 'petslost&found', null, 3),
		(null, 'SUBMENU', 'free pets', 'free pets', null, 3),
		(null, 'SUBMENU', 'mobile phones', 'mobilephones', null, 4),
		(null, 'SUBMENU', 'phone accessories', 'phoneaccessories', null, 4),
		(null, 'SUBMENU', 'tablets', 'tablets', null, 4),
		(null, 'SUBMENU', 'computers', 'computers', null, 4),
		(null, 'SUBMENU', 'TVs and radios', 'TVsandradios', null, 4),
		(null, 'SUBMENU', 'landscaping', 'landscaping', null, 5),
		(null, 'SUBMENU', 'appliances', 'appliances', null, 5),
		(null, 'SUBMENU', 'furniture', 'furniture', null, 5),
		(null, 'SUBMENU', 'antiques', 'antiques', null, 5),
		(null, 'SUBMENU', 'camping gear', 'campinggear', null, 6),
		(null, 'SUBMENU', 'tennis', 'tennis', null, 6),
		(null, 'SUBMENU', 'swimming', 'swimming', null, 6),
		(null, 'SUBMENU', 'books and games', 'booksandgames', null, 6),
		(null, 'SUBMENU', 'construction', 'construction', null, 12),
		(null, 'SUBMENU', 'pools and garden', 'poolsandgarden', null, 12),
		(null, 'SUBMENU', 'home maintenace', 'homemaitenance', null, 12),
		(null, 'SUBMENU', 'health and beauty', 'health and beauty', null, 12),
		(null, 'SUBMENU', 'dashboard', 'dashboard', null, 13),
		(null, 'SUBMENU','profile','profile', null, 14),
		(null, 'SUBMENU','preferences','preferences', null, 14),
		(null, 'SUBMENU','communication','communication', null, 14),
		(null, 'SUBMENU','activity', 'activity', null, 14),
		(null, 'SUBMENU','users', 'users', null, 15),
		(null, 'SUBMENU','menus', 'menus', null, 15),
		(null, 'SUBMENU','posts', 'posts', null, 15);
INSERT INTO advert(pk_advert_id, advert_status, submitted_date, fk_appuser_id, fk_menu_id)
	VALUES(null, 1, (select sysdate() from dual), 1, (select pk_menu_id from menu where label='houseforrent')),
			(null, 1, (select (sysdate()-10) from dual), 1,(select pk_menu_id from menu where label='autoparts')),
			(null, 1, (select (sysdate()-3) from dual), 1, (select pk_menu_id from menu where label='cars')),
			(null, 1, (select (sysdate()-1) from dual), 1, (select pk_menu_id from menu where label='cars')),
			(null, 1, (select (sysdate()-18) from dual), 1,(select pk_menu_id from menu where label='furniture')),
			(null, 1, (select (sysdate()-6) from dual), 1,(select pk_menu_id from menu where label='appliances')),
			(null, 1, (select (sysdate()-14) from dual), 1,(select pk_menu_id from menu where label='houseforsale')),
			(null, 1, (select (sysdate()-2) from dual), 1,(select pk_menu_id from menu where label='houseforrent')),
			(null, 1, (select (sysdate()-5) from dual), 1,(select pk_menu_id from menu where label='houseforrent')),
			(null, 1, (select (sysdate()-14) from dual), 1,(select pk_menu_id from menu where label='housetoshare')),
			(null, 1, (select (sysdate()-8) from dual), 1,(select pk_menu_id from menu where label='mobilephones')),
			(null, 1, (select (sysdate()-2) from dual), 1,(select pk_menu_id from menu where label='phoneaccessories'));
			--
			(null, 1, (select (sysdate()) from dual), 1,(select pk_menu_id from menu where label='computers'));
			(null, 1, (select (sysdate()) from dual), 1,(select pk_menu_id from menu where label='cars'));

INSERT INTO advert_detail (pk_detail_id, body, contact_email, contact_phone, group_cdn, group_count, group_size, group_uuid, location, title) VALUES
(null, 'A LARGE ROOM- can be shared by 2 people porcelain floor tiles, ceiling,\r\n			multi res, boundary wall, electric fence, sliding gate, common toilet, & shower. Close to UB, IDM, \r\n			BAC, BCA, riverwalk, fairground, main mall.', NULL, '77100180', NULL, 0, NULL, NULL, 'Extension 14', 'house for rent'),
(null, '2011 bmw120i with lots of extra Manual gear 150000km 80k Call 0813578313', NULL, '0813578313', NULL, 0, NULL, NULL, 'gaborone', '2011 bmw120i'),
(null, 'DUNLOP EXPRESS MOGODITSHANE Your Professional Tyre Fitment Centre Call 3938718 /3116423 App 71724544\r\n			Price valid till 30th September / Until stock last..', NULL, '3938718 /3116423', NULL, 0, NULL, NULL, 'Dunlop Express Mogoditshane', 'Tyres, Mag Wheels, Shocks, Brakes, Minor and major Services, Car Alarm, Car batteries, 3D Alignment'),
(null, 'GOLF POLO GTI MODEL 2013 DSG good condition.Low kilos 071kms.Full \r\n			serviced car.Aircon sound system.Price 130000 negotiable only serious buyer to view.Call 0814353868 /\r\n			0813040966 car in Dorado park', NULL, '0814353868 /0813040966', NULL, 0, NULL, NULL, 'mogoditshane', 'GOLF POLO GTI MODEL 2013'),
(null, 'Slumberland Concord Queen Mattress and Bed Combo Set Just Used for 1,5 Year by Diplomat R7,500 - \r\n			Gaborone Full Combo Set : 7500 BWP (Mattress 4000 BWP Bed 3000 Bwp, Sides 750 BWP )', NULL, '', NULL, 0, NULL, NULL, 'Gaborone', 'Slumberland Concord Queen Mattress and Bed Combo Set Just Used for 1,5 Year by Diplomat'),
(null, 'Fine Living Handheld Vacuum Cleaner. Plug in your \r\n			car lighter charger and clean around your car. Quick & Easy to use. Contacts: 74402139 P150.00', NULL, '74402139', NULL, 0, NULL, NULL, 'South East', 'Fine Living Handheld Car Vacuum Cleaners'),
(null, 'House for sale in Francistown Gerald 3 beds and 2 nd half \r\n			behind near GIPS and primary school Not fitted rooms Walled Power and water connected For 550k\r\n			Title deed For pic app 71273070', NULL, '71273070', NULL, 0, NULL, NULL, 'Francistown', 'House for sale in Francistown'),
(null, 'Compact 2 bedrooms in a multi res property in Tlokweng near Sefalana. \r\n			house is available first of October. 2,400-00 rent, 300-00 LEASE admin fee, and security deposit. \r\n			Call/whatsapp Ã£â‚¬â€¹ 76130462 Or inbox.', NULL, '76130462', NULL, 0, NULL, NULL, 'Tlokweng', 'Compact 2 bedrooms'),
(null, '3 bedroom bhc house available in Gaborone Phase2 on the 1st \r\n			October 2018. - kitchen has a fitted sink and a storeroom -bathroom has a bathtub and toilet, \r\n			-no landlord -Rent P3600 -security deposit P3600 Contact 71258149 or 76161232', NULL, '71258149/76161232', NULL, 0, NULL, NULL, 'phase 2', '3 bedroom bhc house for Rent'),
(null, 'M looking for a large room or 1 n half Even a Bachelor pad not above P1500.\r\n			Gabane,mogoditshane along route 4,tsolamosese,mmopane,metsimotlhabe.', NULL, '', NULL, 0, NULL, NULL, 'mogoditshane', 'room wanted'),
(null, '3month Samsung J1 Ace For Sale. P650 inclusive of headsets,protective \r\n			back cover and charger. Call or Text 71290199 if interested.', NULL, '71290199', NULL, 0, NULL, NULL, '', 'Samsung J1 Ace For Sale'),
(null, 'Head sets 25 pula Pouches 50 pula', NULL, '', NULL, 0, NULL, NULL, 'Broadhurst Industrial', 'Pouches for sale....clearance sale...J7 pro, iPhone 6s,J7, 7plus,J2 prime,J5 prime,J5'),
(null, 'i am selling a car in gaborone.', 'pearl@email', '71406569', 'https://ucarecdn.com/9096aec4-52f6-449d-beaf-23bddf1c3a43~3/', 3, 12261090, '9096aec4-52f6-449d-beaf-23bddf1c3a43~3', 'Gaborone', 'car for sale'),
(null, 'Dell Inspiron 5000 series i7 15.6" display HDMI', 'pearl@email', '71406569', 'https://ucarecdn.com/46dfc0eb-73a6-400e-a61e-5935e0500ae6~2/', 2, 2008639, '46dfc0eb-73a6-400e-a61e-5935e0500ae6~2', 'Gaborone', 'Dell laptop for sale');


	----
	INSERT INTO advert_detail(pk_detail_id, title, body, contact_phone, contact_email, location, 
		group_cdn, group_count, group_count, group_size, group_uuid)
		VALUES(null, 'VW Polo for sale', 'i am selling a car in gaborone.','71406569', 'pearl@email', 'gaborone',
		'https://ucarecdn.com/9096aec4-52f6-449d-beaf-23bddf1c3a43~3/', 3, 12261090, 
		'9096aec4-52f6-449d-beaf-23bddf1c3a43~3'),
		(null, 'Dell laptop for sale', 'Dell Inspiron 5000 series i7 15.6" display HDMI','71406569', 'pearl@email', 'gaborone',
		'https://ucarecdn.com/46dfc0eb-73a6-400e-a61e-5935e0500ae6~2/',2, 2008639, '46dfc0eb-73a6-400e-a61e-5935e0500ae6~2');
		
	INSERT INTO ad_picture (ad_pic_id, cdn_url, name, size, uuid, fk_ad_detail) VALUES
		(null, 'https://ucarecdn.com/6aecdb6b-fdb0-4e6d-adec-346125e86da0/', 'InkedIMAG0920_LI.jpg', 4732824, '6aecdb6b-fdb0-4e6d-adec-346125e86da0', 13),
		(null, 'https://ucarecdn.com/933bce1e-7c19-4093-90aa-26dba450ca19/', 'InkedIMAG0922_LI.jpg', 4666891, '933bce1e-7c19-4093-90aa-26dba450ca19', 13),
		(null, 'https://ucarecdn.com/50ec1901-352f-4a0f-8201-b7210a8df560/', 'InkedIMAG0925_LI.jpg', 2861375, '50ec1901-352f-4a0f-8201-b7210a8df560', 13),
		(null, 'https://ucarecdn.com/f7a19f90-5883-4bb9-a2e0-205d4ce154ef/', 'IMAG0925.jpg', 689469, 'f7a19f90-5883-4bb9-a2e0-205d4ce154ef', 14),
		(null, 'https://ucarecdn.com/9990cf13-39b0-4ee9-8997-062115475936/', 'IMAG1137.jpg', 1319170, '9990cf13-39b0-4ee9-8997-062115475936', 14);
