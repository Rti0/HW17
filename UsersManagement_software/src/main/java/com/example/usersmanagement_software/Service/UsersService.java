package com.example.usersmanagement_software.Service;

import com.example.usersmanagement_software.Model.Users;
import com.example.usersmanagement_software.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public void addUsers(Users users){
        usersRepository.save(users);
    }
    public boolean updateUsers(Integer id, Users users ) {
        Users oldUsers = usersRepository.getById(id);
        if (oldUsers == null) {
            return false;
        }

        oldUsers.setId(users.getId());
        oldUsers.setName(users.getName());
        oldUsers.setUsername(users.getUsername());
        oldUsers.setPassword(users.getPassword());
        oldUsers.setEmail(users.getEmail());
        oldUsers.setRole(users.getRole());
        oldUsers.setAge(users.getAge());


        usersRepository.save(oldUsers);
        return true;
    }

    public boolean deleteUsers(Integer id){
        Users oldUsers = usersRepository.getById(id);
        if (oldUsers==null){
            return true;
        }
        return false;
    }
}
