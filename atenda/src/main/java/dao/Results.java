package dao;

import java.util.List;

public class Results<T> {
	private List<T> page = null;
	private int startindex = 0;
	private int total = 0;
	
	public Results(List<T> page, int startindex, int total) {
		super();
		this.page = page;
		this.startindex = startindex;
		this.total = total;
	}

	public List<T> getPage() {
		return page;
	}

	public void setPage(List<T> page) {
		this.page = page;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Results [page=" + page + ", startindex=" + startindex + ", total=" + total + "]";
	}
	
}
