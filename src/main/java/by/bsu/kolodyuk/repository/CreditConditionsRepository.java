package by.bsu.kolodyuk.repository;


import by.bsu.kolodyuk.model.CreditConditions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditConditionsRepository extends CrudRepository<CreditConditions, Long> {
    CreditConditions findByUserId(Long userId);
}
