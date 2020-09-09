/*
 * abhishek360
 */

package org.wells.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wells.models.Company;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	/*
	 * SELECT c.company_name AS companyName, c.company_id AS companyId, AVG(s.price) AS averagePrice FROM company c JOIN stock_price s JOIN sector sec WHERE s.date BETWEEN '2018-09-11' AND '2020-01-01' AND sec.sector_id = 1 GROUP BY c.company_id;

	 */

    @Query("SELECT c.companyName AS companyName, c.companyId AS companyId, AVG(s.price) AS averagePrice " +
            "FROM Company c JOIN c.stockPrices s JOIN c.sector sec " +
            "WHERE s.date BETWEEN ?1 AND ?2 AND sec.sectorId = ?3 GROUP BY c.companyId")
    List<Map<String, Object>> getStockPriceInDateRange(Date startDate, Date endDate, int sectorId);
}
