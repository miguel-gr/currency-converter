package org.currconv.dao.hbn;

import java.util.List;

import org.currconv.dao.AbstractDao;
import org.currconv.dao.RateDao;
import org.currconv.entities.currencies.Rate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

@Repository ("rateDao")
public class RateDaoImpl extends AbstractDao<Integer, Rate> implements RateDao {

    public void saveRate(Rate rate) {
        persist(rate);
    }

    public List<Rate> findRecentRates(){
        DateTime now = new DateTime();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.gt("rateTime", now.minusHours(1).toDate()));
        return (List<Rate>) criteria.list();
    }
 
}