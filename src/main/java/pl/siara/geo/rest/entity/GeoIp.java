package pl.siara.geo.rest.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoIp {

    private String ipAddress;
    private String latitude;
    private String longitude;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    
}
