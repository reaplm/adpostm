function CloseEditAd(){
	window.location = "/adpostm/admin/posts";
}

/**
 * Toggle between edit and save
 * @returns
 */
function ToggleEdit(){
	
}
function openUploadCareDialog(currfile){
	var file = uploadcare.fileFrom('url',currfile);
		 uploadcare.openDialog(file,{
			 imagesOnly: true,
			 maxSize: (1024 * 1024),
			 crop: 'free',
			 multiple: false
		 }).done(function(file){
			 file.done(function(fileInfo){
				 if(fileInfo.isStored == true){
					 var imageData = new Array();
					 imageData["cdnUrl"] = fileInfo.cdnUrl;
					 imageData["name"] = fileInfo.name;
					 imageData["uuid"] = fileInfo.uuid;
					 UpdateProfileImage(fileInfo.cdnUrl, fileInfo.name, fileInfo.uuid);
					 
				 }
				 else{
					 alert("Failed to save image. Please try again later.");
				 }
			 }).fail(function(error, fileInfo){
				 alert("Failed: "+error);
			 });
		 });
}

function UpdateProfileImage(cdnUrl, name, uuid){
	var imageData = {cdnUrl: cdnUrl, name: name, uuid: uuid};
	$.ajax({
		type: "post",
		url:	"/adpostm/user/updatepic?cdnUrl="+cdnUrl+"&name="+name+"&uuid="+uuid,
		contentType: "application/json",
		error: function(jqXHr, textStatus, errorThrown){
			alert("Error updating image.\n Error thrown is: " + errorThrown)
			window.location.reload();
		} ,
		success: function(data){
			alert("Picture update! ("+data+")")
			window.location.reload();
		}//data: JSON.stringify(imageData),
	});
	
}

function contentToggle(){
	$(".content-fluid").toggleClass("col-sm-12 col-sm-8");
}
//================================================CALLBACKS===============================
/**
 * Get enumerated menu type 
 * @param url
 * @param callback
 * @returns
 */
function GetMenuType(url, callback){
	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		error: function(jqXhr, status, errorThrown){
			alert("Fetching menu type failed.");
		},
		success: function(data){
			callback(data);
		}
	});
}
/**
 * Fetch user details 
 * @param url
 * @param callback
 * @returns
 */
function GetUserDetail(url, callback){
	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		error: function(jqXhr, textStatus, errorThrown){
			alert("There was an error fetching your details.");
		},
		success: function(data){
			callback(data);
		}
	});
}

/**
 * Ajax call to fetch menu details
 * @param url
 * @param callback
 * @returns
 */
function GetMenuDetail(url, callback){
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		cache: false,
		error: function(jqXHr, textStatus, errorThrown){
			alert("Error fetching menu details.\n Error thrown is: " + errorThrown)
		} ,
		success: function(data){
			callback(data);
			
		}
	});
}
function GetMenuList(url, callback){
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		error: function(jqHxrerrorText, errorThrown){
			alert("Error fetching menu list.\n Error thrown is: " + errorThrown)
		},
		success: function(data){
			callback(data);
		}
	});
}
function GetSubMenu(url, callback){
	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		error: function(jqxHr,errorText,errorThrown){
			alert("Failed to fetch sub-category");
		},
		success: function(data){
			callback(data);
		}
	});
}
/**
 * Get advert details and return to calling function
 * @param url
 * @param callback
 * @returns
 */
function GetAdvertDetail(url, callback){
	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		error: function(jqxHr,errorText,errorThrown){
			alert("Failed to fetch advertDetails");
		},
		success: function(data){
			callback(data);
		}
	});
}
//============================================SUBMIT===============================
function DeletePost(advertId){
	$.ajax({
		type: "get",
		url: "/adpostm/advert/delete?id="+advertId,
		
	}).done(function(data, textStatus, jqXHR){
		alert("Response from server: " + data);
		window.location.reload();
	}).fail(function(jqXHR, textStatus, errorThrown){
		console.log(arguments);
		alert("An error occured. Delete menu failed. - " + errorThrown );
	});
}
/**
 * Delete a menu item using id.
 * @param menuId
 * @returns
 */
