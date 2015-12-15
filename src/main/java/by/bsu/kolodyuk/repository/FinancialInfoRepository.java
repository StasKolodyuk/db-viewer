package by.bsu.kolodyuk.repository;

import by.bsu.kolodyuk.model.FinancialInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FinancialInfoRepository extends CrudRepository<FinancialInfo, Long> {

    FinancialInfo findByUserId(Long userId);
    List<FinancialInfo> findByValidatedNull();

}
