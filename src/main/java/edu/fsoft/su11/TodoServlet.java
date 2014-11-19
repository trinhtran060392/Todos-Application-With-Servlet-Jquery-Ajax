package edu.fsoft.su11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodoServlet extends HttpServlet {

 /**
  * Class TodoServlet to handle request from client
  */
  private static final long serialVersionUID = 1L;
  
  // index variable to set key for map and li tag
  private int index = 0;
  
  // Map to save todo list
  private Map<Integer, TodoModel> listModel = new HashMap<Integer, TodoModel>();

  // Handle request with method is Get
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    if (listModel.size() >= 1) {

      Iterator<TodoModel> i = listModel.values().iterator();
      
      //Send json data to client
      writer.print("[");
      
      while (i.hasNext()) {
        
        TodoModel todo = i.next();
        
        resp.setContentType("text/json");
        
        StringBuilder sb = new StringBuilder("{");
        
        sb.append("\"id\" : ").append(todo.getId()).append(",");
        sb.append("\"name\" :").append("\"").append(todo.getName()).append("\"");
        sb.append("}");
        if (i.hasNext()) sb.append(",");
        writer.print(sb.toString());
        
      }
      
      writer.print("]");
    }

  }

  // handle request with Post method from client
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    // get id from check box input by parameter name
    String id = req.getParameter("id");
    // get id of clear all item button
   // String id_all = req.getParameter("id_all");
    String[] id_all = req.getParameterValues("id_all[]");
    // get id of text edit
    String id_edit = req.getParameter("id_edit");
    
    // get value after edit into edit input text
    String value_edit = req.getParameter("value_edit");

     // check delete item
    if (id != null) {
     // char keyID = id.charAt(id.length() - 1);
      
      listModel.remove(Integer.parseInt(id));
      resp.getOutputStream().write(id.getBytes());
      // check if request is edition
    } else if (checkEdit(id_edit, value_edit)) {
      
      Iterator<TodoModel> iter = listModel.values().iterator();
      
      while (iter.hasNext()) {
        TodoModel todo = iter.next();
        if (("todo_" + todo.getId()).equals(id_edit)) {
          todo.setName(value_edit);
          break;
        }
      }
    }

      // check if request is delete all item
    //else if (id_all != null && id_all.equals("clear-completed")) {
   // listModel.clear();

  //  index = 0;
 // } 
      else if (id_all != null && id_all.length >=1) {
        for(int i = 0; i < id_all.length; i ++){
          resp.getOutputStream().write(id_all[i].getBytes());
          listModel.remove(Integer.parseInt(id_all[i]));
        }
      }
     
    // add new todo
    else {
      resp.setContentType("text/plain");
      resp.setCharacterEncoding("UTF-8");
      index++;
      TodoModel todo = new TodoModel();

      String todoName = req.getParameter("name");
      //send id from server to client
      resp.getOutputStream().write(Integer.toString(index).getBytes());
      todo.setId(index);
      todo.setName(todoName);
      listModel.put(index, todo);
    }
  }
  
  // check if request is edit content
  public boolean checkEdit(String id_edit, String value_edit) {

    if (id_edit != null) {
      return true;
    } else
      return false;

  }

}
