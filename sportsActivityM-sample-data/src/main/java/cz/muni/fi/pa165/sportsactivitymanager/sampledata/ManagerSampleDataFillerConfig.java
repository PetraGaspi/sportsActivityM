package cz.muni.fi.pa165.sportsactivitymanager.sampledata;

import cz.muni.fi.pa165.sportsactivitymanager.service.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Takes ServiceConfiguration and adds the SampleDataLoadingFacade bean.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class ManagerSampleDataFillerConfig {

    final static Logger log = LoggerFactory.getLogger(ManagerSampleDataFillerConfig.class);

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
