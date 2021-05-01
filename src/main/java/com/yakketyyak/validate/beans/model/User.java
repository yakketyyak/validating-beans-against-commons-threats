package com.yakketyyak.validate.beans.model;

import com.yakketyyak.validate.beans.validators.NoSpELInjection;
import com.yakketyyak.validate.beans.validators.NoXSSContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@NoXSSContent
	@NoSpELInjection
	private String username;

	@NoXSSContent
	@NoSpELInjection
	private String firstName;

	@NoXSSContent
	@NoSpELInjection
	private String lastName;
}
