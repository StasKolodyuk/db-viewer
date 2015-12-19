package by.bsu.kolodyuk.repository;


import by.bsu.kolodyuk.model.AccountInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountInfoRepository extends CrudRepository<AccountInfo, Long> {

    public List<AccountInfo> findByValidatedNull();
    public List<AccountInfo> findByValidatedTrue();

}
