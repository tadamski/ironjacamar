/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2018, Red Hat Inc, and individual contributors
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

package org.ironjacamar.adapters.jdbc.local.testimpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * MockDriver
 */
public class MockDriver implements Driver
{

   @Override
   public Connection connect(String url, Properties info) throws SQLException
   {
      return MockConnection.create(url);
   }

   @Override
   public boolean acceptsURL(String url) throws SQLException
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int getMajorVersion()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getMinorVersion()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public boolean jdbcCompliant()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Logger getParentLogger() throws SQLFeatureNotSupportedException
   {
      // TODO Auto-generated method stub
      return null;
   }

}

