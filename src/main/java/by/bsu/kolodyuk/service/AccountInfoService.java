package by.bsu.kolodyuk.service;


import by.bsu.kolodyuk.model.AccountInfo;
import by.bsu.kolodyuk.repository.AccountInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountInfoService {

    @Resource
    private AccountInfoRepository accountInfoRepository;

    public List<AccountInfo> getNotValidatedAccountInfos() {
        return accountInfoRepository.findByValidatedNull();
    }

    public List<AccountInfo> getValidatedAccountInfos() {
        return accountInfoRepository.findByValidatedTrue();
    }

    public void updateAccountInfos(List<AccountInfo> accountInfos) {
        accountInfoRepository.save(accountInfos);
    }

}