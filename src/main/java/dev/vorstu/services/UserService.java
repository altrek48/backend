package dev.vorstu.services;

import dev.vorstu.dto.UserShow;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.mappers.UserMapper;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserShow saveUser(UserShow userShow) {
        UserEntity userEntity = userMapper.toUserEntity(userShow);
        userRepository.save(userEntity);
        UserShow newUserShow = userMapper.toUserShow(userEntity);
        return  newUserShow;
    }

    public boolean checkAvailableUsername(String username) {
        return userRepository.checkAvailableUsername(username);
    }


}
