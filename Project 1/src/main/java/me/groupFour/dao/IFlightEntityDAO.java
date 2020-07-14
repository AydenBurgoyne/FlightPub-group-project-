package me.groupFour.dao;

import me.groupFour.data.FlightEntity;

import java.util.List;

public interface IFlightEntityDAO extends IEntityDAO<FlightEntity, Integer> {




    List<FlightEntity> getAllDistinct();
}
