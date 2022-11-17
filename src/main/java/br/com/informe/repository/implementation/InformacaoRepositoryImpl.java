package br.com.informe.repository.implementation;

import br.com.informe.dto.FiltroInformacaoDTO;
import br.com.informe.entity.Informacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformacaoRepositoryImpl {


    @PersistenceContext
    private EntityManager em;
    private static final String CAMPO_PESQUISA = "campoPesquisa";
    private static final String SQL_UNION = " UNION ";

    public List<Informacao> buscarPorParamentros(FiltroInformacaoDTO filtro) {
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder queryStr = new StringBuilder();

        if (filtro.getCampoPesquisa() != null){
            this.tratarSqlCampoPesquisa(parametros , queryStr , filtro);
        } else {
           this.tratarSqlSomenteDatas(parametros , queryStr , filtro);
        }

        Query query = em.createNativeQuery(queryStr.toString(), Informacao.class);
        parametros.forEach(query::setParameter);

        return query.getResultList();
    }

    public void tratarSqlCampoPesquisa( Map<String, Object> parametros ,  StringBuilder queryStr, FiltroInformacaoDTO filtro ){

        this.setSelect(queryStr);
        this.setJoins(queryStr);
        queryStr.append(" where info.id_informacao is not null and lower(info.detalhe) like lower(:campoPesquisa) ");
        parametros.put(CAMPO_PESQUISA , "%"+filtro.getCampoPesquisa()+"%");
        this.tratarDataInicioComFinalNull(parametros,queryStr,filtro);
        this.tratarDataInicioComFinal(parametros,queryStr,filtro);

        queryStr.append(SQL_UNION);
        this.setSelect(queryStr);
        this.setJoins(queryStr);
        queryStr.append(" WHERE info.id_informacao is not null and lower(info.titulo) like lower(:campoPesquisa) ");
        parametros.put(CAMPO_PESQUISA , "%"+filtro.getCampoPesquisa()+"%");
        this.tratarDataInicioComFinalNull(parametros,queryStr,filtro);
        this.tratarDataInicioComFinal(parametros,queryStr,filtro);

        queryStr.append(SQL_UNION);
        this.setSelect(queryStr);
        this.setJoins(queryStr);
        queryStr.append(" WHERE info.id_informacao is not null and lower(te.descricao) like lower(:campoPesquisa) ");
        parametros.put(CAMPO_PESQUISA , "%"+filtro.getCampoPesquisa()+"%");
        this.tratarDataInicioComFinalNull(parametros,queryStr,filtro);
        this.tratarDataInicioComFinal(parametros,queryStr,filtro);

        queryStr.append(SQL_UNION);
        this.setSelect(queryStr);
        this.setJoins(queryStr);
        queryStr.append("  WHERE info.id_informacao is not null and lower(tp.nome) like lower(:campoPesquisa) ");
        parametros.put(CAMPO_PESQUISA , "%"+filtro.getCampoPesquisa()+"%");
        this.tratarDataInicioComFinalNull(parametros,queryStr,filtro);
        this.tratarDataInicioComFinal(parametros,queryStr,filtro);

        queryStr.append(SQL_UNION);
        this.setSelect(queryStr);
        this.setJoins(queryStr);
        queryStr.append("  WHERE info.id_informacao is not null and lower(tv.descricao) like lower(:campoPesquisa) ");
        parametros.put(CAMPO_PESQUISA , "%"+filtro.getCampoPesquisa()+"%");
        this.tratarDataInicioComFinalNull(parametros,queryStr,filtro);
        this.tratarDataInicioComFinal(parametros,queryStr,filtro);



    }



    public void tratarSqlSomenteDatas( Map<String, Object> parametros ,  StringBuilder queryStr, FiltroInformacaoDTO filtro){

        queryStr.append(" SELECT *  FROM informe.tb_informacao info  WHERE info.id_informacao is not null ");

            this.tratarDataInicioComFinalNull(parametros,queryStr,filtro);
            this.tratarDataInicioComFinal(parametros,queryStr,filtro);

        queryStr.append(" ORDER BY info.dt_ultima_alteracao DESC ");

    }


    public void tratarDataInicioComFinalNull(Map<String, Object> parametros ,  StringBuilder queryStr, FiltroInformacaoDTO filtro) {
        if (filtro.getDataInicial() != null && filtro.getDataFinal() == null) {
            queryStr.append(" AND date ( info.dt_ultima_alteracao )  BETWEEN date( :dataInicial ) AND date ( CURRENT_TIMESTAMP )");
            parametros.put("dataInicial", filtro.getDataInicial().toLocalDate());
        }
    }

    public void tratarDataInicioComFinal(Map<String, Object> parametros ,  StringBuilder queryStr, FiltroInformacaoDTO filtro) {
        if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
            queryStr.append(" AND date( info.dt_ultima_alteracao ) BETWEEN date( :dataInicial )  AND date( :dataFinal ) ");
            parametros.put("dataInicial", filtro.getDataInicial().toLocalDate());
            parametros.put("dataFinal", filtro.getDataFinal().toLocalDate());
        }
    }

    public void setSelect(StringBuilder queryStr) {
        queryStr.append(" SELECT info.* FROM informe.tb_informacao info  " );
    }

    public void setJoins(StringBuilder queryStr) {
        queryStr.append(" left join informe.tb_marcador tm on tm.id_informacao = info.id_informacao " +
                " left join informe.tb_endereco te on tm.id_endereco  = te.id_endereco " +
                " left join informe.tb_pessoa tp   on tp.id_informacao = info.id_informacao " +
                " left join informe.tb_veiculo tv  on tv.id_informacao = info.id_informacao" );
    }

}
