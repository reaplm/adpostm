package com.adpostm.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class Role {
	@Column(name="role_name")
 private  String roleName;
 
 public String getRoleName(){
	 return this.roleName;
 }
 public void setRoleName(String roleName){
	 this.roleName = roleName;
 }
}
