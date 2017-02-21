package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2017-02-21T14:00:27")
@StaticMetamodel(ForumEntidade.class)
public class ForumEntidade_ { 

    public static volatile SingularAttribute<ForumEntidade, String> assunto;
    public static volatile SingularAttribute<ForumEntidade, Short> ativo;
    public static volatile SingularAttribute<ForumEntidade, Integer> idNegociacao;
    public static volatile SingularAttribute<ForumEntidade, Integer> idForum;
    public static volatile SingularAttribute<ForumEntidade, String> mensagem;
    public static volatile SingularAttribute<ForumEntidade, Integer> idUsuarioRemetente;
    public static volatile SingularAttribute<ForumEntidade, Integer> idUsuarioDestinatario;
    public static volatile SingularAttribute<ForumEntidade, Date> dataMensagem;

}