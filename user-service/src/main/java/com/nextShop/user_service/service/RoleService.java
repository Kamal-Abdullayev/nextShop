package com.nextShop.user_service.service;

import com.nextShop.user_service.exception.BaseException;
import com.nextShop.user_service.model.Role;
import com.nextShop.user_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final static String OWNER = "OWNER";
    private final RoleRepository roleRepository;

    public Role getDefaultRole() {
        return roleRepository.findByName(OWNER).orElseThrow(BaseException::unexpected);
    }
}
