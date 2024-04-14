package Api.Futbolistas.Civa.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioBackendRepository extends JpaRepository<UsuarioBackend,Integer> {
    Optional<UsuarioBackend> findUsuarioBackendByUsuario(String Usuario);
}
