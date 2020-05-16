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
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

/**
 * 
 * Back to Models: finding and printing all rdfs:label values that have a language tag (iterative approach)
 * 
 * @see https://rdf4j.org/documentation/programming/model/
 */
public class Example3_model_filtering {

	public static void main(String[] args) throws Exception {
		String data = "/example-data-artists.ttl";

		Model model = Rio.parse(Example3_model_filtering.class.getResourceAsStream(data), "",
				Rio.getParserFormatForFileName(data).orElse(RDFFormat.RDFXML));

		for (Statement st : model.filter(null, RDFS.LABEL, null)) {
			Value v = st.getObject();
			if (v instanceof Literal) {
				Literal l = (Literal) v;

				l.getLanguage().ifPresent(lang -> {
					if (lang.equals("en")) {
						System.out.println(l);
					}
				});
			}
		}
	}

}
