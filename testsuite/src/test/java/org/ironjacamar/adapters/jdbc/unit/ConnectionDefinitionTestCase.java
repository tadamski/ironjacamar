/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2010, Red Hat Inc, and individual contributors
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

package org.ironjacamar.adapters.jdbc.unit;

import org.ironjacamar.adapters.ArquillianJCATestUtils;
import org.ironjacamar.adapters.jdbc.WrappedConnection;
import org.ironjacamar.adapters.jdbc.unit.support.TestConnection;
import org.ironjacamar.embedded.Deployment;
import org.ironjacamar.embedded.dsl.InputStreamDescriptor;
import org.ironjacamar.embedded.junit4.IronJacamar;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.jboss.shrinkwrap.api.spec.ResourceAdapterArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptor;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for connection definition
 *
 * @author <a href="mailto:jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 * @version $Revision: $
 */
@RunWith(IronJacamar.class)
public class ConnectionDefinitionTestCase
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
   public static ResourceAdapterArchive createArchive() throws Exception
   {
      return ArquillianJCATestUtils.buildShrinkwrapJdbcLocal();
   }

   /**
    * Define the -ds.xml
    * @return The deployment archive
    * @throws Exception in case of errors
    */
   @Deployment(order = 2)
   public static Descriptor createDescriptor() throws Exception
   {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      InputStreamDescriptor isd = new InputStreamDescriptor("connection-definition-ds.xml",
                                                            cl.getResourceAsStream("connection-definition-ds.xml"));
      return isd;
   }

   //-------------------------------------------------------------------------------------||
   //---------------------- WHEN  --------------------------------------------------------||
   //-------------------------------------------------------------------------------------||
   //
   @Resource(mappedName = "java:/ConDefDS")
   private DataSource ds;

   //-------------------------------------------------------------------------------------||
   //---------------------- THEN  --------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Test connection definitions
    * @exception Throwable Thrown if case of an error
    */
   @Test
   public void test() throws Throwable
   {
      assertNotNull(ds);
      Connection c = ds.getConnection();
      assertNotNull(c);
      assertTrue(c instanceof WrappedConnection);

      WrappedConnection wc = (WrappedConnection)c;

      TestConnection tc = (TestConnection)wc.getUnderlyingConnection();
      assertNotNull(tc);

      assertTrue("test".equals(tc.getUser()));
      assertTrue("test".equals(tc.getPassword()));

      assertTrue("test".equals(tc.getStringProperty()));

      c.close();
   }
}
