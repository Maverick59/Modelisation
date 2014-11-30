import java.sql.*;

public class gestionBDD {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Connection con = null;
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:bdd_models");
			Statement stmt=con.createStatement();
			String query = "select * from modeles";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String nom=rs.getString("nom"), chemin=rs.getString("chemin"), chemin_screen=rs.getString("chemin_screen"), descriptif=rs.getString("descriptif");
				int nb_points=rs.getInt("nb_points"), nb_segments=rs.getInt("nb_segments"), nb_faces=rs.getInt("nb_faces");
				System.out.println("Nom: "+nom+", chemin: "+chemin+", chemin_screen: "+chemin_screen+", descriptif: "+descriptif+", points: "+nb_points+", segments: "+nb_segments+", faces: "+nb_faces);
			}
		}catch(Exception e){
			System.out.println("Erreur "+e);
		}finally{
			try{
				con.close();
			}catch(Exception e){
				
			}
		}
	}
}
