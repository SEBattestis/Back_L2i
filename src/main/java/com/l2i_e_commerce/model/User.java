package com.l2i_e_commerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NonNull
	String role;
	@NonNull
	String firstname;
	@NonNull
	String lastname;
	@NonNull	
	String email;
	@NonNull
	String password;
	@NonNull
    @JsonIgnoreProperties("users")
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	List<Address> addresses;
	
	

}
