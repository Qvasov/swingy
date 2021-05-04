package model;

import java.util.LinkedList;
import java.util.List;

public abstract class Storage<T> {
	protected List<T> data;

	public Storage() {
		this.data = new LinkedList<>();
	}

}
