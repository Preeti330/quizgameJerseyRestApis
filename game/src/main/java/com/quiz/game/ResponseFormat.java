package com.quiz.game;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ResponseFormat implements ContainerResponseFilter  {
	
	
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		  MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		//headers.add("Access-Control-Allow-Origin", "https://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
	}

	public Response getResponse(int statusCode, String msg) {

		Status status = null;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);

		JSONArray ja = new JSONArray();
		ja.put("sucsess");
		jsonObject.put("data", msg);

		if (statusCode == 200) {
			jsonObject.put("status", statusCode);
			status = Status.OK;
		} else if (statusCode == 422) {
			jsonObject.put("status", statusCode);
			status = Status.UNAVAILABLE_FOR_LEGAL_REASONS;
		} else if (statusCode == 401) {
			jsonObject.put("status", statusCode);
			status = Status.UNAUTHORIZED;
		}

		return Response.status(status)
				.entity(jsonObject.toString())
			   .build();

	}

	public Response getResponseForList(int statusCode, List<Termsandconditions> tandclist) {

		Status status = null;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);

		JSONArray ja = new JSONArray();
		ja.put("sucsess");
		jsonObject.put("data", tandclist);

		if (statusCode == 200) {
			jsonObject.put("status", statusCode);
			status = Status.OK;
		} else if (statusCode == 422) {
			jsonObject.put("status", statusCode);
			status = Status.UNAVAILABLE_FOR_LEGAL_REASONS;
		} else if (statusCode == 401) {
			jsonObject.put("status", statusCode);
			status = Status.UNAUTHORIZED;
		}

		return Response.status(status).entity(jsonObject.toString()).build();

	}

	public Response getResponseForQuestionList(int statusCode, List<question> list) {

		Status status = null;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);

		JSONArray ja = new JSONArray();
		ja.put("sucsess");
		jsonObject.put("data", list);

		if (statusCode == 200) {
			jsonObject.put("status", statusCode);
			status = Status.OK;
		} else if (statusCode == 422) {
			jsonObject.put("status", statusCode);
			status = Status.UNAVAILABLE_FOR_LEGAL_REASONS;
		} else if (statusCode == 401) {
			jsonObject.put("status", statusCode);
			status = Status.UNAUTHORIZED;
		}

		return Response.status(status).entity(jsonObject.toString()).build();

	}

	public Response getResponse(int statusCode, JSONObject obj) {
		
		Status status = null;
		JSONObject json = new JSONObject();
		json.put("success", true);

		JSONArray ja = new JSONArray();
		ja.put("sucsess");
		json.put("data", obj);

		if (statusCode == 200) {
			json.put("status", statusCode);
			status = Status.OK;
		} else if (statusCode == 422) {
			json.put("status", statusCode);
			status = Status.UNAVAILABLE_FOR_LEGAL_REASONS;
		} else if (statusCode == 401) {
			json.put("status", statusCode);
			status = Status.UNAUTHORIZED;
		}	
		
		return Response.status(status).entity(json.toString()).build();
	}
	

}
