package org.studenzone.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.studenzone.models.UserDetails;

@Controller
public class Login {
	@Autowired
	private SessionFactory sessionFactory;
	UserDetails user;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		ModelAndView indexpage=new ModelAndView("index");
		String mob= requestParams.get("mobno");
		String name = requestParams.get("name");
		String dob = requestParams.get("dob");
		String clgname = requestParams.get("clgname");
		String clgloc = requestParams.get("clgloc");
		String course = requestParams.get("course");
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();

	       Query queryResult = session.createQuery("from UserDetails");
	       java.util.List allUsers;
	       String pa,na;
	       
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	        UserDetails user = (UserDetails) allUsers.get(i);
	        pa=user.getMobno();
	        System.out.println(pa);
		      
	        if(mob.equals(pa)){
	         f=1;
	         break; 
	         }
	        }
	         
	           if(f!=1){
	    UserDetails user= new UserDetails();  
	      
	      user.setMobno(mob);
	      user.setName(name);
	      user.setClgname(clgname);
	      user.setClgloc(clgloc);
	      user.setCourse(course);
	      user.setDob(dob);
	      session.save(user);
	       session.getTransaction().commit();
	   
	       session.close(); 
	       user=null;
	       System.out.println(name);
	      
	       

	           } 
	           else
	           {   System.out.println("duplicate");
	          
	           }
	            
		
		return indexpage;
	}
}
