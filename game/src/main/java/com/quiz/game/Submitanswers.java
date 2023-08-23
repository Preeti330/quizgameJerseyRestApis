package com.quiz.game;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Submitanswers {
	
	public int id,user_id,question_id,multiplechoice_id,questionse,status;
	public Date time_start,submit_time;
	public int qid;
	public int mid;
	public List<Submitanswers> answer;
	String answerlist;
	public List<Submitanswers> scoure;
	public int correctAns,wrongAns;
	
	

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setScoure(List<Submitanswers> scoure) {
		this.scoure = scoure;
	}

	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}

	public void setWrongAns(int wrongAns) {
		this.wrongAns = wrongAns;
	}

	public int getUser_id() {
		return user_id;
	}

	public List<Submitanswers> getScoure() {
		return scoure;
	}

	public int getCorrectAns() {
		return correctAns;
	}

	public int getWrongAns() {
		return wrongAns;
	}

	public String getAnswerlist() {
		return answerlist;
	}

	public void setAnswerlist(String answerlist) {
		this.answerlist = answerlist;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getQid() {
		return qid;
	}

	public int getMid() {
		return mid;
	}

	public Submitanswers() {
		
	}
	
	public Submitanswers(int id, int user_id, int question_id, int multiplechoice_id, int status, Date time_start,
			Date submit_time,Submitanswers ans,int qid, int mid) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.question_id = question_id;
		this.multiplechoice_id = multiplechoice_id;
		this.status = status;
		this.time_start = time_start;
		this.submit_time = submit_time;
		this.qid = qid;
		this.mid = mid;
		answer = new ArrayList<Submitanswers>();
		answer.add(ans);
		
	}
	public void setId(int id) {
		this.id = id;
	}
//	public void setUser_id(int user_id) {
//		this.user_id = user_id;
//	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public void setMultiplechoice_id(int multiplechoice_id) {
		this.multiplechoice_id = multiplechoice_id;
	}
	public void setQuestionse(int questionse) {
		this.questionse = questionse;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
	public int getId() {
		return id;
	}
//	public int getUser_id() {
//		return user_id;
//	}
	public int getQuestion_id() {
		return question_id;
	}
	public int getMultiplechoice_id() {
		return multiplechoice_id;
	}
	public int getQuestionse() {
		return questionse;
	}
	public int getStatus() {
		return status;
	}
	public Date getTime_start() {
		return time_start;
	}
	public Date getSubmit_time() {
		return submit_time;
	}
	

}
