package com.eainde.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("appl_role")
public class Role {
  @Id private long roleId;
  private String roleName;

  public Role() {}

  public Role(long roleId, String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
