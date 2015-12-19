package by.bsu.kolodyuk.service;

import by.bsu.kolodyuk.model.FinancialInfo;
import by.bsu.kolodyuk.repository.FinancialInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinancialInfoService {

    @Resource
    private FinancialInfoRepository financialInfoRepository;

    public FinancialInfo getFinancialInfo(Long userId) {
        return financialInfoRepository.findByUserId(userId);
    }

    public FinancialInfo upsertFinancialInfo(FinancialInfo financialInfo) {
        return financialInfoRepository.save(financialInfo);
    }

    public List<FinancialInfo> getNotValidatedFinancialInfos() {
        return financialInfoRepository.findByValidatedNull();
    }

    public void updateFinancialInfos(List<FinancialInfo> financialInfos) {
        financialInfoRepository.save(financialInfos);
    }

}
