package com.subilete.demojwt.Service;

import com.subilete.demojwt.Auth.AuthResponse;
import com.subilete.demojwt.User.RegisterRequest;

public interface UserService {
	
	AuthResponse register(RegisterRequest request);

}
