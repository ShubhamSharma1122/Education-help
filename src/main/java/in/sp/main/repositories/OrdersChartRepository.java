package in.sp.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.sp.main.entities.Orders;

@Repository
public interface OrdersChartRepository extends JpaRepository<Orders, Long> {

	    // 1️⃣ Total sales amount per day
	    String SQL_QUERY1 = """
	        SELECT 
	            DATE(date_of_purchase) AS purchased_date,
	            SUM(course_amount) AS total_sales_amount
	        FROM orders
	        GROUP BY DATE(date_of_purchase)
	        ORDER BY purchased_date
	    """;

	    @Query(value = SQL_QUERY1, nativeQuery = true)
	    List<Object[]> findCoursesAmountTotalSales();


	    // 2️⃣ Total sales per course
	    String SQL_QUERY2 = """
	        SELECT 
	            course_name,
	            COUNT(*) AS total_sold
	        FROM orders
	        GROUP BY course_name
	    """;

	    @Query(value = SQL_QUERY2, nativeQuery = true)
	    List<Object[]> findCoursesTotalSales();


	    // 3️⃣ Courses sold per day
		@Query(value = """
				    SELECT
				        DATE(date_of_purchase) AS purchased_date,
				        COUNT(*) AS number_of_courses_sold
				    FROM orders
				    GROUP BY DATE(date_of_purchase)
				    ORDER BY purchased_date
				""", nativeQuery = true)


	    List<Object[]> findCoursesSoldPerDay();
	}
