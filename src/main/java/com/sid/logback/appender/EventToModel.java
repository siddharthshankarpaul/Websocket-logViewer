package com.sid.logback.appender;

import java.util.Date;

import ch.qos.logback.classic.spi.ILoggingEvent;

public final class EventToModel {

	private ILoggingEvent eventVO;

	public EventToModel(ILoggingEvent event) {
		eventVO = event;
	}

	public String toJsonString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (eventVO.getThreadName() != null) {
			builder.append("\"threadName\":");
			builder.append("\"");
			builder.append(eventVO.getThreadName());
			builder.append("\"");
			builder.append(",");
		}
		if (eventVO.getLoggerName() != null) {
			builder.append("\"logger\":");
			builder.append("\"");
			builder.append(eventVO.getLoggerName());
			builder.append("\"");
			builder.append(",");
		}
		if (eventVO.getLevel() != null) {
			builder.append("\"level\":");
			builder.append("\"");
			builder.append(eventVO.getLevel());
			builder.append("\"");
			builder.append(", ");
		}
		if (eventVO.getMessage() != null) {
			builder.append("\"message\":");
			builder.append("\"");
			builder.append(eventVO.getFormattedMessage());
			builder.append("\"");
			builder.append(", ");
		}
		builder.append("\"timeStamp\":");
		builder.append("\"");
		builder.append(new Date(eventVO.getTimeStamp()));
		builder.append("\"");
		builder.append("}");
		return builder.toString();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (eventVO.getThreadName() != null) {
			builder.append("threadName=");
			builder.append(eventVO.getThreadName());
			builder.append(", ");
		}
		if (eventVO.getLevel() != null) {
			builder.append("level=");
			builder.append(eventVO.getLevel());
			builder.append(", ");
		}
		if (eventVO.getMessage() != null) {
			builder.append("message=");
			builder.append(eventVO.getFormattedMessage());
			builder.append(", ");
		}
		builder.append("timeStamp=");
		builder.append(new Date(eventVO.getTimeStamp()));
		return builder.toString();
	}
}