function DeleteMenu(menuId){
	if(confirm("Are you sure you want to delete this menu?")){
		$.ajax({
			type: "get",
			url: '/adpostm/menu/delete?id='+menuId,
		})
		.done(function(data,textStatus, jqXHR){
			alert("Response from server: " + data);
			window.location.reload();
		})
		.fail(function(jqXHR, textStatus, errorThrown ){
			console.log(arguments);
			alert("An error occured. Delete menu failed. - " + errorThrown );
		});
	}
	
}
/**
 * Update value of admin checkbox
 * @param menuId
 * @param element
 * @returns
 */
function UpdateMenuAdmin(menuId, element){

	var checked = element.checked;
	
	$.ajax({
		url: '/adpostm/menu/edit/admin?id='+menuId+'&checked='+element.checked,
		type: "get",
		error: function(jqHxr,status,errorThrown){
			alert("Sorry, an error occured.");
			window.location.reload();
		},
		success: function(data){
			alert("Admin Flag Update: " + data);
		
			window.location.reload();
		}
	});
	
}
/**
 * On checkbox changed update menu status
 * @param menuId
 * @param element
 * @returns
 */
function UpdateMenuStatus(menuId, element){
	var checked = element.checked;
	
	$.ajax({
		url: '/adpostm/menu/edit/status?id='+menuId+'&checked='+element.checked,
		type: "get",
		error: function(jqHxr,status,errorThrown){
			alert("Sorry, an error occured.");
			window.location.reload();
		},
		success: function(data){
			alert("Status Update: " + data);
			window.location.reload();
		}
	});
	
}
/**
 * 
 * @param advertId
 * @returns
 */
function UpdateAdStatus(advertId, element){
	alert("checked is: " + element.checked);
	$.ajax({
		url: '/adpostm/advert/edit/status?id='+advertId+'&checked='+element.checked,
		type: 'get',
		dataType: 'json',
		error: function(jqHxr,status,errorThrown){
			alert("Sorry, an error occured.");
		},
		success: function(data){
			alert("Response from server: " + data);
			window.location.reload();
		}
	})
}
/**
 * 
 * @returns
 */
function SubmitAdvert(){
	var formData = $("#add-advert-form").serializeArray();
	var url = $("#add-advert-form").attr("action");
	
	$.ajax({
		url: url,
		type: "post",
		data: formData,
		error: function(jqXhr, textStatus, errorThrown){
			alert("Failed to submit advert.")
		},
		success: function(data){
			if(data == "success"){
				alert("Advert successfully submitted!");
				window.location = '/adpostm/advert/search?search=&s-category=-1';
			}
			else{alert("Failed to submit advert.")}
		}
	});
}
function SubmitEditAdvert(){
	var formData = $("#edit-advert-form").serializeArray();
	var url = $("#edit-advert-form").attr("action");
	
	$.ajax({
		url: url,
		type: "post",
		data: formData,
		error: function(jqXhr, textStatus, errorThrown){
			alert("Failed to submit advert.")
		},
		success: function(data){
			if(data == "success"){
				alert("Advert successfully submitted!");
				window.location.reload();
			}
			else{alert("Failed to submit advert.")}
		}
	});
}
function SubmitUpdateMenu(){
	var url = $("#edit-menu-form").attr("action"); 
	var formData = $("#edit-menu-form").serializeArray();
	$.ajax({
		type: "post",
		url: url,
		data: formData,
		async: false,
		error: function(jqXhr, textStatus, errorThrown){
			alert("Failed to submit menu");
		},
		success: function(data){
			alert("Menu Update: " + data);
		}
	});
}
function SubmitAddMenu(){
	var url = $("#add-menu-form").attr("action"); 
	var formData = $("#add-menu-form").serializeArray();
	$.ajax({
		type: "get",
		url: url,
		data: formData
	})
	.done(function(data,textStatus, jqXHR){
		alert("Server Response:  " + data);
		window.location.reload();
	})
	.fail(function(jqXHR, textStatus, errorThrown){
		console.log(arguments);
		alert("Failed to submit menu - " + errorThrown);
	});
	window.location.reload;
}

