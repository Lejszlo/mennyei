package core.service.exceptions;

import core.domain.entity.BaseNodeEntity;

public class ColudNotSavedException extends Exception {

	private BaseNodeEntity entity;
	private String baseMessage;

	public ColudNotSavedException(BaseNodeEntity entity) {
		this.entity = entity;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return String.format("Colud not save the %1 entity! ID: %2", entity.getClass(), entity.getId());
	}

	public BaseNodeEntity getEntity() {
		return entity;
	}

	public void setEntity(BaseNodeEntity entity) {
		this.entity = entity;
	}

	public String getBaseMessage() {
		return baseMessage;
	}

	public void setBaseMessage(String baseMessage) {
		this.baseMessage = baseMessage;
	}

}
