package pl.siara.geo.rest.dao;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.siara.geo.rest.entity.GeoIp;

@Component
public class GeoDaoImpl implements GeoDao{

    @Autowired  
    ProceduresDAOFactory daoFactory;
    private static String ADD = "AddLocalInfo";
    private static String GET = "GetLocalInfo";
    
    @Override
    public void addLocalInfo(String device, String latitude, String longitude, Timestamp date) {
        daoFactory.callProcedure(ADD, device, latitude, longitude, date);
    }

    @Override
    public List<GeoIp> getLocalInfo() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(daoFactory.callProcedure(GET).get("#result-set-1"), mapper.getTypeFactory().constructCollectionType(List.class, GeoIp.class));
    }

    @PostConstruct
    @Override
    public void createGetLocalInfoProcedure() {
        daoFactory.addProcedure(GET);
    }

    @PostConstruct
    @Override
    public void createAddLocalInfoProcedrue() {
        SqlParameter device = new SqlParameter("device", Types.VARCHAR);
        SqlParameter latitude = new SqlParameter("latitude", Types.VARCHAR);
        SqlParameter longitude = new SqlParameter("longitude", Types.VARCHAR);
        SqlParameter date = new SqlParameter("date", Types.TIMESTAMP);
        daoFactory.addProcedure(ADD, device, latitude, longitude, date);
    }

}
