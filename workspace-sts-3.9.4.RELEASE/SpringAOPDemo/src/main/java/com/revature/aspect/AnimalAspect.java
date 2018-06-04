package com.revature.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.service.AnimalService;
import com.revature.service.PersonService;

@Aspect
@Component
public class AnimalAspect {

	
	@AfterReturning("execution(* wake*(..))")
	public void afterWakeAnimal(JoinPoint jp) {
		System.out.println("that was not a good choice");
	}
	
	@Around("execution(* *CatchesYou(..))")
	public void afterCatchesYou(ProceedingJoinPoint pjp) throws Throwable {
		int personSpeed = PersonService.getSpeed();
		AnimalService as = (AnimalService ) pjp.getTarget();
		if (personSpeed <= as.getSpeed()) {
			System.out.println("too slow");
			pjp.proceed();
			if (!as.isFull()) {
				as.setFull(true);
				System.out.println("you became lunch");
			} else {
				System.out.println("you got lucky");
			}
		} else {
			System.out.println("you got away!");
		}
			
	}
	
}
