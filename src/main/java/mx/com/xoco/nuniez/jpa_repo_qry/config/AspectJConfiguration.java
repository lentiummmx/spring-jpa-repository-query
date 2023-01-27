package mx.com.xoco.nuniez.jpa_repo_qry.config;

import mx.com.xoco.nuniez.jpa_repo_qry.aspects.QueryHintsAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver;

@Configuration
@EnableAspectJAutoProxy
@EnableLoadTimeWeaving
public class AspectJConfiguration implements LoadTimeWeavingConfigurer {

    @Bean
    QueryHintsAspect queryHintsAspect() {
        System.out.println("Configuring QueryHintsAspect");
        return new QueryHintsAspect();
    }

    @Override
    public LoadTimeWeaver getLoadTimeWeaver() {
        System.out.println("Configuring LoadTimeWeaver");
        return new InstrumentationLoadTimeWeaver();
    }

}
