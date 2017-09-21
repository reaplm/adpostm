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
function SubmitUpdateMenu(url, data){
	$.ajax({
		type: "post",
		url: url,
		data: data,
		dataType: "json",
		error: function(jqxHr,errorText, errorThrown){
			alert("Failed to submit menu");
		},
		success: function(){
			alert("menu submitted successfully");
		}
	});
}
//=============================================VALIDATORS===============================
function ValidateUpdateMenu(){
	var validator = $("#edit-menu-form").validate({
		errorClass: "formErrorMsg",
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
		errorClass: "formErrMsg",
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
			passRetype:{
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
				required: "Password cannot be blank",
			},
			passRetype:{
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
/**
 * 
 */
$(document).ready(
	function(){
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
	}
);