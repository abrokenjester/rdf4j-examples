/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.rio;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

/**
 * Back to Models: finding and printing all rdfs:label values that have a language tag (streaming approach)
 * 
 * @author jeen
 *
 */
public class Example4_model_filtering_streaming {

	public static void main(String[] args) throws Exception {
		String data = "/example-data-artists.ttl";

		Model model = Rio.parse(Example4_model_filtering_streaming.class.getResourceAsStream(data), "",
				Rio.getParserFormatForFileName(data).orElse(RDFFormat.RDFXML));

		model.filter(null, RDFS.LABEL, null)
				.objects()
				.stream()
				.filter(v -> v instanceof Literal)
				.map(v -> (Literal) v)
				.filter(l -> l.getLanguage().isPresent())
				.filter(l -> l.getLanguage().get().equals("en"))
				.forEach(System.out::println);
	}

}
