package it.storelink.saluber.services.helper;

import it.storelink.saluber.services.model.Station;
import it.storelink.saluber.services.vo.StationVO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * User: kratos
 * Date: 08/12/15
 * Time: 11.22
 */

public class StationHelper {

    public static Station vo2Entity() {
        throw new NotImplementedException();
    }

    public static StationVO entity2VO(Station entity) {

        StationVO stationVO = new StationVO();

        stationVO.setId(entity.getId());
        stationVO.setCompleteAddress(entity.getCompleteAddress());
        stationVO.setLat(entity.getLat());
        stationVO.setLon(entity.getLon());
        stationVO.setName(entity.getName());
        stationVO.setNRoom(entity.getNRoom());
        stationVO.setPhoto(entity.getPhoto());

        return stationVO;
    }
}
