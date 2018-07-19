/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2013, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 1.0 as
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Eclipse
 * Public License for more details.
 *
 * You should have received a copy of the Eclipse Public License
 * along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.ironjacamar.adapters.jdbc.spi;

import org.ironjacamar.adapters.ArquillianJCATestUtils;
import org.ironjacamar.adapters.jdbc.spi.testimpl.TestConnectionListener;
import org.ironjacamar.embedded.Deployment;
import org.ironjacamar.embedded.dsl.InputStreamDescriptor;
import org.ironjacamar.embedded.dsl.datasources20.api.DatasourceType;
import org.ironjacamar.embedded.dsl.datasources20.api.DatasourcesDescriptor;
import org.ironjacamar.embedded.dsl.datasources20.api.ExtensionType;
import org.ironjacamar.embedded.dsl.datasources20.api.PoolType;
import org.ironjacamar.embedded.dsl.resourceadapters20.api.ConnectionDefinitionsType;
import org.ironjacamar.embedded.junit4.IronJacamar;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.resource.spi.TransactionSupport;
import javax.sql.DataSource;

import org.ironjacamar.rars.lazy.LazyManagedConnectionFactory;
import org.jboss.shrinkwrap.api.spec.ResourceAdapterArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptor;

import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * A ConnectionListenerTestCase.
 *
 * @author <a href="jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
@RunWith(IronJacamar.class)
public class ConnectionListenerTestCase
{

   //-------------------------------------------------------------------------------------||
   //---------------------- GIVEN --------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Define the deployment
    * @return The deployment archive
    * @throws Exception in case of errors
    */
   @Deployment(order = 1)
   private ResourceAdapterArchive createArchive() throws Exception
   {
      return ArquillianJCATestUtils.buildShrinkwrapJdbcLocal();
   }

   /**
    * Define the -ds.xml
    * @return The deployment archive
    * @throws Exception in case of errors
    */
   @Deployment(order = 2)
   private Descriptor createDescriptor() throws Exception
   {
      DatasourcesDescriptor descriptor = Descriptors.create(DatasourcesDescriptor.class, "h2-connection-listener-ds.xml");

      DatasourceType datasource = descriptor.createDatasource();

      datasource.jndiName("java:/H2DS");

      datasource.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
      datasource.driverClass("org.h2.Driver");

      datasource.getOrCreateSecurity().userName("sa").password("sa");

      ExtensionType connectionListener = datasource.getOrCreatePool().getOrCreateConnectionListener();
      connectionListener.className("org.ironjacamar.adapters.jdbc.spi.testimpl.TestConnectionListener");
      connectionListener.createConfigProperty().name("testString").text("MyTest");

      return descriptor;
   }

   //-------------------------------------------------------------------------------------||
   //---------------------- WHEN  --------------------------------------------------------||
   //-------------------------------------------------------------------------------------||
   //
   @Resource(mappedName = "java:/H2DS")
   private DataSource ds;

   //-------------------------------------------------------------------------------------||
   //---------------------- THEN  --------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Basic
    * @exception Throwable Thrown if case of an error
    */
   @Test
   public void shouldUseConnectionListener() throws Throwable
   {
      Connection c = null;
      assertNotNull(ds);

      assertFalse(TestConnectionListener.isPropertyCalled());
      assertFalse(TestConnectionListener.isInitializedCalled());
      assertFalse(TestConnectionListener.isActivatedCalled());
      assertFalse(TestConnectionListener.isPassivatedCalled());

      c = ds.getConnection();
      assertNotNull(c);

      assertTrue(TestConnectionListener.isPropertyCalled());
      assertTrue(TestConnectionListener.isInitializedCalled());
      assertTrue(TestConnectionListener.isActivatedCalled());

      try
      {
         c.close();
      }
      catch (SQLException se)
      {
         // Ignore
      }

      assertTrue(TestConnectionListener.isPassivatedCalled());
   }
}
