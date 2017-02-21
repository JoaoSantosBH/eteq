package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2017-02-21T14:00:27")
@StaticMetamodel(RespostaEntidade.class)
public class RespostaEntidade_ { 

    public static volatile SingularAttribute<RespostaEntidade, Integer> idResposta;
    public static volatile SingularAttribute<RespostaEntidade, Integer> idForum;
    public static volatile SingularAttribute<RespostaEntidade, Date> dataResposta;
    public static volatile SingularAttribute<RespostaEntidade, Integer> idUsuarioRemetente;
    public static volatile SingularAttribute<RespostaEntidade, Short> lida;
    public static volatile SingularAttribute<RespostaEntidade, Integer> idUsuarioDestinatario;
    public static volatile SingularAttribute<RespostaEntidade, String> textoResposta;

}