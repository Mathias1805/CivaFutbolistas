package Api.Futbolistas.Civa.auth;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String usuario;
    String contraseia;
}
