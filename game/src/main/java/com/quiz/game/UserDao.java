package com.quiz.game;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class UserDao {
	User u1 = new User();
	Db db = new Db();
	ResponseFormat rf = new ResponseFormat();
	List<User> userslist = new ArrayList<>();

//	public UserDao() {
//		ArrayList user = new ArrayList<>();
//		
//		user.add(Arrays.asList());
//	}

	List<User> list = Arrays.asList();

	public Response logIn(User user) {

		// userslist.add(user);
		// String mobile_no = u1.getMobile_no();
		int user_id=0;
		String pwd = user.password;
		String mobile_no = user.mobile_no;
		String username = user.username;
		String strPattern = "^[0-9]{10}$";
		Connection con = db.getConnection();
		try {
			// validate mobile no
			boolean matches = mobile_no.matches(strPattern);
			if (matches) {

				// Statement st = con.createStatement();
//			String sql="SELECT * FROM users AS u WHERE u.mobile_no='"+mobile_no+"' AND u.password='"+pwd+"'";//mobile_no AND u.password='pwd'";
				String sql = "SELECT * FROM users AS u WHERE u.mobile_no=?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, mobile_no);
				// st.setString(2, pwd);
				ResultSet excute = st.executeQuery();
				// boolean execute = st.execute(sql);

				if (excute.next()) {
					user_id=excute.getInt(1);
					LocalDate date = LocalDate.now();
					String sql2 = "INSERT INTO users_login_history(user_id,login_at,created_date,updated_date,status) VALUES(?,?,?,?,?)";
					PreparedStatement st2 = con.prepareStatement(sql2);
					st2.setInt(1, excute.getInt(1));
					st2.setDate(2, new java.sql.Date(System.currentTimeMillis()));
					st2.setDate(3, new java.sql.Date(System.currentTimeMillis()));
					st2.setDate(4, new java.sql.Date(System.currentTimeMillis()));
					st2.setInt(5, 1);
					int et = st2.executeUpdate();

					//return rf.getResponse(200, "login sucess");
					JSONObject obj = new JSONObject();
//					obj.put("success", true);
			        obj.put("user_id",user_id );
					obj.put("messge", "Login Sucess");
				return 	rf.getResponse(200, obj);

				} else {

					String sql3 = "INSERT INTO users(mobile_no,created_date,updated_date,status) VALUES(?,?,?,?)";
					PreparedStatement userst = con.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
					userst.setString(1, mobile_no);
					LocalDate date = LocalDate.now();
					userst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
					userst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
					userst.setInt(4, 1);
					int et = userst.executeUpdate();

					// if(et == 1) {
					ResultSet getid = userst.getGeneratedKeys();
					int id = 0;
					if (getid.next()) {
						// System.out.println(getid.getString(1));
						id = getid.getInt(1);
					}

					// login history
					String sql4 = "INSERT INTO users_login_history(user_id,login_at,created_date,updated_date,status) VALUES(?,?,?,?,?)";
					PreparedStatement loginst = con.prepareStatement(sql4);
					loginst.setInt(1, id);
					loginst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
					loginst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
					loginst.setDate(4, new java.sql.Date(System.currentTimeMillis()));
					loginst.setInt(5, 1);
					int et2 = loginst.executeUpdate();

					// }
					JSONObject obj = new JSONObject();
//					obj.put("success", true);
			        obj.put("mobile_no",mobile_no );
					obj.put("messge", "Login Sucess");
				return 	rf.getResponse(200, obj);
					//return rf.getResponse(200, "login sucess");
				}
			} else {
				return rf.getResponse(411, "invalid mobile number");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;

	}

	public Response getTerms() {
		List<Termsandconditions> tandclist = new ArrayList<>();

		String sql = "SELECT id,status,terms FROM termsandconditions WHERE id=1";
		Connection con = db.getConnection();
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Termsandconditions tandc = new Termsandconditions();
			while (rs.next()) {
				int id = rs.getInt(1);
				int status = rs.getInt(2);
				String tnc = rs.getString(3);
				tandc.setId(id);
				tandc.setStatus(status);
				tandc.setTerms(tnc);
				tandclist.add(tandc);
			}
			con.close();
			return rf.getResponseForList(200, tandclist);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Response getQuestions() {

		Connection con = db.getConnection();
		try {
			Statement st = con.createStatement();
			List<question> question = new ArrayList<>();

			String str = "SELECT q.id,q.question,q.status FROM question AS q  WHERE status=1";
			ResultSet rs = st.executeQuery(str);
			int i = 1;
			while (rs.next()) {
				question q = new question();
				// int id = rs.getInt(1);
				q.setId(rs.getInt(1));
				q.setId(rs.getInt(1));
				q.setQuestion(rs.getString(2));
				q.setStatus(rs.getInt(3));

				String sql = "SELECT m.id,m.question_id,m.choices FROM multiplechoice AS m WHERE status=1 AND question_id=?";
				PreparedStatement multipleChoiceStatement = con.prepareStatement(sql);
				multipleChoiceStatement.setInt(1, q.getId());
				ResultSet res2 = multipleChoiceStatement.executeQuery();

				List<Multiplechoice> mc = new ArrayList<>();
				while (res2.next()) {
					Multiplechoice mcq = new Multiplechoice();
					mcq.setId(res2.getInt(1));
					mcq.setQuestion_id(res2.getInt(2));
					mcq.setChoices(res2.getString(3));
					mc.add(mcq);
				}
				q.setMultipleChoices(mc);

				question.add(q);

			}

			Status status = null;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", true);
			JSONArray ja = new JSONArray();
			ja.put(question);
			jsonObject.put("data", question.toArray());
//		   System.out.println(jsonObject.toString());
			jsonObject.put("status", 200);

			return Response.status(Status.OK).entity(question).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Response submitAnswers() {
//	int user_id=answer.user_id;

		// String JsonString = answer.answer.toString();

//	JSONArray jsonArray = new JSONArray(JsonString);
//	JSONObject jsonObject = new JSONObject(answer);
		// JSONParser jsonParser = new JSONParser();
//	System.out.println(jsonObject.getInt("user_id"));
		// JSONArray answerArray = jsonObject.getJSONArray("answerlist");
		// JSONArray answerArray = jsonObject.
//	System.out.println(jsonObject.getJSONArray("answerlist"));

//	for(){
//		System.out.println(answers.length);
//		
//	}

		return null;
	}

	public Response submitAns(Submitanswers ans) {
		int user_id = ans.user_id;
		JSONArray jArray = new JSONArray(ans.answer);
		JSONObject jsonObject = jArray.getJSONObject(0);
		System.out.println(jArray.length());
		int correctAns = 0;
		int wrongAns = 0;
		int correctAns2=0;
		Connection con = db.getConnection();
		for (int j = 0; j < jArray.length(); j++) {
			// Get courseName of each JSONObject inside your courses sub array
			int qid = jArray.getJSONObject(j).getInt("qid");
			int mid = jArray.getJSONObject(j).getInt("mid");

			// for every question check answer fro question table
						String sql = "SELECT * FROM question WHERE id=" + qid + " and answer_multiplechoice_id=" + mid;
						try {
							Statement st = con.createStatement();
							ResultSet ex = st.executeQuery(sql);
							
						if (ex.next()) {
							System.out.println(" valid");
							String sql3 = "SELECT * FROM submitanswers WHERE user_id=" + user_id + " AND question_id=" + qid
									+ " AND multiplechoice_id=" + mid;
							Statement st2 = con.createStatement();
							
						    ResultSet rs = st2.executeQuery(sql3);
							if (rs.next()) {
								System.out.println("already in DB");
							return	rf.getResponse(422, "already in DB");
		
							} else {

								String sql2 = "INSERT INTO submitanswers(user_id,question_id,multiplechoice_id,submit_time) VALUES(?,?,?,?)";
								PreparedStatement pst = con.prepareStatement(sql2);
								pst.setInt(1, user_id);
								pst.setInt(2, qid);
								pst.setInt(3, mid);
								pst.setDate(4, new java.sql.Date(System.currentTimeMillis()));
								pst.executeUpdate();

								correctAns = correctAns + 1;
								pst.close();
								st2.close();
							}
						} else {
							System.out.println("not valid");
							wrongAns = wrongAns + 1;
							correctAns2 = correctAns2 + 1;
						}
						st.close();
						
					
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
		}
		JSONObject obj = new JSONObject();
//		obj.put("success", true);
        obj.put("correct", correctAns);
		obj.put("wrong", wrongAns);
	return 	rf.getResponse(200, obj);
		
		//System.out.println(response);
	}

}
