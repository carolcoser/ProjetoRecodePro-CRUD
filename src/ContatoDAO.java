import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;


public class ContatoDAO {
	public void save(Contato contato) {
		String sql = "INSERT INTO contato (nome, idade, dataCadastro)" + "VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void removeById(int id) {
		String sql = "DELETE FROM contato WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Contato contato) {
		String sql = "UPDATE contato SET nome = ?, idade = ?, dataCadastro = ?" + "WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setInt(4, contato.getID());
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> getContatos() {
		String sql = "SELECT * FROM contato";
		Connection conn = null;
		PreparedStatement pstm = null;

		List<Contato> contatos = new ArrayList<Contato>();
		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados, faça
			while (rset.next()) {

				Contato contato = new Contato();

				// Recupera o id do banco e atribui ele ao objeto
				contato.setID(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				contato.setNome(rset.getString("nome"));

				// Recupera a idade do banco e atribui ele ao objeto
				contato.setIdade(rset.getInt("idade"));

				// Recupera a data do banco e atribui ela ao objeto
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				// Adiciono o contato recuperado, a lista de contatos
				contatos.add(contato);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rset != null) {

					rset.close();
				}

				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return contatos;
	}


public Contato buscarID(int id) {
	String sql = "SELECT * FROM contato WHERE id = ?";
	Contato contato = new Contato();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;

	try {
		conn = Conexao.createConnectionToMySQL();
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rset = pstm.executeQuery();
		rset.next();
		contato.setNome(rset.getString("nome"));
		contato.setIdade(rset.getInt("idade"));
		contato.setDataCadastro(rset.getDate("dataCadastro"));
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return contato;
}
}
