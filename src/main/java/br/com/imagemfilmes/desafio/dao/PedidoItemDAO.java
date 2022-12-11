package br.com.imagemfilmes.desafio.dao;

import br.com.imagemfilmes.desafio.entity.PedidoItem;
import br.com.imagemfilmes.desafio.entity.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoItemDAO extends DAO {

    public PedidoItemDAO(Connection connection) {
        super(connection);
    }

    public List<PedidoItem> findAll() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("""
                    SELECT * FROM pedido_item pi
                    INNER JOIN  produto prod ON pi.pedido_id = prod.id""")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<PedidoItem> pedidoItems = new ArrayList<>();
                while (rs.next()) {
                    final PedidoItem pedidoItem = new PedidoItem()
                            .setId(rs.getInt("id"))
                            .setQuantidade(rs.getInt("quantidade"));
                    final Produto produto = new Produto()
                            .setRegistro(rs.getInt("id"))
                            .setDescricao(rs.getString("descricao"))
                            .setValorUnitario(rs.getBigDecimal("valor_unitario"));



                    pedidoItem.setProduto(produto);
                    pedidoItems.add(pedidoItem);
                }
                return pedidoItems;
            }

        }
    }
}

