function submitLogin(){
	var validator = $("#form-login").validate({
		errorClass: "formErrMsg",
		wrapper: "div",
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
		$(window).on("resize", function(){
			if($(window).width() < 576){
			}
		});
	}
);