package by.bsu.kolodyuk.service;

import by.bsu.kolodyuk.model.CreditInfo;
import by.bsu.kolodyuk.repository.CreditInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CreditInfoService {

    @Resource
    private CreditInfoRepository creditInfoRepository;

    public List<CreditInfo> getCreditHistory(Long userId) {
        return creditInfoRepository.findCreditInfoByUserId(userId);
    }

}
