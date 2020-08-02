package pl.siara.geo.rest.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

public class StoredProcedureDAO  extends StoredProcedure{
    public StoredProcedureDAO(JdbcTemplate jdbcTemplate, String name) {
        super(jdbcTemplate, name);
        setFunction(false);
    }
}
