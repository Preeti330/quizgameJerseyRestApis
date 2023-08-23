package com.quiz.game;

import java.sql.Connection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
@Path("user")
public class UserResource {

	Db db=new Db();
   UserDao dao=new UserDao();
   ResponseFormat rf=new ResponseFormat();
	
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public String getConnection() {
		Connection connection = db.getConnection();
		
		return "sucess";
	}
   
   @POST
   @Path("login")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response login(User user) 
   {
		    Response response = dao.logIn(user); 
		    return  response;
		
	}
   
   
   @GET
   @Path("gettandc")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response getTandC() {
	   
	   Response response = dao.getTerms();
	return response;
	   
   }
   
   
   @GET
   @Path("questions")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response getQuestions() {
	   Response response = dao.getQuestions();
	   return response;
   }
   
   
   @POST
   @Path("submitans")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response submitAnswers() {
	   Response response = dao.submitAnswers();
	   return response;
   }
   
   @POST
   @Path("submitanswers")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response submitAns(Submitanswers ans) {
	   Response submitAns = dao.submitAns(ans);
	   return submitAns;
   }
}
