package com.vill;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class FormHandlerServlet
 */
public class FormHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String enteredValue;
	    // gets all the selected options from the client browser
	    
	    // gets the enteredValue fields value
	    enteredValue = request.getParameter("enteredValue");
	    String geturl = "http://finance.yahoo.com/webservice/v1/symbols/"+enteredValue+"/quote?format=json";
	    JSONObject jo = null;
	    String s =null;
	    String name =null;
	   
		try {
			jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL(geturl))).nextValue();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			s = jo.getJSONObject("list").getJSONArray("resources").getJSONObject(0).getJSONObject("resource").getJSONObject("fields").getString("price");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			name = jo.getJSONObject("list").getJSONArray("resources").getJSONObject(0).getJSONObject("resource").getJSONObject("fields").getString("name");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    response.setContentType("text/html");
	    PrintWriter printWriter;
	    try
	    {
	      // get a printwriter from the HttpServletResponse objects ref.
	      printWriter = response.getWriter();
	      // return on the HttpServletResponse objects ref. requested values
	      printWriter.println("<p>");
	      printWriter.print("Name of the stock is ");
	      printWriter.print(name);
	      printWriter.print("<br>");
	      printWriter.print("Value of the stock is");
	      printWriter.print(s);
	      printWriter.print("</p>");
	      
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }

	}

}
