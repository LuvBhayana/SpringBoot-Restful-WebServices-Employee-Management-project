package com.example.organizationservice.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
		description = "Organization Dto Model Information"
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
	
	private Long id;
	@Schema(
			description = "Organization name"
			)
	private String organizationName;
	@Schema(
			description = "Organization Description"
			)
	private String organizationDescription;
	@Schema(
			description = "Organization Code"
			)
	private String organizationCode;
	@Schema(
			description = "Organization Created date"
			)
	private LocalDateTime createdDate;

}
