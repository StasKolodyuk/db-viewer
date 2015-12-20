package by.bsu.kolodyuk.service;

import by.bsu.kolodyuk.model.CreditRequest;
import by.bsu.kolodyuk.repository.CreditRequestRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CreditRequestService {

    @Resource
    private CreditRequestRepository creditRequestRepository;

    public CreditRequest getCreditRequest(Long userId) {
        return creditRequestRepository.findByUserId(userId);
    }

    public CreditRequest upsertCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    public List<CreditRequest> getNotValidatedCreditRequests() {
        return creditRequestRepository.findByValidatedNull();
    }

    public void updateCreditRequests(List<CreditRequest> creditRequests) {
        creditRequestRepository.save(creditRequests);
    }

}
