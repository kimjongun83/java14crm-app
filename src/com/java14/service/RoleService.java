package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository;
	
	public RoleService() {
		roleRepository = new RoleRepository();
	}
	
	
	public List<RoleDto> findAll(){
		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		List<Role> roles = roleRepository.findAll();
		for (Role role : roles) {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(role.getId());
			roleDto.setName(role.getName());
			roleDto.setDescription(role.getDescription());
			
			roleDtos.add(roleDto);
		}
		return roleDtos;
	}
	
	public boolean addRoleDto(RoleDto roleDto) {
		if(!checkExist(roleDto)) {
			return false;
		}
		Role role = new Role();
		convertToRole(role, roleDto);
		return roleRepository.addRole(role);

	}
	public RoleDto getRoleDtoById(int id) {
		RoleDto roleDto = null;
		Role role = roleRepository.getRoleById(id);
		if(role != null) {
			roleDto = new RoleDto();
			roleDto.setId(role.getId());
			roleDto.setName(role.getName());
			roleDto.setDescription(role.getDescription());
		}
		return roleDto;
	}
	public boolean updateRoleDTo(int id, RoleDto roleDto) {
		Role role = roleRepository.getRoleById(id);
		if(role != null) {
			if(!checkExist(roleDto)) {
				return false;
			}
			convertToRole(role, roleDto);
			return roleRepository.updateRole(id, role);
		}
		return false;
	}
	
	private void convertToRole(Role role, RoleDto roleDto) {
		role.setName(roleDto.getName());
		role.setDescription(roleDto.getDescription());
	}
	
	private boolean checkExist(RoleDto roleDto) {
		if(roleDto.getName().equalsIgnoreCase("")) {
			return false;
		}
		List<Role> roles = roleRepository.findAll();
		for (Role roleCheck : roles) {
			if((roleCheck.getName().equalsIgnoreCase(roleDto.getName())) && (roleCheck.getId() != roleDto.getId())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean deleteRoleDto(int id) {
		return roleRepository.deleteRole(id);
	}
}
