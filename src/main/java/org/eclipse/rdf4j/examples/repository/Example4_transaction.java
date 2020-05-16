/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.repository;

import java.net.URL;

import org.eclipse.rdf4j.RDF4JException;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

/**
 * Adding data to a {@link Repository} using a transaction.
 * 
 * @see https://rdf4j.org/documentation/programming/repository/#transactions
 */
public class Example4_transaction {

	public static void main(String[] args) throws Exception {

		URL dataValid = Example4_transaction.class.getResource("/example-data-artists.ttl");
		URL dataInvalid = Example4_transaction.class.getResource("/example-data-artists-invalid.ttl");

		Repository rep = new SailRepository(new MemoryStore());

		try (RepositoryConnection conn = rep.getConnection()) {
			conn.begin();
			// first file will succeed
			conn.add(dataValid.openStream(), dataValid.toExternalForm(), RDFFormat.TURTLE);

			// second file will fail
			conn.add(dataInvalid.openStream(), dataInvalid.toExternalForm(), RDFFormat.TURTLE);
			conn.commit();

		} catch (RDF4JException e) {
			// if something goes wrong, we just print the error and otherwise ignore.
			System.out.println("an error occurred: " + e.getMessage());
		}

		try (RepositoryConnection conn = rep.getConnection()) {
			TupleQuery query = conn.prepareTupleQuery("SELECT * { ?s ?p ?o } LIMIT 100");
			TupleQueryResult result = query.evaluate();
			if (result.hasNext()) {
				result.forEach(System.out::println);
			} else {
				System.out.println("nothing in this database");
			}
		}

	}

}
