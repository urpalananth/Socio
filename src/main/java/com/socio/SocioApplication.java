package com.socio;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.stereotype.Component;

import com.socio.beans.UserProfile;
import com.socio.repo.UserProfileRepository;
import com.socio.security.LdapUserRepo;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SessionAutoConfiguration.class})
public class SocioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocioApplication.class, args);
	}	
}


@Component
class SocioCommandLineRunner implements CommandLineRunner{

	private LdapUserRepo ldapRepo;
	
	private UserProfileRepository userRepo;

	@Autowired
	public SocioCommandLineRunner(LdapUserRepo ldapRepo) {
		this.ldapRepo = ldapRepo;
	}
	@Override
	public void run(String... args) throws Exception {
		//System.out.println("--> "+ldapRepo.getAllLdapUsers().size());
		//  http://localhost:8080/images/profile-default.png
		
		List<String> abouts = Arrays.asList("CEO, Atomist. Creator of Spring, Cofounder/CEO at SpringSource, Investor, Author",
"Life is a dream dont fear be happy always Life is compromise",
"You have found the world-famous XDA-Developers Twitter! Catch up on the latest news or just say Hi",
"TV show on social issues hosted by Aamir Khan. Currently, the team runs Paani Foundation in order to work towards creating a drought-free…",
"This is the official Twitter channel for Java and the source for Java news from the Java community",
"Actor in Indian Telugu Cinema",
"Account Executive at Dongguan Jianghan Electronics http://Co.ltd , manufacturer for iphone cable,Type-C cable,Ugreen,WD supplier",
"VR Gaming News, Info & Reviews. Subscribe to our YouTube Channel!");
		
		ldapRepo.getAllLdapUsers().stream()
						.map(e -> {
							Random rn = new Random();
							int index = rn.nextInt(7);
							abouts.get(index);
							
							return new UserProfile(e, "http://localhost:8080/images/profile-default.png", abouts.get(index), null);})
						.forEach(e -> userRepo.save(e));
		
		System.out.println("--> "+userRepo.findAll().size());
	}
	
	@Autowired
	public void setUserRepo(UserProfileRepository userRepo) {
		this.userRepo = userRepo;
	}
}
