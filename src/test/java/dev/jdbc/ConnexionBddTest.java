package dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ConnexionBddTest {

	@Test
	public void test_select_where() {
		// Informations de connexion à une base de données

		// Url Jdbc d'accès à la base
		String jdbcUrl = "jdbc:mysql://localhost:3306/formation-jpa";

		// Nom utilisateur d'accès à la base de données
		String nomUtilisateur = "root";

		// Mot de passe d'accès à la base de données
		String motDePasse = "root";

		String nomPizza = "Pizza 7";

		try (// ici toutes les variables qui sont de types qui implémentent l'interface
				// AutoCloseable

				// Création d'une connexion à la base de données à partir du gestionnaire de
				// pilotes.
				Connection connexion = DriverManager.getConnection(jdbcUrl, nomUtilisateur, motDePasse);

				// Création PrepareStatement (instruction SQL à exécuter)
				PreparedStatement statement = connexion.prepareStatement("select * from PIZZA where NAME=?");

		) {

			// valorisation du paramètre ?
			statement.setString(1, nomPizza);

			// Récupération du curseur de résultat de l'exécution de la requête SQL
			try (ResultSet resultSet = statement.executeQuery()) {

				List<Pizza> pizzas = new ArrayList<Pizza>();

				// déplacement du curseur
				// => return true s'il y a un prochain élément, false sinon
				// tant qu'il y a une prochaine ligne de résultat à exploiter
				while (resultSet.next()) {

					Long id = resultSet.getLong("ID");
					String nom = resultSet.getString("NAME");

					Pizza pizza = new Pizza();
					pizza.setId(id);
					pizza.setNom(nom);

					pizzas.add(pizza);
				}

				assert !pizzas.isEmpty();

				pizzas.forEach(System.out::println);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur BDD");
		}

	}

	@Test
	public void test_connexion_bdd() {
		// Informations de connexion à une base de données

		// Url Jdbc d'accès à la base
		String jdbcUrl = "jdbc:mysql://localhost:3306/formation-jpa";

		// Nom utilisateur d'accès à la base de données
		String nomUtilisateur = "root";

		// Mot de passe d'accès à la base de données
		String motDePasse = "root";

		try (// ici toutes les variables qui sont de types qui implémentent l'interface
				// AutoCloseable

				// Création d'une connexion à la base de données à partir du gestionnaire de
				// pilotes.
				Connection connexion = DriverManager.getConnection(jdbcUrl, nomUtilisateur, motDePasse);

				// Création Statement (instruction SQL à exécuter)
				Statement statement = connexion.createStatement();

				// Récupération du curseur de résultat de l'exécution de la requête SQL
				ResultSet resultSet = statement.executeQuery("select * from PIZZA");) {

			List<Pizza> pizzas = new ArrayList<Pizza>();

			// déplacement du curseur
			// => return true s'il y a un prochain élément, false sinon
			// tant qu'il y a une prochaine ligne de résultat à exploiter
			while (resultSet.next()) {

				Long id = resultSet.getLong("ID");
				String nom = resultSet.getString("NAME");

				Pizza pizza = new Pizza();
				pizza.setId(id);
				pizza.setNom(nom);

				pizzas.add(pizza);
			}

			assert !pizzas.isEmpty();

			pizzas.forEach(System.out::println);

		} catch (SQLException e) {
			System.out.println("Erreur BDD");
		}

	}

	@Test
	public void test_connexion_bdd_old() {
		// Informations de connexion à une base de données

		// Url Jdbc d'accès à la base
		String jdbcUrl = "jdbc:mysql://localhost:3306/formation-jpa";

		// Nom utilisateur d'accès à la base de données
		String nomUtilisateur = "root";

		// Mot de passe d'accès à la base de données
		String motDePasse = "root";

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			// Création d'une connexion à la base de données à partir du gestionnaire de
			// pilotes.
			connexion = DriverManager.getConnection(jdbcUrl, nomUtilisateur, motDePasse);

			// Création Statement (instruction SQL à exécuter)
			statement = connexion.createStatement();

			// Récupération du curseur de résultat de l'exécution de la requête SQL
			resultSet = statement.executeQuery("select * from PIZZA");

			List<Pizza> pizzas = new ArrayList<Pizza>();

			// déplacement du curseur
			// => return true s'il y a un prochain élément, false sinon
			// tant qu'il y a une prochaine ligne de résultat à exploiter
			while (resultSet.next()) {

				Long id = resultSet.getLong("ID");
				String nom = resultSet.getString("NAME");

				Pizza pizza = new Pizza();
				pizza.setId(id);
				pizza.setNom(nom);

				pizzas.add(pizza);
			}

			assert !pizzas.isEmpty();

			pizzas.forEach(System.out::println);

		} catch (SQLException e) {
			System.out.println("Erreur BDD");
		} finally {
			// Quitter le programme dignement !
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				System.out.println("Erreur BDD");
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				System.out.println("Erreur BDD");
			}
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException e) {
				System.out.println("Erreur BDD");
			}

		}

	}

}
