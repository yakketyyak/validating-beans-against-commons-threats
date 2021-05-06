package com.yakketyyak.validate.beans.model;

import com.yakketyyak.validate.beans.validators.NoLdapInjection;
import com.yakketyyak.validate.beans.validators.NoSpelInjection;
import com.yakketyyak.validate.beans.validators.NoXssContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@NoXssContent
	@NoSpelInjection
	@NoLdapInjection
	private String username;

	@NoXssContent
	@NoSpelInjection
	@NoLdapInjection
	private String firstName;

	@NoXssContent
	@NoSpelInjection
	@NoLdapInjection
	private String lastName;
}
