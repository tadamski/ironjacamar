/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2010, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.jca.codegenerator.xml;

import org.jboss.jca.codegenerator.ConfigPropType;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * A BuildXmlGen.
 * 
 * @author Jeff Zhang
 * @version $Revision: $
 */
public class Ra16XmlGen extends RaXmlGen
{
   /**
    * write Connector Version
    * 
    * @param out output writer
    * @throws IOException io exception
    */
   @Override
   void writeConnectorVersion(Writer out) throws IOException
   {
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      writeEol(out);
      writeEol(out);
      out.write("<!--");
      writeEol(out);
      writeheader(null, out);
      out.write("-->");
      writeEol(out);
      writeEol(out);
      out.write("<connector xmlns=\"http://java.sun.com/xml/ns/javaee\"");
      writeEol(out);
      out.write("           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
      writeEol(out);
      out.write("           xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee");
      writeEol(out);
      out.write("           http://java.sun.com/xml/ns/j2ee/connector_1_6.xsd\"");
      writeEol(out);
      out.write("           version=\"1.6\" metadata-complete=\"true\">");
      writeEol(out);
      writeEol(out);
   }
   
   /**
    * Output As config props xml part
    * @param props config properties
    * @param out Writer
    * @param indent space number
    * @throws IOException ioException
    */
   @Override
   void writeAsConfigPropsXml(List<ConfigPropType> props, Writer out, int indent) throws IOException
   {
      writeRequireConfigPropsXml(props, out, indent);
      writeConfigPropsXml(props, out, indent);
   }
}
