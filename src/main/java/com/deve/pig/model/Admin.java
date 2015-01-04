package com.deve.pig.model;

import java.util.Map;

public class Admin {
    private Long id;

    private String userName;

    private String pwd;

    private String email;

    private Byte type;

    private Long roleId;
    
    // 临时属性（拥有的权限集合）
 	private Map<String, Priv> allPrivs;

 	// 临时属性（用户类型字符串）
 	private String typeStr;

 	// 临时属性（角色字符串）
 	private String roleStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

	public Map<String, Priv> getAllPrivs() {
		return allPrivs;
	}

	public void setAllPrivs(Map<String, Priv> allPrivs) {
		this.allPrivs = allPrivs;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
}