package com.juliomesquita.coreapi.domain.enums;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum PermissionsSystem {
    //Construçao do nome da permissão Tag relacionada_Grupo_DescriçãoResumida

    //Permissions Backoffice
    BACKOFFICE_HOMOLOGATION_APPROVED("Homologação", "Aprovar Entidade Social.");

    private final String group;
    private final String description;

    PermissionsSystem(String group, String description) {
        this.group = group;
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public String getDescription() {
        return description;
    }

    public static Set<PermissionsSystem> getPermissions(String name) {
        Set<PermissionsSystem> permissionList = new HashSet<>();

        boolean isBackoffice = name.equalsIgnoreCase("backoffice");
        if (isBackoffice) {
            Collections.addAll(permissionList, values());
        } else {
            for (PermissionsSystem key : values()) {
                String nameToLowerCase = key.name().toLowerCase();
                if (nameToLowerCase.contains(name)) {
                    permissionList.add(key);
                }
            }
        }
        return permissionList;
    }

}
