// this si teh main class of the project and it has all the configure component scanf and controller repository 
// to it to statr teh funciton like main{} in C language and rest are teh functions ceated

package com.gauransh.StoreON;     //tells where class is located

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;     //these are teh libraries user to import the @SpringBootApplication

//import com.gauransh.loginapp.controller.LoginController;
//import com.gauransh.loginapp.entity.User;
//import com.gauransh.loginapp.repository.UserRepository;

@SpringBootApplication    //it willl combines configuration, component, scan, controller, repository
public class StoreON {

	public static void main(String[] args) {    //####runs the code since it is the main class thus all the other a files are linked to it
		SpringApplication.run(StoreON.class, args);   //it will just pass string, to 
		// the Loginappapplication.class
	}
}

//run is an abstract predefined function of springbootapplication 