package pl.siara.geo.rest.dao;

import java.sql.Timestamp;
import java.util.List;

import pl.siara.geo.rest.entity.GeoIp;

public interface GeoDao {

    public void addLocalInfo(String device, String latitude, String longitude, Timestamp date);
    public List<GeoIp> getLocalInfo();
    public void createGetLocalInfoProcedure();
    public void createAddLocalInfoProcedrue();
    
}
