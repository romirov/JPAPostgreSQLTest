package com.hibernatepostgresqltest;

import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
/**
 * Класс HibernateJpaPostgreSqlTestApplication тестирует работу записи данных в БД postgresql,
 *  связъ таблицы client к таблице apparatuses как один ко многим 
 * @param client - содержит информацию по клиенту, в том числе и по оборудованию, принадлежащему клиенту
 * @param apparatuses - содержит множество оборудования, принадлежащее клиенту
 * @param rootLogger, userLogger - для логирования
 */

@SpringBootApplication
@ConfigurationProperties
public class HibernateJpaPostgreSqlTestApplication {
	static private Client client = new Client();
	static private Set<Apparatuses> apparatuses = new HashSet<Apparatuses>();
	
	static final Logger rootLogger = LogManager.getLogger();
    static final Logger userLogger = LogManager.getLogger(HibernateJpaPostgreSqlTestApplication.class);

	public static void main(String[] args) {
		final ApplicationContext ctx = SpringApplication.run(HibernateJpaPostgreSqlTestApplication.class, args);
		
		Apparatuses a1 = new Apparatuses();
		a1.setRecNumber("a1-1");
		a1.setDefect("trouble_1");
		Apparatuses a2 = new Apparatuses();
		a2.setRecNumber("a2-1");
		a2.setDefect("trouble_2");
		apparatuses.add(a1);
		apparatuses.add(a2);
		client = new Client();
		client.setName("Test");
		client.setSurname("Testovich");
		userLogger.info("CLINET BEFORE save to DB:" + apparatuses.toString());
		userLogger.info("CLINET BEFORE save to DB:" + client.toString());
		
		ApparatusesJpaRepository apparatusesJpaRepository = ctx.getBean(ApparatusesJpaRepository.class);
		ApparatusesJpaService  apparatusesJpaService = new ApparatusesJpaService(apparatusesJpaRepository);
		
		apparatuses.forEach(a -> {a = apparatusesJpaService.create(a); /*userLogger.info(a);*/});
		
		apparatuses.forEach(a -> {client.addApparatuses(a);});
		ClientApparatusesJpaRepositories clientJpaRepository = ctx.getBean(ClientApparatusesJpaRepositories.class);
    	ClientJpaService  clientJpaService = new ClientJpaService(clientJpaRepository);
    	
    	client = clientJpaService.create(client);
    	clientJpaService.update(client, client.getIdClient() );
    	userLogger.info("CLIENT AFTER save to DB:" + clientJpaService.read(client.getIdClient()));
    	    	
	}

}
