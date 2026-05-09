package be.iccbxl.pid.youforbel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Role;
import be.iccbxl.pid.youforbel.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rôle introuvable id=" + id));
    }
}