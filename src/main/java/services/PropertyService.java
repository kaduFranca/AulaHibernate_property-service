package services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

//Controller aciona o Service e o Service aciona o Repository

@Service
public class PropertyService {

    final UserRepository userRepository;

    public PropertyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
