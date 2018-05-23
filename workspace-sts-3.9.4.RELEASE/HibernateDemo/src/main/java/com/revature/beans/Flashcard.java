package com.revature.beans;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="FLASHCARD")
public class Flashcard implements Serializable {

	public Flashcard(int id, String question, String answer, Category c) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.category = c;
	}
	
	public Flashcard(String question, String answer, Category c) {
		super();
		this.question = question;
		this.answer = answer;
		this.category = c;
	}
	
	public Flashcard() {
		super();
	}

	private static final long serialVersionUID = 1897553647260794654L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="flashcardSequence")
	@SequenceGenerator(allocationSize=1, name="flashcardSequence", sequenceName="SQ_FLASHCARD_PK")
	@Column(name="FLASHCARD_ID")
	private int id;
	
	@Column(name="QUESTION")
	private String question;

	@Column(name="ANSWER")
	private String answer;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CATEGORY_ID")
	private Category category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category c) {
		this.category = c;
	}

	@Override
	public String toString() {
		return "Flashcard [id=" + id + ", question=" + question + ", answer=" + answer + "]";
	}
	
	
}
