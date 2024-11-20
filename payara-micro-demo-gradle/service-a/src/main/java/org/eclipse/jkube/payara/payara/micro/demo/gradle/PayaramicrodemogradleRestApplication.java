package org.eclipse.jkube.payara.payara.micro.demo.gradle;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/data")
@ApplicationScoped
public class PayaramicrodemogradleRestApplication extends Application {
}
