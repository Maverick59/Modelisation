import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionBDD {

	public static void main(String[] args) throws IOException {
		File file = new File("model");
		for (File f : file.listFiles()) {
			System.out.println(f.getPath());
			GestionBDD.insert(f.getPath());	
		}
		setColor("squirtle", "255/255/253");
		insertHashTag("squirtle", "pokemon");
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
				query = "select chemin from modeles where nom in(select nom from correspondances where tag like ";
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
			String query = "select chemin_screen from modeles where chemin=\"" + gts + "\"";
			ResultSet rs = stmt.executeQuery(query);
			return rs.getString("chemin_screen");
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

	public static void insert(String gts) {
		Connection con = null;
		String nom = gts.replace(".gts", "");
		nom = nom.split("/")[nom.split("/").length - 1];
		try {
			Model3D model = Charger.chargerModel(gts);
			String png = "img/" + nom + ".png";
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			if(!stmt.executeQuery("select chemin from modeles where chemin='"+gts+"'").getString("chemin").equals(gts)){
				String query = "INSERT INTO modeles  values('" + nom + "','" + gts + "','" + png + "','', '" + model.points.size() + "','" + model.segments.size() + "','" + model.faces.size()
					+ "','255/255/255')";
				stmt.executeUpdate(query);
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
	
	public static void insertHashTag(String nom, String hashtag){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			ResultSet rs;
			ResultSet rs2;
			ResultSet rs3;
			rs=stmt.executeQuery("select nom from modeles where nom='"+nom+"'");
			rs.next();
			if(rs.getString("nom").equals(nom)){
				System.out.println("1");
				rs2=stmt2.executeQuery("select tag from hashTags where tag='"+hashtag+"'");
				rs2.next();
				if(rs2.getString("tag").equals(hashtag)){
					System.out.println("3");
					System.out.println("ce tag existe deja et ne sera donc pas créé");
				}else{
					System.out.println("2");
					System.out.println("ce tag va etre créé");
					stmt.executeUpdate("insert into hashTags values('"+hashtag+"')");
				}
				rs3=stmt3.executeQuery("select nom from correspondances where nom='"+nom+"' and tag='"+hashtag+"'");
				rs3.next();
				if(rs3.getString("nom").equals(nom)){
					System.out.println("5");
					System.out.println("cette correspondace existe deja");
				}else{
					System.out.println("4");
					System.out.println("insertion dan la table de correspondances");
					stmt.executeUpdate("insert into correspondances values('"+hashtag+"', '"+nom+"')");
				}
			}else{
				System.out.println("6");
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
	
	public static void setColor(String nom, String color){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where nom=\"" + nom + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.getString("nom").equals(nom)){
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
	
}