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

function SubmitUpdateMenu(url, data){
	$.ajax({
		type: "post",
		url: url,
		data: data,
		error: function(jqxHr,errorText, errorThrown){
			alert("Failed to submit menu");
		},
		success: function(data){
			alert("Menu created successfully");
		}
	});
}
function SubmitAddMenu(url, formData){
	$.ajax({
		type: "get",
		url: url,
		data: formData,
		error: function(jqxHr,errorText, errorThrown){
			alert("Failed to submit menu");
		},
		success: function(data){
			alert("Menu created successfully");
		}
	});
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
			menuName:{
				required: true
			}
		},
		messages:{
			menuName:{
				required: "Menu title is required"
			}
		}
	});
	if(validator.form()){
		SubmitAddMenu($("#add-menu-form").attr("action"),
				$("#add-menu-form").serializeArray());
		window.location.reload();
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
				required: "Menu name is required"
			}
		}
		
	});
	if(validator.form()){
		SubmitUpdateMenu($("#edit-menu-form").attr("action"), 
				$("#edit-menu-form").serializeArray());
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
		//=============================select=============================================		

		if($("#search-bar").is(":visible")){
			//get category menu
			GetMenuList("/adpostm/menus?type=home", function(menuList){
				FillMenuSelect("#s-category", menuList, "category");
			});
		}
		if($("#add-advert-form").is(":visible")){
			//get category menu
			GetMenuList("/adpostm/menus?type=home", function(menuList){
				FillMenuSelect("#menuId", menuList, "category");
			});
		}
	$("#menuId").on("change", function(e){
		var selectedId = $("#menuId").val();
		GetSubMenu("/adpostm/menus/submenus?parentId="+selectedId, function(menuList){
			FillMenuSelect("#subMenuId", menuList, "sub-category");
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
				$(".modal-header img").attr("src", "/adpostm/resources" +
						"/images/menu/" + menu.icon);
			});
			//open bootstrap modal
			$("#menu-edit-modal").modal();
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
		
				//Images
				$("#lg-img").attr("src", "");
				$("#sm-img1").attr("src", "");
				$("#sm-img2").attr("src", "");
				switch(advert.advertDetail.groupCount){
				case 0:
					break;
				case 1:
					$("#lg-img").attr("src", advert.advertDetail.adPicture[0].cdnUrl);
					break;
				case 2:
					$("#lg-img").attr("src", advert.advertDetail.adPicture[0].cdnUrl);
					$("#sm-img1").attr("src", advert.advertDetail.adPicture[1].cdnUrl);
					break;
				case 3:
					$("#lg-img").attr("src", advert.advertDetail.adPicture[0].cdnUrl);
					$("#sm-img1").attr("src", advert.advertDetail.adPicture[1].cdnUrl);
					$("#sm-img2").attr("src", advert.advertDetail.adPicture[2].cdnUrl);
					break;
				}
				
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

		
//=====================================================================================================
	}
);