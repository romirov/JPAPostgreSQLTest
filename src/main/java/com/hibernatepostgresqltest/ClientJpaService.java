package com.hibernatepostgresqltest;

import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * @author hanza
 *	Класс ClientJpaService - записывет данные по клиенту в базу postgresql
 */
@Service
@EnableConfigurationProperties// - добавляется, если аннотация для доступности проперти из application.properties прописывается в TelegramBotUmediaServiceApplication
@Validated
public class ClientJpaService {
	@Autowired
	private final ClientApparatusesJpaRepositories clientJpaRepository;

	static final Logger rootLogger = LogManager.getLogger();
    static final Logger userLogger = LogManager.getLogger(ClientJpaService.class);
	
	public ClientJpaService(ClientApparatusesJpaRepositories clientJpaRepository) {
		   this.clientJpaRepository = clientJpaRepository;
	}
    
	@Transactional
    public Client create(Client client) {
		client = clientJpaRepository.saveAndFlush(client);
		userLogger.info(client.toString());
	    return client;
    }
	@Transactional
    public List<Client>  readAll() {
        return clientJpaRepository.findAll();
    }
	@Transactional
    public Client read(Long id) {
        return clientJpaRepository.getById(id);
    }
	@Transactional
    public boolean update(Client client, Long id) {
        if (clientJpaRepository.existsById(id)) {
            client.setIdClient(id);
            clientJpaRepository.save(client);
            return true;
        }

        return false;
    }
	@Transactional
    public boolean delete(Long id) {
        if (clientJpaRepository.existsById(id)) {
        	clientJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
	
}