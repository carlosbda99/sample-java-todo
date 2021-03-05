package com.appjava.todo;

import java.util.List;

import org.hibernate.Session;

import com.appjava.tarefa.Tarefa;
import com.appjava.util.HibernateUtil;

public class Conexao {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();
		
		List<Tarefa> tarefas = session.createNativeQuery("select * from tarefa").list();
	
		System.out.println(tarefas);
		for (Tarefa t: tarefas) {
			System.out.println(t.getTitulo());
		} 
		
		session.close();
	}
}
