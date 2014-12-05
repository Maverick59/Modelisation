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
			String query = "INSERT INTO modeles  values('" + nom + "','" + gts + "','" + png + "','', '" + model.points.size() + "','" + model.segments.size() + "','" + model.faces.size()
					+ "','255/255/255')";
			ResultSet rs = stmt.executeQuery(query);
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