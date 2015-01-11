package utilitaire;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import petiteFonction.ChangerWorkspace;
import model.Charger;
import model.Model3D;
import fenetre.Ecran;

public class GestionBDD {
	
	/**
	 * met a jour la BDD en ajoutant les modeles qui ne sont pas encore presents dans celle ci en appelant la methode insert, et supprime toutes les images
	 * qui se re-regenereront au prochain lancement du programme
	 * @param e l'ecran du programme auquel on applique la methode
	 */
	public static void updateDB(Ecran e){
		int reply = JOptionPane.showConfirmDialog(null, "Voulez vous mettre a jour la base de donnee ?");
	    if (reply == JOptionPane.YES_OPTION){
			File file = new File("model");
			for (File f : file.listFiles()) {
				try {
					GestionBDD.insertModel(f.getPath());
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			File img = new File("img");
			for (File f : img.listFiles()) {
				f.delete();
			}
			e.getBarreAjout().ajouterModels(GestionBDD.searchModel(""));
			JOptionPane.showMessageDialog(null, "Redemarrez le programme pour appliquer les changements");
	    }
	}
	
	/**
	 * vide la table de la BDD passee en parametre
	 * @param string la table a vider
	 */

	private static void clearTable(String string) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "Delete from " + string + " where 1==1";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
	}
	
	/**
	 * renvoie tous les modeles qui correspondent au(x) critere(s) donnes par le parametre
	 * @param hashtags une chaine contenant un ou plusieurs criteres de recherche, voir aucun. Ces criteres peuvent etre: 
	 * aucune, une ou plusieurs limitation(s) en nombre de points (avec un operateur inferieur, superieur ou egal suivi du nombre de points) 
	 * aucune, une ou plusieurs chaines de caracteres qui correspondront aux modeles voulus (soit le nom, soit un tag, soit des lettres etc..)
	 * @return une ArrayList contenant tous les modeles qui correspondent aux criteres de recherche
	 */
	public static ArrayList<String> searchModel(String hashtags) {
		String[] s;
		boolean chiffres = true;
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<String> limitesPoints = new ArrayList<String>();
		ArrayList<String> res = new ArrayList<String>();
		s = hashtags.split("[ ]");
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 0 && s[i].charAt(0) != '<' && s[i].charAt(0) != '>' && s[i].charAt(0) != '=') {
				tags.add(s[i]);
			} else if (s[i].length() > 0 && (s[i].charAt(0) == '<' || s[i].charAt(0) == '>' || s[i].charAt(0) == '=')) {
				for (int j = 0; j < s[i].substring(1, s[i].length()).length(); j++) {
					if ((s[i].charAt(j + 1) < 48) || (s[i].charAt(j + 1) > 57)) {
						chiffres = false;
						System.out.println((int) s[i].charAt(j + 1));
					}
				}
				if (s[i].length() > 1 && chiffres && Integer.parseInt(s[i].substring(1, s[i].length())) > 0) {
					limitesPoints.add(s[i]);
				}
				chiffres = true;
			}
		}
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query;
			if (tags.size() > 0) {
				query = "select chemin from modeles where chemin in(select chemin from modeles where nom in(select nom from correspondances where tag like ";
				for (int i = 0; i < tags.size(); i++) {
					query += "'%" + tags.get(i) + "%' ";
					if (i <= tags.size() - 2) {
						query += "or tag like ";
					}
				}
				query += ") ";
				query += "or nom like ";
				for (int i = 0; i < tags.size(); i++) {
					query += "'%" + tags.get(i) + "%' ";
					if (i <= tags.size() - 2) {
						query += "or nom like ";
					}
				}
				query += ")";
				if (limitesPoints.size() > 0) {
					// ajout nombre de points limites comme critere
					// supplementaire(par exemple utilisateur tape <500)
					for (int i = 0; i < limitesPoints.size(); i++) {
						query += " and nb_points";
						query += limitesPoints.get(i).charAt(0) + limitesPoints.get(i).substring(1, limitesPoints.get(i).length());
					}
				}
			} else {
				//pas de critere(s) de recherche par hashtag/nom
                //(selectionne tout les modeles)
				query = "select chemin from modeles";
				// critere(s) de recherche par nombre de points present, ajout
				// d'un where pour limiter les modeles par rapport au(x)
				// critere(s) donne(s)
				if (limitesPoints.size() > 0) {
					query += " where ";
					for (int i = 0; i < limitesPoints.size(); i++) {
						if (i > 0) {
							query += " and ";
						}
						query += "nb_points";
						query += limitesPoints.get(i).charAt(0) + limitesPoints.get(i).substring(1, limitesPoints.get(i).length());
					}
				}
			}
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				res.add(rs.getString("chemin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * renvoie le chemin de l'image d'un modele
	 * @param gts le chemin du modele
	 * @return le chemin vers son image
	 */
	public static String searchPNG(String gts) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select chemin_screen from modeles where chemin='" + gts + "'");
			if (rs.next() != false) {
				return rs.getString("chemin_screen");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ajoute le modele passe en parametre dans la table des modeles, avec toutes les informations necessaires (nom, couleur par defaut, nombre de points; segments et faces etc...)
	 * @param gts, le chemin du modele a ajouter
	 */
	public static void insertModel(String gts) {
		Connection con = null;
		try {
			String nom = gts.replace(".gts", "");
			nom = nom.split("/")[nom.split("/").length - 1];
			Model3D model = Charger.chargerModel(Parametre.workspace + "/"+gts);
			String png = "img/" + nom + ".png";
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "INSERT INTO modeles  values('" + nom + "','" + gts + "','" + png + "','', '" + model.getPoints().size() + "','" + model.getSegments().size()
					+ "','" + model.getFaces().size() + "','" + 0xffffff + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ajoute une correspondance entre nom et hashtag. cree hashtag s'il n'existe pas encore. 
	 * Ne fait rien si le nom du modele ne correspond a aucun modele existant dans la base
	 * @param nom le nom du modele
	 * @param hashtag le tag qu'on veut lui ajouter
	 */
	public static void addConnection(String nom, String hashtag) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			ResultSet rs;
			ResultSet rs2;
			ResultSet rs3;
			rs = stmt.executeQuery("select nom from modeles where nom='" + nom + "'");
			if (rs.next() != false && rs.getString("nom").equals(nom)) {
				insertTag(hashtag);
				rs3 = stmt.executeQuery("select nom from correspondances where nom='" + nom + "' and tag='" + hashtag + "'");
				if (rs3.next() != false && rs3.getString("nom").equals(nom)) {
					System.out.println("cette correspondace existe deja");
				} else {
					System.out.println("insertion dan la table de correspondances");
					stmt.executeUpdate("insert into correspondances values('" + hashtag + "', '" + nom + "')");
				}
			} else {
				System.out.println("ce modele n'existe pas");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * definit la couleur de base d'un modele. Ne fait rien si le nom du modele passe en parametre n'est lie a aucun modele connu de la base
	 * @param nom le nom du modele duquel on veut editer la couleur de base
	 * @param color la couleur debase a lui definir
	 */
	public static void setColor(String nom, int color) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where nom=\"" + nom + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next() != false && rs.getString("nom").equals(nom)) {
				stmt.executeUpdate("update modeles set color='" + color + "' where nom='" + nom + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * renseigne la couleur de base d'un modele
	 * @param nom, le nom du modele duquel on veut connaitre la couleur
	 * @return la couleur du modele correspondant au parametre
	 */
	public static Color getColor(String nom) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where nom=\"" + nom + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next() != false && rs.getString("nom").equals(nom)) {
				return new Color(Integer.parseInt(stmt.executeQuery("select color from modeles where nom='" + nom + "'").getString("color")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new Color(0x999999);
	}

	/**
	 * renseigne le nom d'un modele
	 * @param chemin, le chemin du modele
	 * @return nom, le nom correspondant au modele
	 */
	public static String getName(String chemin) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from modeles where chemin=\"" + chemin + "\"";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next() != false) {
				return rs.getString("nom");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * renvoie tout les tags existants dans la base de donnees
	 * @return un ArrayList contenant tous les tags
	 */
	public static ArrayList<String> selectAllTags() {
		Connection con = null;
		ArrayList<String> l = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "select tag from hashTags";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				l.add(rs.getString("tag"));
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return l;
	}

	/**
	 * supprime une correspondance entre un modele et un tag
	 * @param model, le modele duquel on veut supprimer sa relation avec un tag
	 * @param tag, le tag a supprimer pointant sur le modele
	 */
	public static void removeConnection(String model, String tag) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "delete from correspondances where nom='" + model + "' and tag='" + tag + "'";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ajoute un tag dans la table hashTags s'il n'existe pas encore
	 * @param hashtag, le tag a rajouter
	 */
	public static void insertTag(String hashtag) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select tag from hashTags where tag='" + hashtag + "'");
			;
			if (rs.next() != false && rs.getString("tag").toString().equals(hashtag)) {
				System.out.println("ce tag existe deja et ne sera donc pas cree");
			} else {
				System.out.println("ce tag va etre cree");
				stmt.executeUpdate("insert into hashTags values('" + hashtag + "')");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * supprime un completement un tag de la base de donnees
	 * (le supprime de la table hashTags ainsi que toutes ses correspondances avec les modeles)
	 * @param tag, le tag a supprimer
	 */
	public static void deleteTag(String tag) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "delete from correspondances where tag='" + tag + "'";
			stmt.executeUpdate(query);
			query = "delete from hashTags where tag='" + tag + "'";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * renvoie tous les noms de modeles ayant une correspondance avec le tag passe en parametre
	 * @param tag, le tag avec lequel la recherche est faite
	 * @return une ArrayList contenant les noms des modeles pour lesquels une correspondance existe avec le tag
	 */
	public static ArrayList<String> searchByTag(String tag) {
		Connection con = null;
		ArrayList<String> l = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + Parametre.workspace + "/bdd_models");
			Statement stmt = con.createStatement();
			String query = "select nom from correspondances where tag='" + tag + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				l.add(rs.getString("nom"));
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return l;
	}
	
	/**
	  * enleve toutes les correspondances du projet, hashtags et modeles
	  */
	public static void clearDB(){
	
			ChangerWorkspace.changer();
			clearTable("correspondances");
			clearTable("hashTags");
			clearTable("modeles");
		
	}

}

