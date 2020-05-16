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
 * Using Rio to read a file and store it in an in-memory {@link Model}.
 * 
 * @see https://rdf4j.org/documentation/programming/rio/
 */
public class Example1 {

	public static void main(String[] args) throws Exception {
		String data = "/example-data-artists.ttl";

		Model model = Rio.parse(Example1.class.getResourceAsStream(data), "", RDFFormat.TURTLE);
		model.forEach(System.out::println);
	}

}
