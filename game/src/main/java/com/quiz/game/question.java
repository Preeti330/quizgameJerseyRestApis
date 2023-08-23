package com.quiz.game;
import java.util.ArrayDeque;
import java.util.List;

public class question {
	public String question,answer,marks;
//	public ArrayDeque mcq;
	public int status,id;
	public List<Multiplechoice> mc;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int i) {
		this.status = i;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMultipleChoices(List<Multiplechoice> mc) {
		// TODO Auto-generated method stub
		this.mc=mc;	
	}
	
	public List<Multiplechoice> getMultipleChoices(List<Multiplechoice> mc) {
		// TODO Auto-generated method stub
		return mc;
		
	}


}
