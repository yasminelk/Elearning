/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.Codes;
import Utils.Generator;
import dao.UserDao;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class UserService {
    
    private UserDao dao;
    
    public UserService(){
        this.dao = new UserDao(User.class);
    }
    
    public int insertUser(User user){
        if(getDao().getUserByUsername(user.getUsername()) == null){
            if(getDao().getUserByEmail(user.getEmail()) == null){
                user.setPassword(Generator.hashSHA256(user.getPassword()));
                if(getDao().save(user)){
                    return Codes.SUCCESS;
                }
                return Codes.INTERNAL_ERROR;
            }
            return Codes.EMAIL_EXIST;
        }
        return Codes.USERNAME_EXIST;
    }
    
    public User logUser(String username, String password){
        User user = getDao().getUserByUsername(username);
        if( user != null ){
            if(!user.getPassword().equals(Generator.hashSHA256(password))){
                user = null;
            }
        }
        return user;
    }

    /**
     * @return the dao
     */
    public UserDao getDao() {
        return dao;
    }
    
}
