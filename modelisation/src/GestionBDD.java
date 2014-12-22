import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionBDD {

	public static void main(String[] args) throws IOException {
		/*File file = new File("model");
		for (File f : file.listFiles()) {
			System.out.println(f.getPath());
			GestionBDD.insert(f.getPath());	
		}*/
	}

	public static ArrayList<String> rechercheGTS(String hashtags) {
		String[] s;
		ArrayList<String> res = new ArrayList<String>();
		s = hashtags.split("[ ]");
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			String query;
			if (hashtags.length() != 0) {
				query = "select chemin from modeles where chemin in(select chemin from modeles where nom in(select nom from correspondances where tag like ";
				for (int i = 0; i < s.length; i++) {
					if (!s[i].equals("")) {
						query += "'%" + s[i] + "%' ";
						if (i <= s.length - 2) {
							query += "or tag like ";
						}
					}
				}
				query += ") ";
				query += "or nom like ";
				for (int i = 0; i < s.length; i++) {
					if (!s[i].equals("")) {
						query += "'%" + s[i] + "%' ";
						if (i <= s.length - 2) {
							query += "or nom like ";
						}
					}
				}
				query += ")";
				//ajout nombre de points limites (par exemple utilisateur tape <500)
				for(int i=0; i<s.length ; i++){
					if(!s[i].equals("") && (s[i].charAt(0)=='<' || s[i].charAt(0)=='>' || s[i].charAt(0)=='=')){
						if(Integer.parseInt(s[i].substring(1, s[i].length()))>0){
							query+=" and nb_points";
							query +=s[i].charAt(0)+s[i].substring(1, s[i].length());
						}
					}
				}
			} else {
				query = "select chemin from modeles";
			}
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				res.add(rs.getString("chemin"));
			}
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
		return res;
	}

	// renvoie le chemin vers l'image, en connaissant le chemin du gts
	public static String recherchePNG(String gts) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select chemin_screen from modeles where chemin='"+gts+"'");
			if(rs.next()!=false){
				return rs.getString("chemin_screen");
			}
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void insert(String gts) {
		Connection con = null;
		try {
			String nom = gts.replace(".gts", "");
			nom = nom.split("/")[nom.split("/").length - 1];
			Model3D model = Charger.chargerModel(gts);
			String png = "img/" + nom + ".png";
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			String query = "INSERT INTO modeles  values('" + nom + "','" + gts + "','" + png + "','', '" + model.points.size() + "','" + model.segments.size() + "','" + model.faces.size()
				+ "','"+0xffffff+"')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
	}
	
	public static void insertHashTag(String nom, String hashtag){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			ResultSet rs;
			ResultSet rs2;
			ResultSet rs3;
			rs=stmt.executeQuery("select nom from modeles where nom='"+nom+"'");
			if(rs.next()!=false && rs.getString("nom").equals(nom)){
				rs2=stmt.executeQuery("select tag from hashTags where tag='"+hashtag+"'");
				if(rs2.next()!=false && rs2.getString("tag").toString().equals(hashtag)){
					System.out.println("ce tag existe deja et ne sera donc pas créé");
				}else{
					System.out.println("ce tag va etre créé");
					stmt.executeUpdate("insert into hashTags values('"+hashtag+"')");
				}
				rs3=stmt.executeQuery("select nom from correspondances where nom='"+nom+"' and tag='"+hashtag+"'");
				if(rs3.next()!=false && rs3.getString("nom").equals(nom)){
					System.out.println("cette correspondace existe deja");
				}else{
					System.out.println("insertion dan la table de correspondances");
					stmt.executeUpdate("insert into correspondances values('"+hashtag+"', '"+nom+"')");
				}
			}else{
				System.out.println("ce modele n'existe pas");
			}
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
	}
	
	public static void setColor(String nom, int color){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where nom=\"" + nom + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()!=false && rs.getString("nom").equals(nom)){
				stmt.executeUpdate("update modeles set color='"+color+"' where nom='"+nom+"'");
			}
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
	}
	
	public static Color getColor(String nom){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where nom=\"" + nom + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()!=false && rs.getString("nom").equals(nom)){
				return new Color(Integer.parseInt(stmt.executeQuery("select color from modeles where nom='"+nom+"'").getString("color")));
			}
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
		return new Color(0x999999);
	}
	
	public static String getNom(String chemin){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where chemin=\"" + chemin + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()!=false){
				return rs.getString("nom");
			}
		} catch (Exception e) {
			System.out.println("Erreur " + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
		return null;
	}
	
}