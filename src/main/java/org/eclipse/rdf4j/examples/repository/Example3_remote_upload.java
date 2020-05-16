/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.repository;

import java.net.URL;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;

/**
 * Adding data to a {@link HTTPRepository}
 * 
 */
public class Example3_remote_upload {

	public static void main(String[] args) throws Exception {

		Repository rep = new HTTPRepository("http://localhost:8080/rdf4j-server/repositories/artists");

		ValueFactory vf = rep.getValueFactory();
		IRI graph = vf.createIRI("file:///example-data-artists-from-code.ttl");
		try (RepositoryConnection conn = rep.getConnection()) {
			URL data = Example3_remote_upload.class.getResource("/example-data-artists.ttl");

			conn.add(data.openStream(), data.toExternalForm(), RDFFormat.TURTLE, graph);
		} finally {
			rep.shutDown();
		}
	}

}
