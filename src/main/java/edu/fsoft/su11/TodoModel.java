package edu.fsoft.su11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TodoModel implements Comparator<TodoModel>{
    int id;
    String name;
    
  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    @Override
    public boolean equals(Object obj) {
      TodoModel todo = (TodoModel) obj;
      if (id > todo.id)
        return false;
      return true;
    }
 
    @Override
    public int compare(TodoModel o1, TodoModel o2) {
      // TODO Auto-generated method stub
      return (o1.id - o2.id);
    }
    public static void main(String[] args) {
     
    }
}
