package by.bsu.kolodyuk.repository;


import by.bsu.kolodyuk.model.CreditInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditInfoRepository extends CrudRepository<CreditInfo, Long> {

     List<CreditInfo> findCreditInfoByUserId(Long userId);

}
