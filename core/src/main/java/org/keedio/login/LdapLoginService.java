package org.keedio.login;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapLoginService {
  
  public static String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
  public static String MY_HOST = "ldap://ipa01.jcfernandez.cediant.es:389";
  public static String MY_SEARCHBASE = "dc=jcfernandez,dc=cediant,dc=es";
  public static String MGR_DN = "uid=admin,cn=users,cn=accounts,dc=jcfernandez,dc=cediant,dc=es";
  
  public static boolean doLogin(String user, String password) {
    try{
      String aux = "uid=" + user + ",cn=users,cn=accounts,dc=jcfernandez,dc=cediant,dc=es";
      Hashtable env = new Hashtable();
      env.put(Context.INITIAL_CONTEXT_FACTORY,INITCTX);
      env.put(Context.PROVIDER_URL,MY_HOST);
      env.put(Context.SECURITY_AUTHENTICATION,"simple");
      env.put(Context.SECURITY_PRINCIPAL,aux);
      env.put(Context.SECURITY_CREDENTIALS,password);
      DirContext ctx = new InitialDirContext(env);
      
      return true;
/*
      SearchControls constraints = new SearchControls();
      constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
      //performs the actual search
      //We give it a searchbase, a filter and the contraints containing the scope
      //of the search
      NamingEnumeration results = ctx.search(MY_SEARCHBASE,user,constraints);
      //now stop through the search results
      while(results != null && results.hasMore()){
        SearchResult sr = (SearchResult)results.next();
        String dn = sr.getName();
        System.out.println("Distinguished name is "+dn);
        Attributes attrs = sr.getAttributes();
        for(NamingEnumeration ne = attrs.getAll();ne.hasMoreElements();ne.next())  {
          Attribute attr = (Attribute) ne.next();
          String attrID = attr.getID();
          System.out.println(attrID+" :");
          for(Enumeration vals = attr.getAll();vals.hasMoreElements();vals.nextElement()) {
            System.out.println("\t"+vals.nextElement());
          }

        }
        System.out.println("\n");
      }*/
    }catch(Exception ex){
      return false;
    }

  }
  
  public static void main(String []args) {
    LdapLoginService.doLogin("rolmo", "12341234");
  }
  
}
