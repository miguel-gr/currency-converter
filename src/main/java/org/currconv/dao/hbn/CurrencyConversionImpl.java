package org.currconv.dao.hbn;

import java.util.Date;
import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.currconv.dao.AbstractDao;
import org.currconv.dao.CurrencyConversionDao;
import org.springframework.stereotype.Repository;
import org.currconv.entities.currencies.CurrencyConversion;

@Repository ("CurrencyConversionDao")
public class CurrencyConversionImpl extends AbstractDao<Integer, CurrencyConversion> implements CurrencyConversionDao {

    public CurrencyConversion findById(final int id) {
        return getByKey(id);
    }
    public void saveCurrencyConversion(final CurrencyConversion currencyConversion) {
        currencyConversion.setConversionDate(new Date());
        persist(currencyConversion);
    }

    public List<CurrencyConversion> findRecentConversions(final int maxResults, final int userId){
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.desc("conversionDate"));
        criteria.setMaxResults(maxResults);
        criteria.add(Restrictions.eq("userId", userId));
        return (List<CurrencyConversion>) criteria.list();
    }
 
}