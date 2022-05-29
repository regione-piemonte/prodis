/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.util.serialization;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

/**
 * Utility class for JSON handling
 */
public final class JsonUtility {

	private static final String RFC2822_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
	private static final Jsonb JSONB;

	static {
		JsonbConfig config = new JsonbConfig()
//				.withNullValues(Boolean.TRUE)
				.withDateFormat(RFC2822_FORMAT, Locale.ITALY)
				.withEncoding(StandardCharsets.UTF_8.name());

		JSONB = JsonbBuilder.newBuilder()
			.withConfig(config)
			.build();
	}

	/** Private constructor */
	private JsonUtility() {
		// Prevent instantiation
	}

	/**
	 * Serializes an object to JSON
	 * @param obj the object to be serialized
	 * @return the JSON
	 */
	public static String serialize(Object obj) {
		return obj == null ? null : JSONB.toJson(obj);
	}

	/**
	 * Serializes an object to JSON
	 * @param json the JSON to deserialize
	 * @param type the type to deserialize to
	 * @param <T> the type
	 * @return the JSON
	 */
	public static <T> T deserialize(String json, Class<T> type) {
		return JSONB.fromJson(json, type);
	}
	
	/**
	 * Parses the throwable
	 * @param t the throwable to parse
	 * @param debugMode if the debug mode is active
	 * @return the JSON object equivalent
	 */
	public static JsonObject parseThrowable(Throwable t, boolean debugMode) {
		JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(new HashMap<>());
		
		JsonObjectBuilder job = jsonBuilderFactory.createObjectBuilder();
		job.add("class", t.getClass().getName());
		job.add("message", t.getMessage());
		if(debugMode) {
			JsonArrayBuilder jab = jsonBuilderFactory.createArrayBuilder();
			for(StackTraceElement ste : t.getStackTrace()) {
				jab.add(
					jsonBuilderFactory.createObjectBuilder()
						.add("className", ste.getClassName())
						.add("fileName", ste.getFileName())
						.add("lineNumber", ste.getLineNumber())
						.add("methodName", ste.getMethodName())
						.build()
				);
			}
			job.add("stackTrace", jab.build());

			if(t.getCause() != null && t.getCause() != t) {
				job.add("cause", parseThrowable(t.getCause(), debugMode));
			}
		}

		return job.build();
	}

}
