package br.com.amaurygomes.ecpay.features.system.repository;

import br.com.amaurygomes.ecpay.features.system.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
