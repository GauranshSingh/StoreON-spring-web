// this si the file that has the power to look int o the table and check if teh user credentials match or not

package com.gauransh.StoreON.repository;
 
import com.gauransh.StoreON.entity.Login_User;
import org.springframework.data.jpa.repository.JpaRepository;  // to get teh jpa repository 
import org.springframework.stereotype.Repository;				//

@Repository
public interface Login_UserRepository extends JpaRepository<Login_User, String> {		// function for SQL JPa is predifined 
    Login_User findByEmail(String email);            // for this-SELECT email,password FROM user_table WHERE email = gauranshsinghse@gmailcom;
    
}
//###interface to be user instead of class bcoz 