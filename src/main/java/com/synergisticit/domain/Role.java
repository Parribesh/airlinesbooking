package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long roleId;
	
	@NotNull
	RoleTypes roleName;
	
	@ManyToMany(mappedBy="userRoles")
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	

}
