package model;

import org.apache.ibatis.annotations.*;
import java.util.List;
public interface ItemMapper {
    @Select("SELECT * FROM items")
    List<Item> getAllItems();

    @Insert("INSERT INTO items (name, weight, qty, price) VALUES (#{name}, #{weight}, #{qty}, #{price})")
    void insertItem(Item item);

    @Update("UPDATE items SET name = #{name}, weight = #{weight}, qty = #{qty}, price = #{price} WHERE id = #{id}")
    void updateItem(Item item);

    @Delete("DELETE FROM items WHERE id = #{id}")
    void deleteItemById(int id);
}

