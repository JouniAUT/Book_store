package hh.sof03.kirjakauppa.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof03.kirjakauppa.domain.User;
import hh.sof03.kirjakauppa.domain.UserRepository;

//Autentikoidaan ja autorisoidaan käyttäjä, jos hänelle myönnetään admin-oikeudet

@Service
public class UserDetailServiceImplementation implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserDetailServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User currentuser = userRepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentuser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(currentuser.getRole()));
		return user;
	}
}
