package br.com.amaurygomes.ecpay.features.system.service;


import br.com.amaurygomes.ecpay.features.system.entity.Log;
import br.com.amaurygomes.ecpay.features.system.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void saveLog(Log log){
        logRepository.save(log);
    }

    public Page<Log> getAllLogs(Pageable pageable){
        return logRepository.findAll(pageable);
    }

}
