/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.shacl;

import java.net.URL;

import org.eclipse.rdf4j.examples.repository.Example2a_sparql_tsv;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.vocabulary.RDF4J;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.sail.shacl.ShaclSail;
import org.eclipse.rdf4j.sail.shacl.ShaclSailValidationException;

/**
 * ShaclSail example: load shapes, then data. The data does not conform to the shacl shapes and therefore validation
 * will fail with an exception.
 * 
 * @see https://rdf4j.org/documentation/programming/shacl/
 */
public class Example1_shacl {

	public static void main(String[] args) throws Exception {
		ShaclSail shaclSail = new ShaclSail(new MemoryStore());
		Repository sailRepository = new SailRepository(shaclSail);

		URL shaclRules = Example2a_sparql_tsv.class.getResource("/shacl-shapes.ttl");
		URL data = Example2a_sparql_tsv.class.getResource("/example-data-artists.ttl");

		try (RepositoryConnection connection = sailRepository.getConnection()) {

			// add SHACL constraints
			connection.add(shaclRules.openStream(), "", RDFFormat.TURTLE, RDF4J.SHACL_SHAPE_GRAPH);

			// add data and validate
			connection.add(data.openStream(), data.toExternalForm(), RDFFormat.TURTLE);

		} catch (RepositoryException exception) {
			Throwable cause = exception.getCause();
			if (cause instanceof ShaclSailValidationException) {
				System.out.println("Validation failed!");
				Model validationReportModel = ((ShaclSailValidationException) cause).validationReportAsModel();
				Rio.write(validationReportModel, System.out, RDFFormat.TURTLE);
			} else {
				throw exception;
			}
		}

	}

}
