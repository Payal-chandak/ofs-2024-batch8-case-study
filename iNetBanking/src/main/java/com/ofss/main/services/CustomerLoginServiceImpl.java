package com.ofss.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.domain.Login;
import com.ofss.main.repositories.CustomerLoginRepository;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {
	
	@Autowired
	private CustomerLoginRepository customerLoginRepository;

	@Override
	public Login createNewLogin(Login login) {
		return customerLoginRepository.save(login);
	}

	@Override
	public Login loginExisting(int loginId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login getLoginCreds(int login_id) {
		Optional <Login> optional= customerLoginRepository.findById(login_id);
        if (optional.isPresent()) {
            return optional.get();
        }
		return null;
	
	}


	    @Override
	    public LoginDetails login(String username, String password) {
	        LoginDetails loginDetails = customerLoginRepository.findByUsername(username);
	        if (loginDetails!=null) {
	            LoginDetails loginDetails2 = customerLoginRepository.findByUsernameAndPassword(username, password);
	            if(loginDetails2!=null && loginDetails2.isAllowed()==true){
	                loginDetails2.setLoggedIn(true);
	                loginDetails2.setLoginAttempt(0);
	                customerLoginRepository.save(loginDetails2);
	            }else{
	                loginDetails.setLoginAttempt(loginDetails.getLoginAttempt()+1);
	                if(loginDetails.getLoginAttempt()>3){
	                    loginDetails.setAllowed(false);
	                }
	                loginDetails.setLoggedIn(false);
	                customerLoginRepository.save(loginDetails);
	            }
	        }
	        else{
	            return null; //Add exceptions
	        }
	        return loginDetails;
	    }

}
