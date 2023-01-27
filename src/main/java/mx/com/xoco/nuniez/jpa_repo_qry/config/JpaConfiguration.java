package mx.com.xoco.nuniez.jpa_repo_qry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfiguration {

    /*
     * @Autowired private EntityManagerFactory entityManagerFactory;
     *
     * @Bean public SessionFactory getSessionFactory() { if
     * (entityManagerFactory.unwrap(SessionFactory.class) == null) { throw new
     * NullPointerException("factory is not a hibernate factory"); } return
     * entityManagerFactory.unwrap(SessionFactory.class); }
     */

}
