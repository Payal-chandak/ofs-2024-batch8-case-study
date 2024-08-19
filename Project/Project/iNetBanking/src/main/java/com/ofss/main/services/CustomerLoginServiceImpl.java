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
	    // Fetch the login entry using loginId
	    Login login = customerLoginRepository.findByloginId(loginId);

	    if (login != null) {
	        // Check login status
	        switch (login.getLoginStatus()) {
	            case "NEW":
	                // Activate the account and reset login attempts
	                login.setLoginStatus("ACTIVE");
	                login.setLoginAttempts(0);
	                customerLoginRepository.save(login);
	                return login; // Return login details with updated status

	            case "LOCKED":
	                // Handle locked status
	                // Return or handle appropriately, e.g., throw an exception or return a specific error message
	                return login; // Return login details or status indicating account is locked

	            case "ACTIVE":
	                // Handle login attempts for active status
	                if (login.getLoginAttempts() < 3) {
	                    if (login.getPassword().equals(password)) {
	                        // Successful login: reset attempts and return login details
	                        login.setLoginAttempts(0);
	                        customerLoginRepository.save(login);
	                        return login; // Return login details for a successful login
	                    } else {
	                        // Failed login attempt: increment attempts
	                        login.setLoginAttempts(login.getLoginAttempts() + 1);
	                        if (login.getLoginAttempts() >= 3) {
	                            // Lock the account if maximum attempts are reached
	                            login.setLoginStatus("LOCKED");
	                        }
	                        customerLoginRepository.save(login);
	                        return login; // Return login details with updated attempts/status
	                    }
	                } else {
	                    // If attempts exceed the limit, lock the account
	                    login.setLoginStatus("LOCKED");
	                    customerLoginRepository.save(login);
	                    return login; // Return login details indicating the account is locked
	                }

	            default:
	                // Handle unexpected statuses
	                // Optionally throw an exception or handle as per application requirements
	                return login; // Return login details with a status indicating unexpected behavior
	        }
	    } else {
	        // Handle case where login entry is not found
	        // Optionally throw an exception or handle as per application requirements
	        return null; // Return null or handle as needed
	    }
	}



	@Override
	public Login getLoginCreds(int login_id) {
		Optional <Login> optional= customerLoginRepository.findById(login_id);
        if (optional.isPresent()) {
            return optional.get();
        }
		return null;
	
	}
	
}
	

//
//	    @Override
//	    public LoginDetails login(String username, String password) {
//	        LoginDetails loginDetails = customerLoginRepository.findByUsername(username);
//	        if (loginDetails!=null) {
//	            LoginDetails loginDetails2 = customerLoginRepository.findByUsernameAndPassword(username, password);
//	            if(loginDetails2!=null && loginDetails2.isAllowed()==true){
//	                loginDetails2.setLoggedIn(true);
//	                loginDetails2.setLoginAttempt(0);
//	                customerLoginRepository.save(loginDetails2);
//	            }else{
//	                loginDetails.setLoginAttempt(loginDetails.getLoginAttempt()+1);
//	                if(loginDetails.getLoginAttempt()>3){
//	                    loginDetails.setAllowed(false);
//	                }
//	                loginDetails.setLoggedIn(false);
//	                customerLoginRepository.save(loginDetails);
//	            }
//	        }
//	        else{
//	            return null; //Add exceptions
//	        }
//	        return loginDetails;
//	    }
//
//}
