import java.sql.*;
import java.util.*;

public class CategoriaDAO {
    public void inserir (Categoria categoria) {
        String comandoSql = "INSERT INTO categoria (nome, ativo) VALUES (?,?)";

        try(Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(comandoSql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getAtivo());
            stmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Não foi possível inserir a categoria!");
            e.printStackTrace();
        }
    }

    public List<Categoria> listar(){
        List<Categoria> lista = new ArrayList<>();
        String comandoSql = "SELECT * FROM categoria";

        try (Connection connection = Conexao.conectar(); Statement stmt = connection.createStatement(); ResultSet result = stmt.executeQuery(comandoSql)){
            while (result.next()){
                Categoria categoria = new Categoria(result.getString("nome"), result.getString("ativo"));
                categoria.setId(result.getInt("id"));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível listar as categorias disponíveis!");
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Categoria categoria){
        String comandoSql = "UPDATE categoria SET nome=?, ativo=? WHERE id=?";

        try (Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(comandoSql)){
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getAtivo());
            stmt.setInt(3, categoria.getId());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Não foi possível atualizar a categoria!");
            e.printStackTrace();
        }
    }

    public void deletar(int id){
        String comandoSql = "DELETE FROM categoria WHERE id=?";

        try(Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(comandoSql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Não foi possível deletar essa categoria!");
            e.printStackTrace();
        }
    }
}