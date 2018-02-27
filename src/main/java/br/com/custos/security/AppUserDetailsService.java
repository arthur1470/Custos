package br.com.custos.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.custos.dao.UsuarioDAO;
import br.com.custos.model.Grupo;
import br.com.custos.model.Usuario;
import br.com.custos.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UsuarioDAO usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarioDAO.carregar(Usuario.class, login);
		UsuarioSistema user = null;
		
		if(usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		System.out.println(usuario.getLogin());
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Grupo grupo: usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}
