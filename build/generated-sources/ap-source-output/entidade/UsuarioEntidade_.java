package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2017-02-21T14:00:27")
@StaticMetamodel(UsuarioEntidade.class)
public class UsuarioEntidade_ { 

    public static volatile SingularAttribute<UsuarioEntidade, String> senha;
    public static volatile SingularAttribute<UsuarioEntidade, Short> autentico;
    public static volatile SingularAttribute<UsuarioEntidade, String> foto;
    public static volatile SingularAttribute<UsuarioEntidade, Integer> idUsuario;
    public static volatile SingularAttribute<UsuarioEntidade, Short> validado;
    public static volatile SingularAttribute<UsuarioEntidade, String> nome;
    public static volatile SingularAttribute<UsuarioEntidade, Short> reserva;
    public static volatile SingularAttribute<UsuarioEntidade, Date> ultimoAcesso;
    public static volatile SingularAttribute<UsuarioEntidade, String> email;
    public static volatile SingularAttribute<UsuarioEntidade, Short> fotoAprovada;

}