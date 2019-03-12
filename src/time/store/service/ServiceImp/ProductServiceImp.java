package time.store.service.ServiceImp;

import java.sql.SQLException;
import java.util.List;

import time.store.dao.ProductDao;
import time.store.dao.daoImp.ProductDaoImp;
import time.store.domain.PageModel;
import time.store.domain.Product;
import time.store.service.ProductService;

public class ProductServiceImp implements ProductService {

	@Override
	public List<Product> findNews() throws SQLException {
		ProductDao dao = new ProductDaoImp();
		return dao.findNews();
		
	}

	@Override
	public List<Product> findHots() throws SQLException {
		ProductDao dao = new ProductDaoImp();
		return dao.findHots();
	}

	@Override
	public Product findProductById(String pid) throws SQLException {
		ProductDao dao = new ProductDaoImp();
		return dao.findProductById(pid);
		
	}

	@Override
	public PageModel findProductsByCidWithPage(int num, String cid) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDaoImp();
		// 获取总的条目数
		int totalNum = dao.findTotalNum(cid);
		PageModel page = new PageModel(num,totalNum,12);
		List<Product> list = dao.findProductsByCidWithPage(page.getStartIndex(),page.getPageSize(),cid);
		page.setList(list);
		page.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return page;
	}

}
