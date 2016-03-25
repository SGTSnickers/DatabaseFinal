import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class SimpleLdapAuthentication{
   public static void main(String[] args){

      String username = ""; //zrc6825
      String password = "";
      String base = "ou=People,dc=rit,dc=edu";
      String ldapURL = "ldap://ldap.rit.edu";
   
      // Setup environment for authenticating
      Hashtable<String, String> environment = new Hashtable<String, String>();
      
      environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
      environment.put(Context.PROVIDER_URL, ldapURL);
      environment.put(Context.REFERRAL, "ignore");
      environment.put(Context.SECURITY_AUTHENTICATION, "simple");
      environment.put(Context.SECURITY_PRINCIPAL, "uid="+username+","+ base);
      environment.put(Context.SECURITY_CREDENTIALS, password);
   
      try{
         DirContext authContext = new InitialDirContext(environment);
         System.out.println("Hello I authenticated");
         // user is authenticated
      }catch (AuthenticationException ex){
         // Authentication failed
         System.out.println("Error: " + ex.getMessage());
      }catch (NamingException ex){
         ex.printStackTrace();
      }//end try/catch
   }//end main
}//end class