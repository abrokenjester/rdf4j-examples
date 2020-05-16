/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.rio;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

/**
 * Using Rio to convert from one format to another.
 * 
 * @author jeen
 *
 */
public class Example2_convert_to_json {

	public static void main(String[] args) throws Exception {
		String data = "/example-data-artists.ttl";

		Model model = Rio.parse(Example2_convert_to_json.class.getResourceAsStream(data), "",
				Rio.getParserFormatForFileName(data).orElse(RDFFormat.RDFXML));
		Rio.write(model, System.out, RDFFormat.RDFJSON);
	}

}
