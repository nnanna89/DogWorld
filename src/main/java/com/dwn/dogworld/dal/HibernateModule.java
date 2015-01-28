package com.dwn.dogworld.dal;

import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Match;

public class HibernateModule {
	public static void bind(ServiceBinder binder){
		binder.bind(CrudServiceDAO.class, HibernateCrudServiceDAO.class);
	}
	
	@Match("*DAO")
	public static void adviseTransactions(HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver){
		advisor.addTransactionCommitAdvice(receiver);
	} 
}
