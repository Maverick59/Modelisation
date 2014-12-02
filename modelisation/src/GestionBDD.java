import java.sql.*;
import java.util.ArrayList;
 
public class GestionBDD {
       
        public static void main(String[] args){
                System.out.println("\n");
                System.out.println(rechercheGTS("").toString());
        }
       
        public static ArrayList<String> rechercheGTS(String hashtags){
                String[] s;
                ArrayList<String> res=new ArrayList<String>();
                s=hashtags.split("[ ]");
                Connection con = null;
                try{
                        Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
                        Statement stmt=con.createStatement();
                        String query;
                        if(hashtags.length()!=0){
                        	query = "select chemin from modeles where nom in(select nom from correspondances where tag in(";
                        	for(int i=0; i<s.length; i++){
                        		query+="\""+s[i]+"\"";
                        		if(i<=s.length-2){
                        			query+=", ";
                                }
                        	}
                        	query+="))";
                        }else{
                        	query="select chemin from modeles";
                        }
                        ResultSet rs = stmt.executeQuery(query);
                        int i=0;
                        while(rs.next()){
                                res.add(rs.getString("chemin"));
                                i++;
                        }
                }catch(Exception e){
                        System.out.println("Erreur "+e);
                }finally{
                        try{
                                con.close();
                        }catch(Exception e){
                               
                        }
                }
                return res;
        }
       
        public static String recherchePNG(String gts){
                String[] s;
                Connection con = null;
                try{
                        Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
                        Statement stmt=con.createStatement();
                        String query = "select chemin_screen from modeles where chemin=\""+gts+"\"";
                        ResultSet rs = stmt.executeQuery(query);
                        return rs.getString("chemin_screen");
                }catch(Exception e){
                        System.out.println("Erreur "+e);
                }finally{
                        try{
                        	con.close();
                        }catch(Exception e){
                               
                        }
                }
				return null;
        }
       
}