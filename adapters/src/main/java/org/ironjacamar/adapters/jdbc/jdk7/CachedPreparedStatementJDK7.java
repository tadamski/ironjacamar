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

package org.ironjacamar.adapters.jdbc.jdk7;

import org.ironjacamar.adapters.jdbc.CachedPreparedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * CachedPreparedStatementJDK7.
 * 
 * @author <a href="jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public class CachedPreparedStatementJDK7 extends CachedPreparedStatement
{
   private static final long serialVersionUID = 1L;

   /**
    * Constructor
    * @param ps The prepared statement
    * @exception SQLException Thrown if an error occurs
    */
   public CachedPreparedStatementJDK7(PreparedStatement ps) throws SQLException
   {
      super(ps);
   }
}
