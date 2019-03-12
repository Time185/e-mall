package time.store.service;

import java.sql.SQLException;

import time.store.domain.CartItem;

public interface CartService {

	CartItem findCartItemById(String pid, String num) throws SQLException;

}
