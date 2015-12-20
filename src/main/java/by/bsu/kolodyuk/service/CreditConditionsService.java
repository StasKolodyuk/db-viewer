package by.bsu.kolodyuk.service;


import by.bsu.kolodyuk.model.CreditConditions;
import by.bsu.kolodyuk.repository.CreditConditionsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CreditConditionsService {

    @Resource
    private CreditConditionsRepository creditConditionsRepository;

    public CreditConditions getCreditConditions(Long userId) {
        return creditConditionsRepository.findByUserId(userId);
    }

    public void saveCreditConditions(CreditConditions creditConditions) {
        creditConditionsRepository.save(creditConditions);
    }

}
