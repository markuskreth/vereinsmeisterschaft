package de.kreth.vereinsmeisterschaftprog;

import java.util.List;

import de.kreth.vereinsmeisterschaftprog.data.Durchgang;
import de.kreth.vereinsmeisterschaftprog.data.WertungFactory;
import de.kreth.vereinsmeisterschaftprog.db.Persister;

public abstract class Factory {

	protected static Factory instance = null;

	public static Factory getInstance() {
		if (instance == null)
			throw new IllegalStateException("Factory nicht initalisiert");
		return instance;
	}

	public abstract Persister getPersister();

	public abstract WertungFactory getWertungFactory();

	public abstract List<Durchgang> getDurchgaenge();
}
