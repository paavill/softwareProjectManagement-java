package ru.rsreu.Chistyakov0618.fileSystemUtils;

import com.prutzkow.resourcer.Resourcer;

public enum ActResult {

	CREATED(Resourcer.getString("create.success")), 
	EXISTS(Resourcer.getString("create.exists")),
	NOT_CREATED(Resourcer.getString("create.notCreated")), 
	MOVED(Resourcer.getString("move.success")),
	COPIED(Resourcer.getString("copy.success"));

	private final StringBuilder resultDescription;
	private String[] elementsPathes;

	ActResult(String description) {
		this.resultDescription = new StringBuilder(description);
	}

	public String getResultDescription() {
		String result = this.resultDescription.toString();
		result = String.format(result, (Object[]) this.elementsPathes);
		return result;
	}

	public ActResult addElemenPath(String... path) {
		this.elementsPathes = path;
		return this;
	}
}
