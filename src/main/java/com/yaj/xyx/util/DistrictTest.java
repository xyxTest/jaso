package com.yaj.xyx.util;

 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
 
import org.junit.Test;
 
/**
 * 如何根据对象的单个字段去重的三种方式
 * 2018年2月28日
 */
public class DistrictTest {
	
	
	Dog dog = new Dog("二哈", "哈哈", 3);
	List<Dog> dlist = Arrays.asList(
			new Dog("秋田犬", "产地1", 5), 
			new Dog("西施犬", "产地2", 5), 
			new Dog("柴犬", "产地3", 5),
			new Dog("秋田犬", "产地1", 5), 
			new Dog("阿拉斯加雪橇犬", "产地5", 5), 
			new Dog("阿拉斯加雪橇犬", "产地6", 6),
			new Dog("阿拉斯加雪橇犬", "产地7", 7), 
			dog, 
			dog);
 
 
 
	
	//方式3:使用TreeSet比较器的排序 来去重
	@Test
	public void testName4() throws Exception {
		List<Dog> list =new ArrayList<>(dlist);
		//这里添加一个比较器,根据getDname排序
		TreeSet<Dog> tree=new TreeSet<>(
				 Comparator.comparing(Dog::getDname)
				);
		tree.addAll(list);
//		tree.forEach(System.out::println);
		
		//可能你会想,我要List  方式1:使用addall
		list.clear();
		list.addAll(tree);
		list.forEach(System.out::println);
	}
	
	
	
	

}
 
class Dog {
	private String dname;
	private String dloc;
	private Integer dage;
 
	public String getDname() {
		return dname;
	}
 
	public void setDname(String dname) {
		this.dname = dname;
	}
 
	public String getDloc() {
		return dloc;
	}
 
	public void setDloc(String dloc) {
		this.dloc = dloc;
	}
 
	public Integer getDage() {
		return dage;
	}
 
	public void setDage(Integer dage) {
		this.dage = dage;
	}
 
	public Dog(String dname, String dloc, Integer dage) {
		super();
		this.dname = dname;
		this.dloc = dloc;
		this.dage = dage;
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + ((dage == null) ? 0 : dage.hashCode());
		// result = prime * result + ((dloc == null) ? 0 : dloc.hashCode());
		result = prime * result + ((dname == null) ? 0 : dname.hashCode());
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		// if (dage == null) {
		// if (other.dage != null)
		// return false;
		// } else if (!dage.equals(other.dage))
		// return false;
		// if (dloc == null) {
		// if (other.dloc != null)
		// return false;
		// } else if (!dloc.equals(other.dloc))
		// return false;
		if (dname == null) {
			if (other.dname != null)
				return false;
		} else if (!dname.equals(other.dname))
			return false;
		return true;
	}
 
	public Dog() {
		super();
	}
 
	@Override
	public String toString() {
		return "Dog [dname=" + dname + ", dloc=" + dloc + ", dage=" + dage + "]";
	}
 
}
