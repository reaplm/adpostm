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
INSERT INTO advert_detail(pk_detail_id, title, body, contact_phone, location)
	VALUES(null, 'house for rent', 'A LARGE ROOM- can be shared by 2 people porcelain floor tiles, ceiling,
			multi res, boundary wall, electric fence, sliding gate, common toilet, & shower. Close to UB, IDM, 
			BAC, BCA, riverwalk, fairground, main mall.', '77100180', 'Extension 14'), 
			(null, '2011 bmw120i', '2011 bmw120i with lots of extra Manual gear 150000km 80k Call 0813578313',
			'0813578313', 'gaborone'),
			(null, 'Tyres, Mag Wheels, Shocks, Brakes, Minor and major Services, Car Alarm, Car batteries, 3D Alignment',
			'DUNLOP EXPRESS MOGODITSHANE Your Professional Tyre Fitment Centre Call 3938718 /3116423 App 71724544
			Price valid till 30th September / Until stock last..', '3938718 /3116423', 'Dunlop Express Mogoditshane'),
			(null, 'GOLF POLO GTI MODEL 2013', 'GOLF POLO GTI MODEL 2013 DSG good condition.Low kilos 071kms.Full 
			serviced car.Aircon sound system.Price 130000 negotiable only serious buyer to view.Call 0814353868 /
			0813040966 car in Dorado park', '0814353868 /0813040966', 'mogoditshane'),
			(null, 'Slumberland Concord Queen Mattress and Bed Combo Set Just Used for 1,5 Year by Diplomat',
			'Slumberland Concord Queen Mattress and Bed Combo Set Just Used for 1,5 Year by Diplomat R7,500 - 
			Gaborone Full Combo Set : 7500 BWP (Mattress 4000 BWP Bed 3000 Bwp, Sides 750 BWP )', '', 'Gaborone'),
			(null, 'Fine Living Handheld Car Vacuum Cleaners','Fine Living Handheld Vacuum Cleaner. Plug in your 
			car lighter charger and clean around your car. Quick & Easy to use. Contacts: 74402139 P150.00',
			'74402139', 'South East'),
			(null, 'House for sale in Francistown','House for sale in Francistown Gerald 3 beds and 2 nd half 
			behind near GIPS and primary school Not fitted rooms Walled Power and water connected For 550k
			Title deed For pic app 71273070', '71273070', 'Francistown'),
			(null, 'Compact 2 bedrooms', 'Compact 2 bedrooms in a multi res property in Tlokweng near Sefalana. 
			house is available first of October. 2,400-00 rent, 300-00 LEASE admin fee, and security deposit. 
			Call/whatsapp 》 76130462 Or inbox.', '76130462', 'Tlokweng'),
			(null, '3 bedroom bhc house for Rent', '3 bedroom bhc house available in Gaborone Phase2 on the 1st 
			October 2018. - kitchen has a fitted sink and a storeroom -bathroom has a bathtub and toilet, 
			-no landlord -Rent P3600 -security deposit P3600 Contact 71258149 or 76161232','71258149/76161232', 'phase 2'),
			(null, 'room wanted','M looking for a large room or 1 n half Even a Bachelor pad not above P1500.
			Gabane,mogoditshane along route 4,tsolamosese,mmopane,metsimotlhabe.','','mogoditshane'),
			(null, 'Samsung J1 Ace For Sale', '3month Samsung J1 Ace For Sale. P650 inclusive of headsets,protective 
			back cover and charger. Call or Text 71290199 if interested.','71290199',''),
			(null, 'Pouches for sale....clearance sale...J7 pro, iPhone 6s,J7, 7plus,J2 prime,J5 prime,J5', 
			'Head sets 25 pula Pouches 50 pula','','Broadhurst Industrial');