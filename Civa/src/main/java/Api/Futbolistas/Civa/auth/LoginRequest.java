package Api.Futbolistas.Civa.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequest {
    String usuario;
    String contraseia;
}
