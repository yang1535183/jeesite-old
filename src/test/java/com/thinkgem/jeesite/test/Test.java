package com.thinkgem.jeesite.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


public class Test implements Converter<String , Date> {
	private static final Logger LOGGER= LoggerFactory.getLogger(Test.class);
	public String  datePattern="yyyy-MM-dd HH:mm:ss";

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			LOGGER.error("对日期进行格式转换异常！期望的格式：{}",datePattern,e);
		}
		return null;
	}

	public static void main(String[] args) {
		//被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
		Person zhangsan = new Student("张三");

		//生成代理对象，并将张三传给代理对象
		Person monitor = new StudentsProxy(zhangsan);

		//班长代理上交班费
		monitor.giveMoney();
	}
}

/**
 * 创建Person接口
 * @author Gonjan
 */
interface Person {
	//上交班费
	void giveMoney();
}

class Student implements Person {
	private String name;
	public Student(String name) {
		this.name = name;
	}

	@Override
	public void giveMoney() {
		System.out.println(name + "上交班费50元");
	}
}

/**
 * 学生代理类，也实现了Person接口，保存一个学生实体，这样既可以代理学生产生行为
 * @author Gonjan
 *
 */
class StudentsProxy implements Person{
	//被代理的学生
	Student stu;

	public StudentsProxy(Person stu) {
		// 只代理学生对象
		if(stu.getClass() == Student.class) {
			this.stu = (Student)stu;
		}
	}

	//代理上交班费，调用被代理学生的上交班费行为
	public void giveMoney() {
		System.out.printf("班长代缴");
		stu.giveMoney();
	}
}
