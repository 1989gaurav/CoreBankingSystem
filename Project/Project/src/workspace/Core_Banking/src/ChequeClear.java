

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
 * Servlet implementation class for Servlet: ChequeClear
 *
 */
 public class ChequeClear extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ChequeClear() {
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
		Connection conn = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		
		if(!login.equals("T"))
			pw.println(myIndex.section1+myIndex.section2+myIndex.section3+myIndex.section4);
		else
		{
			//Logged In
			String per="";
			String date="",time="";
			String query="";
			try
			 {
			 InitialContext context = new InitialContext ();
			 dsource = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			 conn = dsource.getConnection();
			 stmt=conn.createStatement();
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
			 
			 
			char[] perarray = new char[per.length()];
			perarray=per.toCharArray();
			if(perarray[0]=='c')
			{
				
					pw.println("<html><title>Error Page!</title><body><font color=\"red\" size=3><B>Error! This page is not meant for you</font></B></body></html>");

				
			}
			else if(perarray[0]=='e')
			{
				pw.println(myIndex.section1+myIndex.employee);
				pw.println("<td valign=\"top\" width=580><script language=Javascript>function validate(){	var myform = document.form1;		if(myform.bank.value==\"\")	{		alert(\"Bank is the required Field\");		return false;	}	if(myform.Chequeno.value==\"\")	{		alert(\"Cheque Number is the required Field\");		return false;	}	if(myform.ACno.value==\"\")	{		alert(\"Account Number is the required Field\");		return false;	}	if(myform.Operation.value==\"Select One\")	{		alert(\"Select any one Operation\");		return false;	}		if(myform.Amount.value==\"\")	{		alert(\"Amount is the required Field\");		return false;	}	}</script>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"6\">Cheque Clearance</font>          <br><br><form action=\"ClearVerify\" method=\"POST\" name=form1><table>	<td width=200>	<b>Bank:</b><br><br>	<b>Cheque No.:</b><br><br>	<b>Account No.:</b><br><br>	<b>Operation:</b><br><br>	<b>Amount:</b><br><br>		</td>	<td>	<input type=\"text\" name=bank maxlength=20><br><br><input type=\"text\" name=Chequeno maxlength=20><br><br>	<input type=\"text\" name=ACno maxlength=20><br><br>	<select name=Operation>	<option value=\"Select One\">Select One</option>	<option value=\"Credit\">Credit</option><option value=\"Debit\">Debit</option>	</select><br><br>	<input type=\"text\" name=Amount maxlength=20><br><br>	</td></table><br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=Submit value=\"Submit\" onclick=\"return validate()\"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value=\"Reset\"></form></td>	");
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
				
			}
			else if(perarray[0]=='a')
			{
				pw.println(myIndex.section1+myIndex.admin);
				pw.println("<td valign=\"top\" width=580><script language=Javascript>function validate(){	var myform = document.form1;		if(myform.bank.value==\"\")	{		alert(\"Bank is the required Field\");		return false;	}	if(myform.Chequeno.value==\"\")	{		alert(\"Cheque Number is the required Field\");		return false;	}	if(myform.ACno.value==\"\")	{		alert(\"Account Number is the required Field\");		return false;	}	if(myform.Operation.value==\"Select One\")	{		alert(\"Select any one Operation\");		return false;	}		if(myform.Amount.value==\"\")	{		alert(\"Amount is the required Field\");		return false;	}	}</script>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"6\">Cheque Clearance</font>          <br><br><form action=\"ClearVerify\" method=\"POST\" name=form1><table>	<td width=200>	<b>Bank:</b><br><br>	<b>Cheque No.:</b><br><br>	<b>Account No.:</b><br><br>	<b>Operation:</b><br><br>	<b>Amount:</b><br><br>		</td>	<td>	<input type=\"text\" name=bank maxlength=20><br><br><input type=\"text\" name=Chequeno maxlength=20><br><br>	<input type=\"text\" name=ACno maxlength=20><br><br>	<select name=Operation>	<option value=\"Select One\">Select One</option>	<option value=\"Credit\">Credit</option><option value=\"Debit\">Debit</option>	</select><br><br>	<input type=\"text\" name=Amount maxlength=20><br><br>	</td></table><br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=Submit value=\"Submit\" onclick=\"return validate()\"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value=\"Reset\"></form></td>	");
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
		Connection conn = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		
		if(!login.equals("T"))
			pw.println(myIndex.section1+myIndex.section2+myIndex.section3+myIndex.section4);
		else
		{
			//Logged In
			String per="";
			String date="",time="";
			String query="";
			try
			 {
			 InitialContext context = new InitialContext ();
			 dsource = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			 conn = dsource.getConnection();
			 stmt=conn.createStatement();
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
			 
			 
			char[] perarray = new char[per.length()];
			perarray=per.toCharArray();
			if(perarray[0]=='c')
			{
				
					pw.println("<html><title>Error Page!</title><body><font color=\"red\" size=3><B>Error! This page is not meant for you</font></B></body></html>");

				
			}
			else if(perarray[0]=='e')
			{
				pw.println(myIndex.section1+myIndex.employee);
				pw.println("<td valign=\"top\" width=580><script language=Javascript>function validate(){	var myform = document.form1;		if(myform.bank.value==\"\")	{		alert(\"Bank is the required Field\");		return false;	}	if(myform.Chequeno.value==\"\")	{		alert(\"Cheque Number is the required Field\");		return false;	}	if(myform.ACno.value==\"\")	{		alert(\"Account Number is the required Field\");		return false;	}	if(myform.Operation.value==\"Select One\")	{		alert(\"Select any one Operation\");		return false;	}		if(myform.Amount.value==\"\")	{		alert(\"Amount is the required Field\");		return false;	}	}</script>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"6\">Cheque Clearance</font>          <br><br><form action=\"ClearVerify\" method=\"POST\" name=form1><table>	<td width=200>	<b>Bank:</b><br><br>	<b>Cheque No.:</b><br><br>	<b>Account No.:</b><br><br>	<b>Operation:</b><br><br>	<b>Amount:</b><br><br>		</td>	<td>	<input type=\"text\" name=bank maxlength=20><br><br><input type=\"text\" name=Chequeno maxlength=20><br><br>	<input type=\"text\" name=ACno maxlength=20><br><br>	<select name=Operation>	<option value=\"Select One\">Select One</option>	<option value=\"Credit\">Credit</option><option value=\"Debit\">Debit</option>	</select><br><br>	<input type=\"text\" name=Amount maxlength=20><br><br>	</td></table><br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=Submit value=\"Submit\" onclick=\"return validate()\"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value=\"Reset\"></form></td>	");
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
				
			}
			else if(perarray[0]=='a')
			{
				pw.println(myIndex.section1+myIndex.admin);
				pw.println("<td valign=\"top\" width=580><script language=Javascript>function validate(){	var myform = document.form1;		if(myform.bank.value==\"\")	{		alert(\"Bank is the required Field\");		return false;	}	if(myform.Chequeno.value==\"\")	{		alert(\"Cheque Number is the required Field\");		return false;	}	if(myform.ACno.value==\"\")	{		alert(\"Account Number is the required Field\");		return false;	}	if(myform.Operation.value==\"Select One\")	{		alert(\"Select any one Operation\");		return false;	}		if(myform.Amount.value==\"\")	{		alert(\"Amount is the required Field\");		return false;	}	}</script>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"6\">Cheque Clearance</font>          <br><br><form action=\"ClearVerify\" method=\"POST\" name=form1><table>	<td width=200>	<b>Bank:</b><br><br>	<b>Cheque No.:</b><br><br>	<b>Account No.:</b><br><br>	<b>Operation:</b><br><br>	<b>Amount:</b><br><br>		</td>	<td>	<input type=\"text\" name=bank maxlength=20><br><br><input type=\"text\" name=Chequeno maxlength=20><br><br>	<input type=\"text\" name=ACno maxlength=20><br><br>	<select name=Operation>	<option value=\"Select One\">Select One</option>	<option value=\"Credit\">Credit</option><option value=\"Debit\">Debit</option>	</select><br><br>	<input type=\"text\" name=Amount maxlength=20><br><br>	</td></table><br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=Submit value=\"Submit\" onclick=\"return validate()\"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value=\"Reset\"></form></td>	");
				pw.println(myIndex.section6+"Administrator"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
				
			}
			 }
			catch(Exception ex)
			{
				
			}
		}
	}   	  	    
}