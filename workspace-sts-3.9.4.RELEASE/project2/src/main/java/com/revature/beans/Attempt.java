package com.revature.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATTEMPT")
public class Attempt {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attemptSequence")
	@SequenceGenerator(allocationSize = 1, name = "attemptSequence", sequenceName = "SQ_ATTEMPT_PK")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUIZ_ID")
	private Quiz quiz;

	@ManyToMany
	@JoinTable(name = "ATTEMPT_ANSWER")
	private Set<Answer> answers;

	public Attempt(int id, Account account, Quiz quiz, Set<Answer> answers) {
		super();
		this.id = id;
		this.account = account;
		this.quiz = quiz;
		this.answers = answers;
	}

	public Attempt(Account account, Quiz quiz, Set<Answer> answers) {
		super();
		this.account = account;
		this.quiz = quiz;
		this.answers = answers;
	}

	public Attempt() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Attempt [id=" + id + ", account=" + account + ", quiz=" + quiz + ", answers=" + answers + "]";
	}
	
	
}