/**
 * Submit edit contact no
 * @param callback
 * @returns
 */
function SubmitEditContact(callback){
	var url = $("#edit-contact-form").attr("action");
	var formData = $("#edit-contact-form").serializeArray();
	$.ajax({
		url: url,
		type: "get",
		data: formData,
		dataType: "json",
		error: function(jqHxr, textStatus, errorThrown){
			alert("An error occured while submitting contact.");
		},
		success: function(data){
			callback(data);
		}
	});
}
/**
 * Submit update of address
 * @param callback
 * @returns
 */
function SubmitEditAddress(callback){
	var url = $("#edit-address-form").attr("action");
	var formData = $("#edit-address-form").serializeArray();
	$.ajax({
		url: url,
		type: "post",
		data: formData,
		dataType: "json",
		error: function(jqHxr, textStatus, errorThrown){
			alert("An error occured while submitting address.");
		},
		success: function(data){
			callback(data);
		}
	});
}
//=============================================VALIDATORS===============================
function ValidateAddMenu(){
	var validator = $("#add-menu-form").validate({
		errorClass: "form-control-danger",
		rules:{
			addMenuName:{
				required: true,
				remote:{
					async: false,
					type: "get",
					url: "/adpostm/menuNameValid",
					data:{
						'menuName': function(){ return $("#addMenuName").val();},
						'parentId': function(){return $("#addParentId").val();}
					}
				}//element is valid return true otherwise return false
			}
		},
		messages:{
			addMenuName:{
				required: "Menu name is required",
				remote: "That menu already exists!"
			}
		}
	});
	if(validator.form()){
		SubmitAddMenu();
		$("#menu-add-modal").modal("toggle");
		
	}
}
function ValidateUpdateMenu(){
	var validator = $("#edit-menu-form").validate({
		errorClass: "form-control-danger",
		rules:{
			menuName:{
				required: true
			}
		},
		messages:{
			menuName:{
				required: "Menu name is required",
			}
		}
		
	});
	if(validator.form()){
		SubmitUpdateMenu();
		$("#menu-edit-modal").modal("toggle");
		window.location.reload();
	}
}
/**
 * Login Validation
 * @returns
 */
function submitLogin(){
	var validator = $("#form-login").validate({
		errorClass: "form-control-danger",
		rules:{
			username:{
				required: true,
				email: true
			},
			password:{
				required: true
			}
		},
		messages:{
			username:{
				required: "Username is required",
				email: "Invalid email address"
			},
			password:{
				required: "Password is required"
			}
		},
		errorPlacement: function(error, element){
			error.insertAfter(element);
		}
	});
	if(validator.form()){
		$("#login-form").submit();
	}
}
/**
 * Registration Validation
 */
