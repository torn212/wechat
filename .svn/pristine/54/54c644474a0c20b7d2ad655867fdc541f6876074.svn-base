package com.torn.farmer.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

/**
 * @author likun
 *
 */
public class JSONUtil {

	/**
	 * json 转为 object object类型为type定义的 例如：Type type = new TypeToken<Map
	 * <String,TreeVo>>(){}.getType(); 则返回map对象
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static Object jsonToObj(String json, Type type) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	/**
	 * 对象转为json
	 * 
	 * @param obj
	 * @param serializeNullValue
	 * @return
	 */
	public static String beanToJson(Object obj, boolean serializeNullValue) {
		if (obj == null) {
			return null;
		}
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(obj);
		return json;
	}

	/**
	 * 带日期的bean转为json 日期格式化
	 * 
	 * @param obj
	 * @param serializeNullValue
	 * @return
	 */
	public static String beanWithDateToJson(Object obj, boolean serializeNullValue) {
		if (obj == null) {
			return null;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create()
				: gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		String json = gson.toJson(obj);
		return json;
	}

	public static <T> T jsonToBean(String json, Class<T> clazz) {
		if (json == null || "".equals(json.trim())) {
			return null;
		}
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);
		return jsonToBean(jsonReader, clazz);
	}

	private static <T> T jsonToBean(JsonReader json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		Gson gson = new Gson();
		T bean = gson.fromJson(json, clazz);
		return (T) bean;
	}

	public static <T> T jsonWithDateToBean(String json, Class<T> clazz) {
		if (json == null || "".equals(json.trim())) {
			return null;
		}
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);
		return jsonWithDateToBean(jsonReader, clazz);
	}

	private static <T> T jsonWithDateToBean(JsonReader json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateDeserializerUtils()).setDateFormat(DateFormat.LONG).create();
		T b = gson.fromJson(json, clazz);
		return b;
	}

	public static <T> String listToJson(List<T> list, boolean serializeNullValue) {
		if (list == null) {
			return null;
		}
		Gson gson = serializeNullValue ? new GsonBuilder().disableHtmlEscaping().serializeNulls().create() : new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(list);
		return json;
	}

	public static <T> String listWithDateToJson(List<T> list, boolean serializeNullValue) {
		if (list == null) {
			return null;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create()
				: gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		String json = gson.toJson(list);
		return json;
	}

	public static <T> List<T> jsonToList(String json, Class<T> clazz) {
		if (json == null || "".equals(json.trim())) {
			return null;
		}
		StringReader strReader = new StringReader(json);
		List<T> list = null;
		try {
			list = readForList(strReader, false, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> List<T> jsonWithDateToList(String json, Class<T> clazz) {
		if (json == null || "".equals(json.trim())) {
			return null;
		}

		StringReader strReader = new StringReader(json);
		List<T> list = null;
		try {
			list = readForList(strReader, true, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static <T> List<T> readForList(Reader reader, boolean hasDate, Class<T> clazz) throws IOException {
		JsonReader jsonReader = new JsonReader(reader);
		List<T> objs = new ArrayList<T>();
		jsonReader.beginArray();
		while (jsonReader.hasNext()) {
			T obj = null;
			if (hasDate) {
				obj = jsonWithDateToBean(jsonReader, clazz);
			} else {
				obj = jsonToBean(jsonReader, clazz);
			}
			if (obj != null)
				objs.add(obj);
		}
		jsonReader.endArray();
		jsonReader.close();
		return objs;
	}

	public static <T> String setToJson(Set<T> set, boolean serializeNullValue) {
		if (set == null) {
			return null;
		}
		// set -> Json
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(set);
		return json;
	}

	public static <T> String setWithDateToJson(Set<T> set, boolean serializeNullValue) {
		if (set == null) {
			return null;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create()
				: gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		String json = gson.toJson(set);
		return json;
	}

	public static <T> Set<T> jsonToSet(String json, Class<T> clazz) {
		if (json == null || "".equals(json.trim())) {
			return null;
		}
		StringReader strReader = new StringReader(json);
		Set<T> set = null;
		try {
			set = readForSet(strReader, false, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	public static <T> Set<T> jsonWithDateToSet(String json, Class<T> clazz) {
		if (json == null || "".equals(json.trim())) {
			return null;
		}

		StringReader strReader = new StringReader(json);
		Set<T> set = null;
		try {
			set = readForSet(strReader, true, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	private static <T> Set<T> readForSet(Reader reader, boolean hasDate, Class<T> clazz) throws IOException {
		JsonReader jsonReader = new JsonReader(reader);
		Set<T> objs = new HashSet<T>();
		jsonReader.beginArray();
		while (jsonReader.hasNext()) {
			T obj = null;
			if (hasDate) {
				obj = jsonWithDateToBean(jsonReader, clazz);
			} else {
				obj = jsonToBean(jsonReader, clazz);
			}
			if (obj != null)
				objs.add(obj);
		}
		jsonReader.endArray();
		jsonReader.close();
		return objs;
	}

	public static <T> String mapToJson(Map<String, T> map, boolean serializeNullValue) {
		if (map == null) {
			return null;
		}
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(map);
		return json;
	}

	public static <T> String mapWithDateToJson(Map<String, T> map, boolean serializeNullValue) {
		if (map == null) {
			return null;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create()
				: gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		String json = gson.toJson(map);
		return json;
	}

	static class DateSerializerUtils implements JsonSerializer<java.util.Date> {
		public JsonElement serialize(Date date, Type type, JsonSerializationContext content) {
			return new JsonPrimitive(date.getTime());
		}

	}

	static class DateDeserializerUtils implements JsonDeserializer<java.util.Date> {
		public java.util.Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
			return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
		}
	}

	public static void main(String[] args) {
		// TreeVo vo = new TreeVo();
		// vo.setId("id");
		// vo.setText("test");
		// System.out.println(JSONUtil.beanToJson(vo, true));
		// System.out.println(JSONUtil.beanToJson(vo, false));
		// System.out.println(JSONUtil.beanWithDateToJson(vo, false));

		// String jsonString =
		// "{'id':'id','text':'test','isChecked':true,'depth':0,'hasChildren':false}";
		// System.out.println(JSONUtil.jsonToBean(jsonString, TreeVo.class));

		// Map<String, TreeVo> map = new HashMap<String, TreeVo>();
		// map.put("123", vo);
		// System.out.println(JSONUtil.mapToJson(map, false));

		// String mapstring =
		// "{'123':{'id':'id','text':'test','isChecked':true,'depth':0,'hasChildren':false}}";
		// Type type = new TypeToken<Map<String,TreeVo>>(){}.getType();
		// Gson gson = new Gson();
		// Map fromJson = gson.fromJson(mapstring, type);
		// Object x = fromJson.get("123");
		// System.out.println(x);

		// Map<String,TreeVo> map = new HashMap<String, TreeVo>();
		// Map<String,TreeVo> jsonToBean = JSONUtil.jsonToBean(mapstring,
		// map.getClass());
		// System.out.println(jsonToBean.get("123"));

	}
}
