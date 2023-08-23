package com.quiz.game;

public class Multiplechoice {
	int id,question_id,status;
	public String choices;
	public void setId(int id) {
		this.id = id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setChoices(String choices) {
		this.choices = choices;
	}
	public int getId() {
		return id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public int getStatus() {
		return status;
	}
	public String getChoices() {
		return choices;
	}
	public Object toArray() {
		// TODO Auto-generated method stub
		return null;
	}


}