function SubmitRegistration(){
	var validator = $("#form-register").validate({
		errorClass: "form-control-danger",
		rules:{
			fname:{
				required: true
			},
			lname:{
				required: true
			},
			email:{
				required: true,
				email: true,
				remote:{
					type: "post",
					url: "/adpostm/usernameValid",
					data: $("#email").val(),
					dataType: "json"
				}//element is valid return true otherwise return false
			},
			password:{
				required: true,
			},
			passRepeat:{
				required: true,
				equalTo: "#password"
			}
		},
		messages:{
			fname:{
				required: "First name is required"
			},
			lname:{
				required: "Last name is required"
			},
			email:{
				required: "Email name is required",
				emailAddress: "Please enter valid email address",
				remote: "That email is already registered!"
			},
			password:{
				required: "Password is required",
			},
			passRepeat:{
				required: "Please re-type password",
				equalTo: "Passwords must match"
			}
		}
	});
	if(validator.form()){
		//$("#form-register").submit();
		alert("All Good!");
	}
}
function ValidateSubmitAdvert(){
	var validator = $("#add-advert-form").validate({
		errorClass: "form-control-danger",
		rules:{
			menuId:{
				required: true
			},
			subMenuId:{
				required: true
			},
			subject:{
				required: true
			},
			body:{
				required: true
			},
			contactNo:{
				required: true
			},
			contactEmail:{
				required: false,
				email: true
			}
		},
		errorPlacement: function() {
	        return false;//prevent display of error message
	    }
		
	});
	if(validator.form()){
		SubmitAdvert();
	}
}
function ValidateEditAdvert(){
	var validator = $("#edit-advert-form").validate({
		errorClass: "form-control-danger",
		rules:{
			menuId:{
				required: true
			},
			subMenuId:{
				required: true
			},
			subject:{
				required: true
			},
			body:{
				required: true
			},
			contactNo:{
				required: true
			},
			contactEmail:{
				required: false,
				email: true
			}
		},
		messages:{

			contactNo:{
				required: "required"
			},

		},
		errorPlacement: function() {
	        //return false;//prevent display of error message
	    }
		
	});
	if(validator.form()){
		SubmitEditAdvert();
	}
}
//==================================================================
function SaveActiveAcc(active){
	var dataStore = window.sessionStorage;
	try{
		dataStore.setItem("activeAccId", active);
	}catch(e){Alert("failed to store index: "+active);}
}
function OpenActiveAcc(){
	var dataStore = window.sessionStorage;
	var oldIndex = dataStore.getItem("activeAccId");
	elementId = "#"+oldIndex;
	var roleSelector = "[href='"+elementId+"']";
	$("[role=tabpanel]").removeClass("show");
	$("[role=tab] h5 a").attr("aria-expanded","false");
	
	$(roleSelector).attr("aria-expanded","true");
	$(elementId).addClass("show");
}
window.onload = function() {
	 //OpenActiveAcc();
	
}
function FillMenuSelect(element, menuList, title){
	if(menuList.length > 0){
		$(element).empty();
		$(element).append("<option value='-1' selected>" + "----All----" + "</option>");
		for(var i=0; i< menuList.length; i++){
			$(element).append("<option value="+menuList[i].menuId+">" +
					menuList[i].menuName+"</option>");
		}
	}
	else{
		$(element).empty();
		$(element).append("<option value='-1' disabled selected>" + title + "</option>");
	}
}
/**
 * 
 */
