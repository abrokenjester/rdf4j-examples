/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.repository;

import java.net.URL;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

/**
 * Adding data to a {@link Repository}: in-memory database.
 * 
 */
public class Example1_add_model {

	public static void main(String[] args) throws Exception {

		URL data = Example1_add_model.class.getResource("/example-data-artists.ttl");

		Model model = Rio.parse(data.openStream(), data.toExternalForm(), RDFFormat.TURTLE);

		Repository rep = new SailRepository(new MemoryStore());

		try (RepositoryConnection conn = rep.getConnection()) {
			conn.add(model);
			conn.getStatements(null, null, null).forEach(System.out::println);
		} finally {
			rep.shutDown();
		}
	}

}
