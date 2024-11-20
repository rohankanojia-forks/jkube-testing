package org.eclipse.jkube.integration.tests.gradle.openliberty;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/data")
public class OpenlibertyRestApplication extends Application {
}
