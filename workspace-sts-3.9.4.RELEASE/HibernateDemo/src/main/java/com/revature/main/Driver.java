package com.revature.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Animal;
import com.revature.beans.Bat;
import com.revature.beans.Category;
import com.revature.beans.Cave;
import com.revature.beans.Flashcard;
import com.revature.dao.CategoryDao;
import com.revature.dao.CategoryDaoImpl;
import com.revature.dao.FlashcardDao;
import com.revature.dao.FlashcardDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		
		//System.out.println(System.getenv("HIB_DB_USERNAME"));
		
		//FlashcardDao fd = new FlashcardDaoImpl(); 
	
		init();
		
		//getVsLoad(fd);
		
		//saveVsPersist(fd);
		
		//updateVsMerge();
		
		//animalInit();
	
	}
	
	public static void animalInit() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		Cave c = (Cave) s.get(Cave.class, 2);
		
		Animal a = new Animal("Mystery", c);
		Bat bat1 = new Bat("Ernie", c, 42);
		
		s.persist(a);
		s.persist(bat1);
		
		tx.commit();
		s.close();
	}
	
	public static void updateVsMerge() {
		
		//f is in transient state
		Flashcard f = new Flashcard("Where do you stand if you're cold?","In the corner, it's 90 degrees.",new Category(2,"meteorology"));
		Session s1 = HibernateUtil.getSession();
		Transaction tx1 = s1.beginTransaction();
		s1.persist(f);
		tx1.commit();
		s1.close();
		
		//f is now in detached state
		Session s2 = HibernateUtil.getSession();
		Transaction tx = s2.beginTransaction();
		try {
			
			Flashcard f2 = (Flashcard) s2.get(Flashcard.class, f.getId());
			
			//f2 is persistent and has same peristence identity (same id) 
			//as detached object f
			f.setAnswer("outside, it's Florida"); //f is still detached
			
			//throws NonUniqueObjectException because update attempts to 
			//reattach f to s2
			//s2.update(f);
			
			//merge is fine
			s2.merge(f);
			
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		s2.close();
		
	}
	
	public static void saveVsPersist(FlashcardDao fd) {
		
		//System.out.println(fd.addFlashcard(new Flashcard("What version of Java do we use?","Java 8", new Category(1,""))));
		System.out.println(fd.persistFlashcard(new Flashcard("What is the answer to life, the universe, and everything?","Java 8", new Category(1,""))));
		
	}
	
	public static void getVsLoad(FlashcardDao fd) {
		
		Flashcard f = fd.getFlashcardById(2);
		if (f != null) {
			System.out.println("flashcard 2 exists");
			//f is detached 
			System.out.println(f.getQuestion());
			System.out.println(f.getCategory()); //works iff category is eagerly fetched 
													//OR if category is lazily fetched but accessed in a session
		}
		
	}
	
	public static void init() {
		
		/*
		Session s = HibernateUtil.getSession();
		System.out.println(s.isOpen());
		s.close();
		System.out.println(s.isOpen()); 
		HibernateUtil.closeSessionFactory();
		*/
		
		
		CategoryDao cd = new CategoryDaoImpl();
		Category c1 = new Category("coding");
		c1.setId(cd.addCategory(c1));
		Category c2 = new Category("meteorology");
		c2.setId(cd.addCategory(c2));
		
		FlashcardDao fd = new FlashcardDaoImpl();
		Flashcard f1 = new Flashcard("What is Java?","The best language",c1);
		Flashcard f2 = new Flashcard("Is it raining?","not right now... maybe",c2);
		
		fd.addFlashcard(f1);
		fd.addFlashcard(f2);
		
		List<Flashcard> fcl = fd.getFlashcards();
		for (Flashcard f : fcl) {
			System.out.println(f);
		}
		
		HibernateUtil.closeSessionFactory();
		
	}

}
