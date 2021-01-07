package com.dev;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
class ExpenseTrackerApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	public static void main(String[] args) throws IllegalStateException, IOException, ParseException {
//		Resource resource = new ClassPathResource("test.csv");
////		InputStream is = this.getClass().getResourceAsStream("test.csv");
////		Reader reader = new InputStreamReader(new FileReader(resource.getFile()));
//		List<User> list = new CsvToBeanBuilder(new FileReader(resource.getFile())).withType(User.class).build().parse();
//
//		list.stream().forEach(System.out::println);

		Date date = new Date();
		System.out.println(date);

		Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2020 14:27:30");
		System.out.println(d1);

		String year = new SimpleDateFormat("MMM").format(d1);
		System.out.println(year);

	}

}
