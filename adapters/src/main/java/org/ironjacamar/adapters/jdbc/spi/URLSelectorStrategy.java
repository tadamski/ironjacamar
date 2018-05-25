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

package org.ironjacamar.adapters.jdbc.spi;

import java.util.List;

/**
 * URLSelectorStrategy
 *
 * @author <a href="mailto:jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public interface URLSelectorStrategy 
{
   /**
    * Init the plugin with the URLs
    * @param urls The URLs
    */
   public void init(List<String> urls);

   /**
    * Does the plugin has more valid URLs ?
    * @return True, if more are available, otherwise false
    */
   public boolean hasMore();

   /**
    * Get the active URL
    * @return The value
    */
   public String active();

   /**
    * Fail an URL - e.g. mark it as bad
    * @param url The URL
    */
   public void fail(String url);

   /**
    * URL was accepted - potentially mark it as active
    * @param url The URL
    */
   public default void success(String url)
   {
   // DO NOTHING
   }

   /**
    * Reset the plugin
    */
   public void reset();

   /**
    * Get the data for an error presentation
    * @return The value
    */
   public String getData();
}
