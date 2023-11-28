package com.example.organizationservice.service.impl;

import org.springframework.stereotype.Service;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.mapper.OrganizationMapper;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{

	private OrganizationRepository organizationRepository;
	@Override
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
		//convert organizationDto into organozation Entity
		Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
		
		Organization savedOrganization = organizationRepository.save(organization);
		
		//convert organization into organizationDto
		OrganizationDto savedOrganizationDto = OrganizationMapper.mapToOrganizationDto(savedOrganization);
		return savedOrganizationDto;
	}
	@Override
	public OrganizationDto getOrganizationByCode(String organizationCode) {
		Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
		
		OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(organization);
		return organizationDto;
	}
	
	
	

}
