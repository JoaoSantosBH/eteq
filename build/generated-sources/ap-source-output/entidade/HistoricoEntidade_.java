package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2017-02-21T14:00:27")
@StaticMetamodel(HistoricoEntidade.class)
public class HistoricoEntidade_ { 

    public static volatile SingularAttribute<HistoricoEntidade, Integer> idUsuarioTem;
    public static volatile SingularAttribute<HistoricoEntidade, Short> concluido;
    public static volatile SingularAttribute<HistoricoEntidade, Integer> idPatrimonioTem;
    public static volatile SingularAttribute<HistoricoEntidade, Integer> idPatrimonioQuis;
    public static volatile SingularAttribute<HistoricoEntidade, Date> dataDesativacao;
    public static volatile SingularAttribute<HistoricoEntidade, Integer> idHistorico;
    public static volatile SingularAttribute<HistoricoEntidade, Integer> idUsuarioQuis;
    public static volatile SingularAttribute<HistoricoEntidade, String> status;

}