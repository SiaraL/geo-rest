package pl.siara.geo.rest.dao;

import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class ProceduresDAOFactory extends NamedParameterJdbcDaoSupport{

    @Autowired
    public void setDataSors(@Autowired @Qualifier("serverDataSource") BasicDataSource dataSource) {
        setDataSource(dataSource);
    }
    Map<String, StoredProcedureDAO> cachedProcedures;


    public void addProcedure(String name, SqlParameter... parameters) {
        StoredProcedureDAO store = new StoredProcedureDAO(getJdbcTemplate(), "dbo." + name);
        store.setParameters(parameters);
        store.compile();
        cachedProcedures.put(name, store);
    }

    public Map<String, Object> callProcedure(String procedureName, Object... parameters) {
        return cachedProcedures.get(procedureName).execute(parameters);
    }

}
