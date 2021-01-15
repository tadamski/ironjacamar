/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2014, Red Hat Inc, and individual contributors
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
package org.ironjacamar.common.metadata.common;

import org.ironjacamar.common.CommonBundle;
import org.ironjacamar.common.api.metadata.common.Credential;
import org.ironjacamar.common.api.validator.ValidateException;

import java.util.Map;

import org.jboss.logging.Messages;

/**
 * Credential implementation
 *
 * @author <a href="stefano.maestri@ironjacamar.org">Stefano Maestri</a>
 * @author <a href="jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public class CredentialImpl extends AbstractMetadata implements Credential
{
   /** The serialVersionUID */
   private static final long serialVersionUID = -5842402120520191086L;

   /** The bundle */
   private static CommonBundle bundle = Messages.getBundle(CommonBundle.class);

   /** The security domain */
   protected final String securityDomain;

   /**
    * Create a new credential
    *
    * @param securityDomain securityDomain
    * @param expressions expressions
    * @throws ValidateException ValidateException
    */
   public CredentialImpl(String securityDomain, Map<String, String> expressions) throws ValidateException
   {
      super(expressions);
      this.securityDomain = securityDomain;
      this.validate();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public final String getSecurityDomain()
   {
      return securityDomain;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((securityDomain == null) ? 0 : securityDomain.hashCode());
      return result;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (!(obj instanceof CredentialImpl))
         return false;
      CredentialImpl other = (CredentialImpl) obj;
      if (securityDomain == null)
      {
         if (other.securityDomain != null)
            return false;
      }
      else if (!securityDomain.equals(other.securityDomain))
         return false;
      return true;
   }

   /**
    * {@inheritDoc}
    */
   public void validate() throws ValidateException
   {
      if (securityDomain == null)
         throw new ValidateException(bundle.invalidSecurityConfiguration());
   }
}
