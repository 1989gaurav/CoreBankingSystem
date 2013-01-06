

import java.io.IOException;
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
 * Servlet implementation class for Servlet: Stand
 *
 */
 public class Stand extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Stand() {
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
				String abc="<td valign=top width=580><SCRIPT LANGUAGE=JavaScript>function validate(){   var myForm = document.form1;  if (myForm.acnum.value ==\"\")   {      alert(\"Enter your Account Number\");    	return false;     } if (myForm.amt.value ==\"\")   {      alert(\"Select Debit Amount\");    	return false;     }if (myForm.dd.value ==\"\")   {      alert(\"Enter Date of Operation\");    	return false;     }if (myForm.period.value ==\"Select One\")   {      alert(\"Choose the appropriate period\");    	return false;     }if (myForm.place.value ==\"Select One\")   {      alert(\"Choose the appropriate place of transfer\");    	return false;     }if (myForm.dest.value ==\"\")   {      alert(\"Enter the destination credentials\");    	return false;     }}</SCRIPT>";
				pw.println(abc+"<br><br><form action=\"IssueStand\" method=\"POST\" name=form1><font size=\"3\">Issue Standing Instruction</font>          <br><br><table>	<td width=200 valign=\"top\">	<b>Account Number:</b><br><br><b>Amount to Debit:</b><br><br>	<b>Date of Operation:</b><br><br>	<b>Period of Repetition (in months):</b><br><br>	<b>Place of Transfer:</b><br><br>	<b>Destination Credentials:</b></td>	<td valign=\"top\"><input type=\"text\" name=acnum><br><br><input type=\"text\" name=amt><br><br>	<input type=\"text\" name=dd><br><br><select name=period>	<option value=\"Select One\">Select One</option>	<option value=\"1\">1</option>	<option value=\"2\">2</option>	<option value=\"3\">3</option><option value=\"6\">6</option><option value=\"12\">12</option></select><br><br><select name=place><option value=\"Select One\">Select One</option><option value=\"Loan\">Loan</option><option value=\"Account\">Account</option></select><br><br><input type=\"text\" name=dest><br><br><input type=reset value=\"Reset\" name=btnd2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"Submit\" value=\"Issue\" name=btnd1 onclick=\"return validate()\">	</td></table> </form></td>");
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			}
			else if(perarray[0]=='a')
			{
				//admin
				pw.println(myIndex.section1+myIndex.admin);
				String abc="<td valign=top width=580><SCRIPT LANGUAGE=JavaScript>function validate(){   var myForm = document.form1;  if (myForm.acnum.value ==\"\")   {      alert(\"Enter your Account Number\");    	return false;     } if (myForm.amt.value ==\"\")   {      alert(\"Select Debit Amount\");    	return false;     }if (myForm.dd.value ==\"\")   {      alert(\"Enter Date of Operation\");    	return false;     }if (myForm.period.value ==\"Select One\")   {      alert(\"Choose the appropriate period\");    	return false;     }if (myForm.place.value ==\"Select One\")   {      alert(\"Choose the appropriate place of transfer\");    	return false;     }if (myForm.dest.value ==\"\")   {      alert(\"Enter the destination credentials\");    	return false;     }}</SCRIPT>";
				pw.println(abc+"<br><br><form action=\"IssueStand\" method=\"POST\" name=form1><font size=\"3\">Issue Standing Instruction</font>          <br><br><table>	<td width=200 valign=\"top\">	<b>Account Number:</b><br><br><b>Amount to Debit:</b><br><br>	<b>Date of Operation:</b><br><br>	<b>Period of Repetition (in months):</b><br><br>	<b>Place of Transfer:</b><br><br>	<b>Destination Credentials:</b></td>	<td valign=\"top\"><input type=\"text\" name=acnum><br><br><input type=\"text\" name=amt><br><br>	<input type=\"text\" name=dd><br><br><select name=period>	<option value=\"Select One\">Select One</option>	<option value=\"1\">1</option>	<option value=\"2\">2</option>	<option value=\"3\">3</option><option value=\"6\">6</option><option value=\"12\">12</option></select><br><br><select name=place><option value=\"Select One\">Select One</option><option value=\"Loan\">Loan</option><option value=\"Account\">Account</option></select><br><br><input type=\"text\" name=dest><br><br><input type=reset value=\"Reset\" name=btnd2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"Submit\" value=\"Issue\" name=btnd1 onclick=\"return validate()\">	</td></table> </form></td>");
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
				String abc="<td valign=top width=580><SCRIPT LANGUAGE=JavaScript>function validate(){   var myForm = document.form1;  if (myForm.acnum.value ==\"\")   {      alert(\"Enter your Account Number\");    	return false;     } if (myForm.amt.value ==\"\")   {      alert(\"Select Debit Amount\");    	return false;     }if (myForm.dd.value ==\"\")   {      alert(\"Enter Date of Operation\");    	return false;     }if (myForm.period.value ==\"Select One\")   {      alert(\"Choose the appropriate period\");    	return false;     }if (myForm.place.value ==\"Select One\")   {      alert(\"Choose the appropriate place of transfer\");    	return false;     }if (myForm.dest.value ==\"\")   {      alert(\"Enter the destination credentials\");    	return false;     }}</SCRIPT>";
				pw.println(abc+"<br><br><form action=\"IssueStand\" method=\"POST\" name=form1><font size=\"3\">Issue Standing Instruction</font>          <br><br><table>	<td width=200 valign=\"top\">	<b>Account Number:</b><br><br><b>Amount to Debit:</b><br><br>	<b>Date of Operation:</b><br><br>	<b>Period of Repetition (in months):</b><br><br>	<b>Place of Transfer:</b><br><br>	<b>Destination Credentials:</b></td>	<td valign=\"top\"><input type=\"text\" name=acnum><br><br><input type=\"text\" name=amt><br><br>	<input type=\"text\" name=dd><br><br><select name=period>	<option value=\"Select One\">Select One</option>	<option value=\"1\">1</option>	<option value=\"2\">2</option>	<option value=\"3\">3</option><option value=\"6\">6</option><option value=\"12\">12</option></select><br><br><select name=place><option value=\"Select One\">Select One</option><option value=\"Loan\">Loan</option><option value=\"Account\">Account</option></select><br><br><input type=\"text\" name=dest><br><br><input type=reset value=\"Reset\" name=btnd2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"Submit\" value=\"Issue\" name=btnd1 onclick=\"return validate()\">	</td></table> </form></td>");
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			}
			else if(perarray[0]=='a')
			{
				//admin
				pw.println(myIndex.section1+myIndex.admin);
				String abc="<td valign=top width=580><SCRIPT LANGUAGE=JavaScript>function validate(){   var myForm = document.form1;  if (myForm.acnum.value ==\"\")   {      alert(\"Enter your Account Number\");    	return false;     } if (myForm.amt.value ==\"\")   {      alert(\"Select Debit Amount\");    	return false;     }if (myForm.dd.value ==\"\")   {      alert(\"Enter Date of Operation\");    	return false;     }if (myForm.period.value ==\"Select One\")   {      alert(\"Choose the appropriate period\");    	return false;     }if (myForm.place.value ==\"Select One\")   {      alert(\"Choose the appropriate place of transfer\");    	return false;     }if (myForm.dest.value ==\"\")   {      alert(\"Enter the destination credentials\");    	return false;     }}</SCRIPT>";
				pw.println(abc+"<br><br><form action=\"IssueStand\" method=\"POST\" name=form1><font size=\"3\">Issue Standing Instruction</font>          <br><br><table>	<td width=200 valign=\"top\">	<b>Account Number:</b><br><br><b>Amount to Debit:</b><br><br>	<b>Date of Operation:</b><br><br>	<b>Period of Repetition (in months):</b><br><br>	<b>Place of Transfer:</b><br><br>	<b>Destination Credentials:</b></td>	<td valign=\"top\"><input type=\"text\" name=acnum><br><br><input type=\"text\" name=amt><br><br>	<input type=\"text\" name=dd><br><br><select name=period>	<option value=\"Select One\">Select One</option>	<option value=\"1\">1</option>	<option value=\"2\">2</option>	<option value=\"3\">3</option><option value=\"6\">6</option><option value=\"12\">12</option></select><br><br><select name=place><option value=\"Select One\">Select One</option><option value=\"Loan\">Loan</option><option value=\"Account\">Account</option></select><br><br><input type=\"text\" name=dest><br><br><input type=reset value=\"Reset\" name=btnd2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"Submit\" value=\"Issue\" name=btnd1 onclick=\"return validate()\">	</td></table> </form></td>");
				pw.println(myIndex.section6+"Administrator"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			
			}
			 }
			catch(Exception ex)
			{
				
			}
		}
	}   	  	    
}