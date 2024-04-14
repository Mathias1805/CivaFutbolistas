package Api.Futbolistas.Civa.auth;


import Api.Futbolistas.Civa.JWT.JWTservice;
import Api.Futbolistas.Civa.User.Rol;
import Api.Futbolistas.Civa.User.UsuarioBackend;
import Api.Futbolistas.Civa.User.UsuarioBackendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UsuarioBackendRepository usuarioBackendRepository;
    @Autowired
    private final JWTservice jwTservice;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(),request.getContraseia()));
        UserDetails user = usuarioBackendRepository.findUsuarioBackendByUsuario(request.getUsuario()).orElseThrow();
        String token=jwTservice.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        UsuarioBackend user = UsuarioBackend.builder()
                .usuario(request.getUsuario())
                .contraseia(passwordEncoder.encode(request.getContraseia()))
                .rol(Rol.USER)
                .build();
        usuarioBackendRepository.save(user);
        return  AuthResponse.builder().token(jwTservice.getToken(user)).build();
    }
}
