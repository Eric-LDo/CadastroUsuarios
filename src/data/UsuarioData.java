package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.UsuarioModel;

public class UsuarioData extends Conexao implements CRUD {
    public UsuarioData() throws Exception{}

    @Override
    public boolean editar(UsuarioModel obj) throws Exception {
        String sql="update tbusuarios set nome=?,email=?,senha=? where id=?";
        PreparedStatement ps = getCon().prepareStatement(sql);
        ps.setString(1, obj.getNome());
        ps.setString(2, obj.getEmail());
        ps.setString(3, obj.getSenha());
        ps.setInt(4,obj.getId());
        return (ps.executeUpdate()>0) ? true : false;
    }
    
    @Override
    public boolean excluir(int id) throws Exception {
        String sql="delete from tbusuarios where id=?";
        PreparedStatement ps = getCon().prepareStatement(sql);
        ps.setInt(1, id);
        return (ps.executeUpdate()>0) ? true : false;
    }

    @Override
    public boolean incluir(UsuarioModel obj) throws Exception {
        //     @Override
        // public boolean incluir(Object o) throws Exception {
        //     if(o instanceof Casual){
        //         Casual obj = (Casual)o;
        //         //casting
        //         //chama a procedure
        //         System.out.println(obj);
        //         return true;    
        //     }
        //     if(o instanceof Esportivo){
        //         Esportivo obj = (Esportivo)o;
        //         //chama a procedure
        //         System.out.println(obj);
        //         return true;    
        //     }
        //     return false;
        // }
        String sql="insert into tbusuarios (nome,email,senha) values(?,?,?)";
        PreparedStatement ps = getCon().prepareStatement(sql);
        ps.setString(1, obj.getNome());
        ps.setString(2, obj.getEmail());
        ps.setString(3, obj.getSenha());
        return (ps.executeUpdate()>0) ? true : false; 
    }
    
    @Override
    public ArrayList<UsuarioModel> listar(String pesquisa) throws Exception {
        String sql="select * from tbusuarios where nome like '"+pesquisa+"%' or email like '"+pesquisa+"%' order by nome";
        PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<UsuarioModel> lista = new ArrayList<>();
        while(rs.next()){
            UsuarioModel obj = new UsuarioModel(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
            lista.add(obj);
        }
        return lista;
    }
    
    @Override
    public UsuarioModel obter(int id) throws Exception {
        String sql = "Select * from tbusuarios where id=?";
        PreparedStatement ps = getCon().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        UsuarioModel obj = null;
        if (rs.next()){
            obj = new UsuarioModel(rs.getInt("id"),rs.getString("nome"),rs.getString("email"),rs.getString("senha") );
        }
        return obj;
    }
    
}
