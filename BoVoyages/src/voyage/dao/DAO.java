package voyage.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.sun.glass.ui.GestureSupport;

/**
 * <b>DAO est une classe contenant les informations de connexion à la base de données mysql.</b>
 * <p>
 * La classe DAO peut être instanciée de deux façons :
 * <ul>
 * 	<li>Soit en lui fournissant un DataSource comme argument (i.e., instanciée sur un serveur)</li>
 * 	<li>Soit en lui fournissant toutes les informations de connexion (i.e., instanciée localement)</li>
 * 		Il faut alors fournir le nom du driver, l'url de connexion à la base de données, le nom d'utilisateur
 * 		et le mot de passe.
 * </ul>
 * 
 * Cette classe fournit deux méthodes : {@link DAO#getConnection()} pour récupérer une connexion
 * (physique ou logique) à la base de données, et {@link DAO#close(Connection)} pour refermer
 * cette connexion.
 * 
 * @author Adminl
 * @version 2.0
 *
 */
public class DAO {
	private String user;
	private String pswd;
	private String driverName;
	private String url;
	private DataSource ds;
	
	/**
	 * Utilisation d'un {@link javax.sql.DataSource} pour récupérer une connection logique
	 * à la base de données
	 *  
	 * @param ds
	 */
	public DAO(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * Utilisation des paramètres de connexion bruts pour récupérer une connexion physique
	 * à la base de données.
	 * @param driverName Le nom du driver
	 * @param url L'url de connexion
	 * @param user Le nom d'utilisateur
	 * @param pswd Le mot de passe
	 * @throws ClassNotFoundException Si le driver ne peut pas être injecté.
	 */
	public DAO(String driverName, String url, String user, String pswd) throws ClassNotFoundException {
		this.driverName = driverName;
		this.url = url;
		this.user = user;
		this.pswd = pswd;
		init();
	}

	/**
	 * Constructeur par défaut, qui récupère automatiquement les informations de connexion dans un
	 * fichier de configuration <code>dao.properties</code> situé à la racine du projet.
	 * @throws ClassNotFoundException Si le driver ne peut pas être injecté
	 * @throws IOException Si le fichier de configuration ne peut pas être ouvert
	 */
	public DAO() throws ClassNotFoundException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("dao.properties");
		prop.load(in);
		this.driverName = prop.getProperty("jdbc.driver");
		this.url = prop.getProperty("jdbc.url");
		this.user = prop.getProperty("jdbc.user");
		this.pswd = prop.getProperty("jdbc.password");
		init();
		in.close();
	}

	/**
	 * Cette méthode permet de récupérer une connexion à la base de données, que ce soit sur un 
	 * serveur ou en local.
	 * 
	 * @return Une {@link java.sql.Connection} à la base de données
	 * @throws SQLException Si le {@link javax.sql.DataSource} ne peut pas récupérer la connexion.
	 */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		if (ds == null){
			connection = DriverManager.getConnection(url, user, pswd);
		} else {
			// Si le datasource existe, on est sur un serveur
			connection = ds.getConnection();
		}
		return connection;
	}

	/**
	 * Referme la connexion.
	 * 
	 * @param connection La connexion à refermer 
	 * @throws SQLException Si la connexion ne peut pas être refermée
	 */
	public void close(Connection connection) throws SQLException {
		connection.close();
	}

	/**
	 * Injecte dynamiquement le driver.
	 * 
	 * @throws ClassNotFoundException
	 */
	private void init() throws ClassNotFoundException {
		Class.forName(driverName);
	}

}