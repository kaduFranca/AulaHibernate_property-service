package services;

import lombok.Data;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import javax.transaction.Transactional;

//Controller aciona o Service e o Service aciona o Repository

@Service
public class PropertyService { //Controller acionar o service e o service aciona o Repository

    final UserRepository userRepository;

    public PropertyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
