package com.cenmo.mogu.portal.service;

import com.cenmo.mogu.pojo.User;

public interface LoginService {
	User getUserByToken(String token);
}
