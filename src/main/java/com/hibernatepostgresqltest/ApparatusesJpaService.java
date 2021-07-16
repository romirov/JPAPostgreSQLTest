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
 *	Класс ApparatusesJpaService - записывет данные по оборудованию клиента в базу postgresql
 */
@Service
@EnableConfigurationProperties// - добавляется, если аннотация для доступности проперти из application.properties прописывается в TelegramBotUmediaServiceApplication
@Validated
public class ApparatusesJpaService {
		@Autowired
		private final ApparatusesJpaRepository apparatusesJpaRepository;

		static final Logger rootLogger = LogManager.getLogger();
	    static final Logger userLogger = LogManager.getLogger(ClientJpaService.class);
		
		public ApparatusesJpaService(ApparatusesJpaRepository apparatusesJpaRepository) {
			   this.apparatusesJpaRepository = apparatusesJpaRepository;
		}
	    
	    
		@Transactional
	    public Apparatuses create(Apparatuses apparatuses) {			
			apparatuses = apparatusesJpaRepository.saveAndFlush(apparatuses);
			userLogger.info(apparatuses.toString());
		    return apparatuses;
	    }
		@Transactional
	    public List<Apparatuses>  readAll() {
	        return apparatusesJpaRepository.findAll();
	    }
		@Transactional
	    public Apparatuses read(Long id) {
	        return apparatusesJpaRepository.getById(id);
	    }
		@Transactional
	    public boolean update(Apparatuses apparatuses, Long id) {
	        if (apparatusesJpaRepository.existsById(id)) {
	        	apparatuses.setIdApparatuses(id);
	            apparatusesJpaRepository.saveAndFlush(apparatuses);
	            return true;
	        }

	        return false;
	    }
		@Transactional
	    public boolean delete(Long id) {
	        if (apparatusesJpaRepository.existsById(id)) {
	        	apparatusesJpaRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
}
