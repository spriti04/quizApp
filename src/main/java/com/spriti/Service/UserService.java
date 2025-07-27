package com.spriti.Service;

import com.spriti.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User saveUser(User user);

    public User getUser(String email);
}
