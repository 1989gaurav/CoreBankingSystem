

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: CreditIssue
 *
 */
 public class CreditIssue extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CreditIssue() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Index myIndex = new Index();
		ServletContext ctx=this.getServletContext();
		String login =ctx.getAttribute("loginsession").toString();
		DataSource dsource = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Connection conn = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		
		if(!login.equals("T"))
			pw.println(myIndex.section1+myIndex.section2+myIndex.section3+myIndex.section4);
		else
		{
			String per="";
			String date="",time="";
			String query="";
			try
			 {
			 InitialContext context = new InitialContext ();
			 dsource = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			 conn = dsource.getConnection();
			 stmt=conn.createStatement();
			 stmt1=conn.createStatement();
			 per=ctx.getAttribute("UserID").toString();
			 ctx.setAttribute("loginsession", "T");
			 ctx.setAttribute("UserID",per);
			 query="select * from logininfo where logininfo.user='"+per+"'";
			 rset1=stmt.executeQuery(query);
			 
			 while(rset1.next())
			 {
				date=rset1.getString(2);
				time=rset1.getString(3);
				 break;
			 }
			 
			float balance=0;
			char[] perarray = new char[per.length()];
			perarray=per.toCharArray();
			if(perarray[0]=='c')
			{
				//customer
				
					pw.println("<html><title>Error Page!</title><body><font color=\"red\" size=3><B>Error! This page is not meant for you</font></B></body></html>");

				
				
			}
			else if(perarray[0]=='e')
			{
				//employee
				pw.println(myIndex.section1+myIndex.employee);
				String uid = request.getParameter("user");
				String amnt = request.getParameter("bill");
				Date dt = new Date();
				String day="",month="",year="",hrs="",mins="",sec="";
				 if(dt.getDate()<10)
					 day+="0"+dt.getDate();
				 else
					 day+=dt.getDate();
				 int m=dt.getMonth()+1;
				 
				 if(m<10)
					 month+="0"+m;
				 else
					 month+=m;
				 int y=dt.getYear()+1900;
				 year+=y;
				
				String CompDate = day+"."+month+"."+year ;
				
				query = "select * from account where account.accno="+uid;
				rset=stmt.executeQuery(query);
				boolean enter=false;
				
				while(rset.next())
				{
				enter=true;
				String userID = rset.getString(1);
				
				query = "select * from cnt where cnt.name='TransID'";
				rset = stmt.executeQuery(query);
				rset.next();
				int TID = rset.getInt(2) +1;
				query = "update cnt set cnt.cnt1="+TID+" where cnt.name='TransID'";
				stmt.executeUpdate(query);
				
				query = "insert into credit values('"+userID+"',"+amnt+",'"+CompDate+"','By Cash','"+TID+"','"+per+"')";
				stmt.executeUpdate(query);
				
				query = "update account set account.balance=account.balance+"+amnt+" where account.user='"+userID+"'";
				stmt.executeUpdate(query);
				

				pw.println("<td valign=\"top\" width=580><font color=\"red\">Amount Credit to Account<br>Transaction ID: "+TID+"</font></td>");
				break;
				}
				if(!enter)
					pw.println("<td valign=\"top\" width=580>Account Not Found</td>");
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			}
			else if(perarray[0]=='a')
			{
				//admin
				pw.println(myIndex.section1+myIndex.admin);
				String uid = request.getParameter("user");
				String amnt = request.getParameter("bill");
				Date dt = new Date();
				String day="",month="",year="",hrs="",mins="",sec="";
				 if(dt.getDate()<10)
					 day+="0"+dt.getDate();
				 else
					 day+=dt.getDate();
				 int m=dt.getMonth()+1;
				 
				 if(m<10)
					 month+="0"+m;
				 else
					 month+=m;
				 int y=dt.getYear()+1900;
				 year+=y;
				
				String CompDate = day+"."+month+"."+year ;
				
				query = "select * from account where account.accno="+uid;
				rset=stmt.executeQuery(query);
				boolean enter=false;
				while(rset.next())
				{
					enter=true;
				String userID = rset.getString(1);
				
				query = "select * from cnt where cnt.name='TransID'";
				rset = stmt.executeQuery(query);
				rset.next();
				int TID = rset.getInt(2) +1;
				query = "update cnt set cnt.cnt1="+TID+" where cnt.name='TransID'";
				stmt.executeUpdate(query);
				
				query = "insert into credit values('"+userID+"',"+amnt+",'"+CompDate+"','By Cash','"+TID+"','"+per+"')";
				stmt.executeUpdate(query);
				
				query = "update account set account.balance=account.balance+"+amnt+" where account.user='"+userID+"'";
				stmt.executeUpdate(query);
				
				pw.println("<td valign=\"top\" width=580><font color=\"red\">Amount Credit to Account<br>Transaction ID: "+TID+"</font></td>");	
				break;
				}
				if(!enter)
					pw.println("<td valign=\"top\" width=580>Account Not Found</td>");
				
				pw.println(myIndex.section6+"Administrator"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			}
			 }
			catch(Exception ex)
			{
				
			}
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Index myIndex = new Index();
		ServletContext ctx=this.getServletContext();
		String login =ctx.getAttribute("loginsession").toString();
		DataSource dsource = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Connection conn = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		
		if(!login.equals("T"))
			pw.println(myIndex.section1+myIndex.section2+myIndex.section3+myIndex.section4);
		else
		{
			String per="";
			String date="",time="";
			String query="";
			try
			 {
			 InitialContext context = new InitialContext ();
			 dsource = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			 conn = dsource.getConnection();
			 stmt=conn.createStatement();
			 stmt1=conn.createStatement();
			 per=ctx.getAttribute("UserID").toString();
			 ctx.setAttribute("loginsession", "T");
			 ctx.setAttribute("UserID",per);
			 query="select * from logininfo where logininfo.user='"+per+"'";
			 rset1=stmt.executeQuery(query);
			 
			 while(rset1.next())
			 {
				date=rset1.getString(2);
				time=rset1.getString(3);
				 break;
			 }
			 
			float balance=0;
			char[] perarray = new char[per.length()];
			perarray=per.toCharArray();
			if(perarray[0]=='c')
			{
				//customer
				
					pw.println("<html><title>Error Page!</title><body><font color=\"red\" size=3><B>Error! This page is not meant for you</font></B></body></html>");

				
				
			}
			else if(perarray[0]=='e')
			{
				//employee
				pw.println(myIndex.section1+myIndex.employee);
				String uid = request.getParameter("user");
				String amnt = request.getParameter("bill");
				Date dt = new Date();
				String day="",month="",year="",hrs="",mins="",sec="";
				 if(dt.getDate()<10)
					 day+="0"+dt.getDate();
				 else
					 day+=dt.getDate();
				 int m=dt.getMonth()+1;
				 
				 if(m<10)
					 month+="0"+m;
				 else
					 month+=m;
				 int y=dt.getYear()+1900;
				 year+=y;
				
				String CompDate = day+"."+month+"."+year ;
				
				query = "select * from account where account.accno="+uid;
				rset=stmt.executeQuery(query);
				boolean enter=false;
				
				while(rset.next())
				{
				enter=true;
				String userID = rset.getString(1);
				
				query = "select * from cnt where cnt.name='TransID'";
				rset = stmt.executeQuery(query);
				rset.next();
				int TID = rset.getInt(2) +1;
				query = "update cnt set cnt.cnt1="+TID+" where cnt.name='TransID'";
				stmt.executeUpdate(query);
				
				query = "insert into credit values('"+userID+"',"+amnt+",'"+CompDate+"','By Cash','"+TID+"','"+per+"')";
				stmt.executeUpdate(query);
				
				query = "update account set account.balance=account.balance+"+amnt+" where account.user='"+userID+"'";
				stmt.executeUpdate(query);
				

				pw.println("<td valign=\"top\" width=580><font color=\"red\">Amount Credit to Account<br>Transaction ID: "+TID+"</font></td>");
				break;
				}
				if(!enter)
					pw.println("<td valign=\"top\" width=580>Account Not Found</td>");
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			}
			else if(perarray[0]=='a')
			{
				//admin
				pw.println(myIndex.section1+myIndex.admin);
				String uid = request.getParameter("user");
				String amnt = request.getParameter("bill");
				Date dt = new Date();
				String day="",month="",year="",hrs="",mins="",sec="";
				 if(dt.getDate()<10)
					 day+="0"+dt.getDate();
				 else
					 day+=dt.getDate();
				 int m=dt.getMonth()+1;
				 
				 if(m<10)
					 month+="0"+m;
				 else
					 month+=m;
				 int y=dt.getYear()+1900;
				 year+=y;
				
				String CompDate = day+"."+month+"."+year ;
				
				query = "select * from account where account.accno="+uid;
				rset=stmt.executeQuery(query);
				boolean enter=false;
				while(rset.next())
				{
					enter=true;
				String userID = rset.getString(1);
				
				query = "select * from cnt where cnt.name='TransID'";
				rset = stmt.executeQuery(query);
				rset.next();
				int TID = rset.getInt(2) +1;
				query = "update cnt set cnt.cnt1="+TID+" where cnt.name='TransID'";
				stmt.executeUpdate(query);
				
				query = "insert into credit values('"+userID+"',"+amnt+",'"+CompDate+"','By Cash','"+TID+"','"+per+"')";
				stmt.executeUpdate(query);
				
				query = "update account set account.balance=account.balance+"+amnt+" where account.user='"+userID+"'";
				stmt.executeUpdate(query);
				
				pw.println("<td valign=\"top\" width=580><font color=\"red\">Amount Credit to Account<br>Transaction ID: "+TID+"</font></td>");	
				break;
				}
				if(!enter)
					pw.println("<td valign=\"top\" width=580>Account Not Found</td>");
				
				pw.println(myIndex.section6+"Administrator"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			}
			 }
			catch(Exception ex)
			{
				
			}
		}
	}   	  	    
}