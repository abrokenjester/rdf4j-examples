/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.rio;

import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;

/**
 * Using Rio to convert from one format to another without keeping everything in memory.
 * 
 * @author jeen
 *
 */
public class Example2b_convert_without_model {

	public static void main(String[] args) throws Exception {
		String data = "/example-data-artists.ttl";

		RDFParser parser = Rio.createParser(Rio.getParserFormatForFileName(data).orElse(RDFFormat.RDFXML));
		RDFWriter writer = Rio.createWriter(RDFFormat.RDFJSON, System.out);
		parser.setRDFHandler(writer);
		parser.parse(Example2b_convert_without_model.class.getResourceAsStream(data), "");

	}

}
