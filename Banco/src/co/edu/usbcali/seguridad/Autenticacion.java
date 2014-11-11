package co.edu.usbcali.seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.edu.usbcali.logica.IClientesLogica;
import co.edu.usbcali.logica.IUsuariosLogica;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Usuarios;
 
 
@Scope("singleton")
@Component("Autenticacion")
public class Autenticacion implements AuthenticationProvider {
	
	@Autowired
	private IUsuariosLogica usuariosLogica;
	@Autowired
	private IClientesLogica clientesLogica;
     
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
    	
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Usuarios usuario = null;
        Clientes cliente = null;
                 
        if (name.equals("admin") && password.equals("1234")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            
            //HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            //httpSession.setAttribute("usuario", name);

            return auth;
        } else {
        		try{
					usuario = usuariosLogica.consultarUsuariosLoginClave(name, password);
        		} catch (Exception e) {
					e.printStackTrace();
				}	
				if(usuario != null){
					if(usuario.getTiposUsuarios().getTusuCodigo() == 10){
						final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				        grantedAuths.add(new SimpleGrantedAuthority("ROLE_CAJERO"));
				        final UserDetails principal = new User(name, password, grantedAuths);
				        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
				        return auth;
					}else if(usuario.getTiposUsuarios().getTusuCodigo() == 20){
						final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ASESOR"));
				        final UserDetails principal = new User(name, password, grantedAuths);
				        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
				        return auth;
					}
				}else {
					Long numCue = Long.parseLong(name);
					Long idCliente = 101234L;
					try{
						cliente = clientesLogica.consultarClientesCuenta(numCue, idCliente, password);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(cliente != null){
						final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				        grantedAuths.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
				        final UserDetails principal = new User(name, password, grantedAuths);
				        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
				        return auth;
					}else{
						return null;
					}
				}
				return null;
        }
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
         return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loginController");
        return "login.html";
    }
 
}