package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Users;
import pidev.tn.aurora.repository.Users.UsersRepository;

import java.util.List;

@Service
@Slf4j
public class SeviceUser implements IServiceUsers{

    @Autowired
    public UsersRepository usersRepository;
    @Override
    public Users addOrUpdateUser(Users users) {

        return usersRepository.save(users);
    }

    public List<Users>  GetAllUser(){
       List<Users> listUsers= usersRepository.findAll();
       return  listUsers;
    }

    @Override
    public Users GetUser(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        usersRepository.deleteById(id);
    }

}