$(document).ready(
	function(){

		if($("#search-bar").is(":visible")){
			//get category menu
			GetMenuList("/adpostm/menus?type=home", function(menuList){
				FillMenuSelect("#s-category", menuList, "select category");
			});
		}
		if($("#add-advert-form").is(":visible")){
			//get category menu
			GetMenuList("/adpostm/menus?type=home", function(menuList){
				FillMenuSelect("#menuId", menuList, "category");
			});
		}
		if($("#pg-menus").is(":visible")){
			
		}
		

	$("#menuId").on("change", function(e){
		var selectedId = $("#menuId").val();
		GetSubMenu("/adpostm/menus/submenus?parentId="+selectedId, function(menuList){
			FillMenuSelect("#subMenuId", menuList, "Select Category");
		});
	});
//=============================dialogs=============================================		
		/**
		 * OnClick for links to details and edits
		 */
		$(document).on("click", ".dtl-link", function(e){
			e.preventDefault();
			var url = $(this).attr("href");
			GetMenuDetail(url, function(menu){

				$("#spanName").text(menu.menuName);
				$("#spanDesc").text(menu.menuDesc);
				$("#spanUrl").text(menu.url);
				document.getElementById("menuId").value = menu.menuId;
				document.getElementById("menuType").value = menu.menuType;
				document.getElementById("menuName").value = menu.menuName;
				document.getElementById("menuDesc").value = menu.menuDesc;
				document.getElementById("url").value = menu.url;
				document.getElementById("icon").value = menu.icon;
				document.getElementById("menuStatus").value = menu.menuStatus;
				document.getElementById("adminMenu").value = menu.adminMenu;
				
				if(menu.adminMenu == 1){
					document.getElementById("adminMenuCheck").checked = true;
				} 
				if(menu.menuStatus == 1){
					document.getElementById("menuStatusCheck").checked = true;
				}
				
				if(menu.icon !== null){
					$(".modal-header img").attr("src", "/adpostm/resources" +
							"/images/menu/" + menu.icon);
				}
			});
			//open bootstrap modal
			$("#menu-edit-modal").modal();
		});
		/**
		 * Modal for inserting a new menu item
		 */
		$(document).on("click", "#newMenuBtn", function(){
			//parentId select
			GetMenuList("/adpostm/menus?type=home&type=sidebar", function(menuList){
				if(menuList.length > 0){
					$("#addParentId").empty();
					$("#addParentId").append("<option value=0> </option>");
					for(var i=0; i< menuList.length; i++){
						$("#addParentId").append("<option value="+menuList[i].menuId+">" +
								menuList[i].menuName+"</option>");
					}
				}
			});
			GetMenuType("/adpostm/menutype", function(menuType){
				if(menuType.length > 0){
					$("#addMenuType").empty();
					$("#addMenuType").append("<option value=0> </option>");
					for(var i=0; i< menuType.length; i++){
						$("#addMenuType").append("<option value="+menuType[i]+">" +
								menuType[i]+"</option>");
					}
				}
			});
			$("#menu-add-modal").modal();
			
		});
		$(".modal").on("hidden.bs.modal", function(){
			$(".modal-body input").val("");
		});
		/**
		 * Modal for editing user's address
		 */
		$(document).on("click", "#edit-address", function(e){
			e.preventDefault();
			var url = $(this).attr("href");
			GetUserDetail(url, function(user){
				document.getElementById("postAddress1").value = user.userDetail.address.postAddress1;
				document.getElementById("postAddress2").value = user.userDetail.address.postAddress2;
				document.getElementById("surbub").value = user.userDetail.address.surbub;
				document.getElementById("state").value = user.userDetail.address.state;
				document.getElementById("postCode").value = user.userDetail.address.postCode;
				document.getElementById("mobileNo").value = user.userDetail.mobileNo;
				document.getElementById("userId").value = user.appUserId;
				document.getElementById("userDetailId").value = user.userDetail.userDetailId;
			});
			//open bootstrap modal
			$("#address-edit-modal").modal();
		});
		$(document).on("click", "#edit-contact", function(e){
			e.preventDefault();
			var url = $(this).attr("href");
			GetUserDetail(url, function(user){
				document.getElementById("mobileNo").value = user.userDetail.mobileNo;
				document.getElementById("co-userId").value = user.appUserId;
			});
			//open bootstrap modal
			$("#contact-edit-modal").modal();
		});
		$(document).on("click", "#newMenuBtn", function(){
			//parentId select
			GetMenuList("/adpostm/menus?type=home", function(menuList){
				if(menuList.length > 0){
					$("#parentId").empty();
					$("#parentId").append("<option value=0> </option>");
					for(var i=0; i< menuList.length; i++){
						$("#parentId").append("<option value="+menuList[i].menuId+">" +
								menuList[i].menuName+"</option>");
					}
				}
			});
			$("#menu-add-modal").modal();
			
		});
		$(document).on("click","#sidebar-accordion ul li a",function(){
			
		});
		/**
		 * OnClick for links to details and edits
		 */
		$(document).on("click", ".ad-dtl-link", function(e){
			e.preventDefault();
			
			var url = $(this).attr("href");
			GetAdvertDetail(url, function(advert){
				var submitDate = new Date(advert.submittedDate);

				$("#spanHeading").text(advert.advertDetail.title);
				$("#spanTitle").text(advert.advertDetail.title);
				$("#spanUser").text(advert.appUser.userDetail.firstName +
						" "+advert.appUser.userDetail.lastName);
				$("#spanSubmitDate").text(submitDate.getDate()+"/"
						+(submitDate.getMonth() + 1)+"/"+submitDate.getFullYear());
				$("#spanBody").text(advert.advertDetail.body);
				$("#spanContactEmail").text(advert.advertDetail.contactEmail+"\n");
				$("#spanContactPhone").text(advert.advertDetail.contactPhone);
		
				//Carousel 
				var html = "<ol class='carousel-indicators'>";
				for(i=0; i< advert.advertDetail.groupCount; i++){
						if(i == 0){
							html += "<li data-target='#bs-carousel' data-slide-to='"+i+"' class='active'></li>";
						}
						else{
							html += "<li data-target='#bs-carousel' data-slide-to='"+i+"' class=''></li>";

						}
				}
				html += "</ol>";
				html += "<div class='carousel-inner'>";
				for(i=0; i< advert.advertDetail.groupCount; i++){
					if(i == 0){
						html += "<div class='carousel-item active'>";
					}
					else{
						html += "<div class='carousel-item'>";
					}
						html += "<img class='d-block w-100 h-100' src='"+advert.advertDetail.adPicture[i].cdnUrl+"' alt='first slide' ></div>";
				}
				html += "</div>";				
				html += "<a class='carousel-control-prev' href='#bs-carousel' role='button'";
				html += "data-slide='prev'> <span class='carousel-control-prev-icon'";
				html += "aria-hidden='true'></span> <span class='sr-only'>previous</span>";
				html += "</a> <a class='carousel-control-next' href='#bs-carousel' role='button'";
				html += "data-slide='next'> <span class='carousel-control-next-icon'";
				html += "aria-hidden='true'></span> <span class='sr-only'>Next</span></a>";
		
				$("#bs-carousel").html("");
				$("#bs-carousel").append(html);
			});
			//open bootstrap modal
			$("#advert-detail-modal").modal();
		});
		/*$("#sidebar-accordion").on("show.bs.collapse", function(e){
			var index = e.target.id;
			SaveActiveAcc(index);
		});*/
		
		//Navigation highlight
		var url = window.location.href;
		url = url.split("?",1);
		url = decodeURIComponent(url);
		$(".nav-link").each(function(){
			if(this.href == url){
				$(this).addClass("active");
				//add active to li item
				$(this).closest('li').addClass("active");
			}
		});
		//sidebar navigation highlight
		$(".acc-nav-link").each(function(){
			if(this.href == url){
				$(this).addClass("active");
				//find the li item
				$(this).closest('li').addClass("active");
				//find the header
				var elementId = $(this).closest('[role=tabpanel]').attr('id');
				elementId = "#"+elementId;
				var headerSelector = "[href='"+elementId+"']";
				$("[role=tabpanel]").removeClass("show");
				$("[role=tab] h5 a").attr("aria-expanded","false");
				
				$(headerSelector).attr("aria-expanded","true");
				$(elementId).addClass("show");
				
			}
		});
		/**
		 * Hide collapsible menu when screen clicked
		 */
		$(document).on("click", function(){
			$("#navbar-menu .collapse").collapse("hide");
		});
		$(document).on("click", "#submitAddressEdit", function(e){
			e.preventDefault();
			SubmitEditAddress(function(data){
					alert("Address update " + data);
			});
			$("#address-edit-modal").modal("toggle");
			window.location.reload();
		});
		/**
		 * Edit Contact Number Modal
		 */
		$(document).on("click", "#submitContactEdit", function(e){
			e.preventDefault();
			SubmitEditContact(function(data){
					alert("Contact No update " + data);
			});
			$("#contact-edit-modal").modal("toggle");
			window.location.reload();
		});
		$(document).on("click", "#close-ad-edit", function(){
			window.location = "/adpostm/admin/posts";
		});
		//===================================UPLOADCARE WIDGET ONCHANGE=================================
		if($("#add-advert-form").is(":visible")){
			var multiWidget = uploadcare.MultipleWidget('#uploadcareWidget');
			
			multiWidget.onUploadComplete(function(group){
				if(group){
					group;
					
					$("#add-advert-form").append(
							"<input type='hidden' name='groupUuid' " +
							"value='" + group.uuid + "' path='groupUuid'/>"
					);
					$("#add-advert-form").append(
							"<input type='hidden' name='groupCdnUrl' " +
							"value='" + group.cdnUrl + "' path='groupCdnUrl'/>"
					);
					$("#add-advert-form").append(
							"<input type='hidden' name='groupCount' " +
							"value='" + group.count + "' path='groupCount'/>"
					);
					$("#add-advert-form").append(
							"<input type='hidden' name='groupSize' " +
							"value='" + group.size + "' path='groupSize'/>"
					);
				}
			});
			multiWidget.onChange(function(group){
				if(group){
					group;
					group.files();
					
					$.when.apply(null, group.files()).then(
						function(){
							var filesInfo = arguments;
							for(i=0; i< filesInfo.length; i++){
								$("#add-advert-form").append(
										"<input type='hidden' name='imageUuid[" + i + "]'" +
										"value='"+filesInfo[i].uuid+"' path='imageUuid'/>"
								);
								$("#add-advert-form").append(
										"<input type='hidden' name='imageCdnUrl[" + i + "]'" +
										"value='"+filesInfo[i].cdnUrl+"' path='imageCdnUrl'/>"
								);
								$("#add-advert-form").append(
										"<input type='hidden' name='imageSize[" + i + "]'" +
										"value='"+filesInfo[i].size+"' path='imageSize'/>"
								);
								$("#add-advert-form").append(
										"<input type='hidden' name='imageName[" + i + "]'" +
										"value='"+filesInfo[i].name+"' path='imageName'/>"
								);
							}
						}
					);	
				}
			});
		}
		if($("#edit-advert-form").is(":visible")){
			var element = document.getElementById("groupUuid");
			var multiWidget = uploadcare.MultipleWidget('#editAdWidget');
			multiWidget.value(element.value);
			
			var groupUuid = document.getElementById("groupUuid");
			
			multiWidget.onUploadComplete(function(group){
				if(group){
					group;
					
					groupUuid.value = group.uuid;
					document.getElementById("groupCdnUrl").value = group.cdnUrl;
					document.getElementById("groupCount").value = group.count;
					document.getElementById("groupSize").value = group.size;

				}
			});
			multiWidget.onChange(function(group){
				
				if(group){
					group;
					group.files();
		
					$.when.apply(null, group.files()).then(
						function(){
							var filesInfo = arguments;
							var uuid = new Array(filesInfo.length);
							var cdnUrl = new Array(filesInfo.length);
							var name = new Array(filesInfo.length);
							var size = new Array(filesInfo.length);
							
							for(i=0; i<filesInfo.length; i++){
								uuid[i] = filesInfo[i].uuid
								cdnUrl[i] = filesInfo[i].cdnUrl;
								name[i] = filesInfo[i].name;
								size[i] = filesInfo[i].size;
							}
							$('[name=imageUuid]').val(uuid);
							$('[name=imageCdnUrl]').val(cdnUrl);
							$('[name=imageName]').val(name);
							$('[name=imageSize]').val(size);
							
						}
					);	
				}
			});
		}
		//==========================================SELECT====================================
		$(".modal-body #addParentId").on("change", function(){
			if(this.value == 0){
				$("#addMenuType").enabled = true;
			}
			else{$("#addMenuType").enabled = false;}
		});
		//==========================================CHECKBOXES====================================
		$("#menuStatusCheck").on("change", function(){
			if(this.checked){
				document.getElementById("menuStatus").value = 1;
			}
			else{document.getElementById("menuStatus").value = 0;}
		});
		$("#adminMenuCheck").on("change", function(){
			if(this.checked){
				document.getElementById("adminMenu").value = 1;
			}
			else{document.getElementById("adminMenu").value = 0;}
		});
	}
);