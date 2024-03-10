package com.gxlpes.restaurant.service;

import com.gxlpes.restaurant.model.TypeMenu;
import com.gxlpes.restaurant.model.enums.StatusEnum;
import com.gxlpes.restaurant.repository.TypeMenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class TypeMenuService {

    private final TypeMenuRepository typeMenuRepository;

    public TypeMenuService(TypeMenuRepository typeMenuRepository) {
        this.typeMenuRepository = typeMenuRepository;
    }

    @Transactional
    public TypeMenu saveOrUpdate(TypeMenu typeMenu) {
        TypeMenu existingType = typeMenuRepository.findByNameOrderByAsc(typeMenu.getName());

        if (existingType != null && !existingType.getId().equals(typeMenu.getId())) {
            throw new RuntimeException("A type with the same name already exists.");
        }

        if (typeMenu.getName().length() < 3) {
            throw new RuntimeException("The name of the type must have at least 3 characters.");
        }

        if (Arrays.stream(StatusEnum.values()).noneMatch(status -> status.equals(typeMenu.getStatus()))) {
            throw new RuntimeException("Status can only be 'A' (Active) or 'I' (Inactive).");
        }

        return typeMenuRepository.save(typeMenu);
    }

    public Page<TypeMenu> listByName(String name, Pageable pageable) {
        if (name.length() < 3) {
            throw new RuntimeException("The name of the type must have at least 3 characters.");
        }

        Page<TypeMenu> typeMenuPage = typeMenuRepository.findByNameContainingIgnoreCaseOrderByAsc(name, pageable);
        if (typeMenuPage.isEmpty()) {
            throw new RuntimeException("No menus found with the provided name.");
        }

        return typeMenuPage;
    }

    public Page<TypeMenu> listAllMenus(Pageable pageable) {
        return typeMenuRepository.findAll(pageable);
    }

    public TypeMenu findById(String id) {
        return typeMenuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
    }

    @Transactional
    public TypeMenu updateStatus(String id, String status) {
        TypeMenu typeMenu = findById(id);

        if (!status.equals("A") && !status.equals("I")) {
            throw new RuntimeException("Status can only be 'A' (Active) or 'I' (Inactive).");
        }

        typeMenu.setStatus(StatusEnum.valueOf(status));
        return typeMenuRepository.save(typeMenu);
    }
}
