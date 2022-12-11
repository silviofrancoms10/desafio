package br.com.imagemfilmes.desafio.dao;

import br.com.imagemfilmes.desafio.entity.Pedido;
import br.com.imagemfilmes.desafio.entity.PedidoItem;
import br.com.imagemfilmes.desafio.entity.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends DAO{

    public PedidoDAO(Connection connection) {
        super(connection);
    }

    public List<Pedido> findAll() throws SQLException{
        try (PreparedStatement psmt = getConnection()
                .prepareStatement(""" 
                        SELECT * FROM pedido p
                        INNER JOIN pedido_item pi ON p.id = pi.pedido_id
                        INNER JOIN produto prod ON pi.produto_id = prod.id
                        INNER JOIN pessoa pes ON p.pessoa_id = pes.id """ )){
            try (ResultSet rs = psmt.executeQuery()){
                final List<Pedido> pedidos = new ArrayList<>();
                while (rs.next()){
                    final Pedido pedido = new Pedido()
                            .setId((long) rs.getInt("id"));

                    final Pessoa pessoa = new Pessoa()
                            .setId(rs.getLong("id"))
                            .setCpf(rs.getString("cpf"))
                            .setNome(rs.getString("nome"));



                    pedido.setPessoa(pessoa);
                    pedidos.add(pedido);




                }
                return pedidos;
            }

        }
    }

}
