package io.confluent.rest.examples.helloworld.dao;

import io.confluent.rest.examples.helloworld.entity.HelloResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class HelloWorldDao {

  //public EntityManager em;
  //public EntityManagerFactory emf;
  private Cluster cluster;

  public HelloWorldDao(String persistenceUnitName) {
    Cluster cluster = Cluster.builder().addContactPoint("ambari4").build();
  }
  
  public HelloResponse save(HelloResponse response) {
    Session session = cluster.connect();
    
    ResultSet rs = session.execute("insert into hola(nombre, apellido, dni, email) values ('" + response.getNombre()
        + "','" + response.getApellido() + "','" + response.getDNI() + "','" 
        + response.getEmail() + "')");
    
    return response;
  }
  
  public List<HelloResponse> listAll() {
    Session session = cluster.connect();
    
    ResultSet rs = session.execute("select * from hola");
    List<HelloResponse> list = new ArrayList<HelloResponse>();
    
    Iterator<Row>it = rs.iterator();
    while(it.hasNext()) {
      Row row = it.next();
      HelloResponse hr = new HelloResponse(row.getString(0), row.getString(1), row.getString(2), row.getString(3));
    
      list.add(hr);
    }
    
    return list;
  }
  
  public List<HelloResponse> findByName(String name) {
    Session session = cluster.connect();
    
    ResultSet rs = session.execute("select * from hola where nombre=" + name);
    List<HelloResponse> list = new ArrayList<HelloResponse>();
    
    Iterator<Row>it = rs.iterator();
    while(it.hasNext()) {
      Row row = it.next();
      HelloResponse hr = new HelloResponse(row.getString(0), row.getString(1), row.getString(2), row.getString(3));
    
      list.add(hr);
    }
    
    return list;
  }  

  public List<HelloResponse> findByNameAndSurname(String name, String surname) {
    Session session = cluster.connect();
    
    ResultSet rs = session.execute("select * from hola");
    List<HelloResponse> list = new ArrayList<HelloResponse>();
    
    Iterator<Row>it = rs.iterator();
    while(it.hasNext()) {
      Row row = it.next();
      HelloResponse hr = new HelloResponse(row.getString(0), row.getString(1), row.getString(2), row.getString(3));
    
      list.add(hr);
    }
    
    return list;
  }

  public List<HelloResponse> findByDNI(String dni) {
    Session session = cluster.connect();
    
    ResultSet rs = session.execute("select * from hola where dni=" + dni);
    List<HelloResponse> list = new ArrayList<HelloResponse>();
    
    Iterator<Row>it = rs.iterator();
    while(it.hasNext()) {
      Row row = it.next();
      HelloResponse hr = new HelloResponse(row.getString(0), row.getString(1), row.getString(2), row.getString(3));
    
      list.add(hr);
    }
    
    return list;
  }  

}
