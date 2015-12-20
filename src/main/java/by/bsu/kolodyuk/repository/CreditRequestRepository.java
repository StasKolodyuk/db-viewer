package by.bsu.kolodyuk.repository;

import by.bsu.kolodyuk.model.CreditRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditRequestRepository extends CrudRepository<CreditRequest, Long> {

    CreditRequest findByUserId(Long userId);
    List<CreditRequest> findByValidatedNull();

}
