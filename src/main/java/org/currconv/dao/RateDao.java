package org.currconv.dao;

import java.util.List;

import org.currconv.entities.currencies.Rate;
 
public interface RateDao {

    void saveRate(Rate rate);

    List<Rate> findRecentRates();
 
}