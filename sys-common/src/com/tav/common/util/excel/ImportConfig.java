package com.tav.common.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation định nghĩa các tham số để mapping giữa một đối tượng với row của
 * excel
 * 
 * @author pmdn_tutm3
 * @Time: Mar 20, 2013
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportConfig {
	public static final int TYPE_NOTHING = 0;
	public static final int TYPE_BETWEEN = 1;
	public static final int TYPE_LOWER = 2;
	public static final int TYPE_GREATER = 3;
	public static final int TYPE_ONE_OF = 4;

	/**
	 * Column tương ứng trong file import
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 20, 2013
	 * @return
	 */
	int column() default -1;

	/**
	 * Tên cột/tên thuộc tính
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 21, 2013
	 * @return
	 */
	String name() default "";

	/**
	 * Có bắt buộc không
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 20, 2013
	 * @return
	 */
	boolean require() default false;

	/**
	 * Độ dài tối đa
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 20, 2013
	 * @return
	 */
	int maxlength() default -1;

	/**
	 * Độ dài tối thiểu
	 * 
	 * @author tutm3
	 * @Date: Dec 23, 2013
	 * @return
	 */
	int minlength() default 0;

	/**
	 * Ràng buộc định dạng (Regular Expression)
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 21, 2013
	 * @return
	 */
	String constrain() default "";

	/**
	 * Kiểu validate dữ liệu
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 20, 2013
	 * @return
	 */
	int validateType() default TYPE_NOTHING;

	/**
	 * Giá trị tham chiếu khi validate
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 20, 2013
	 * @return
	 */
	String[] validateValues() default {};

	/**
	 * Có cho phép giống nhau không
	 * 
	 * @author pmdn_tutm3
	 * @Time: Mar 21, 2013
	 * @return
	 */
	boolean duplicate() default true;
}
