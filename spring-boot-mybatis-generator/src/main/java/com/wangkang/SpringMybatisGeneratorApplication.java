package com.wangkang;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@MapperScan(basePackages = {"com.wangkang.mapper"})
public class SpringMybatisGeneratorApplication {

	public static void main(String[] args) throws InterruptedException, SQLException, IOException {
		SpringApplication.run(SpringMybatisGeneratorApplication.class, args);

//		try {
//			System.out.println("**************start generator***************");
//			List<String> warnings =  new ArrayList<String>();
//			boolean overwrite = true;
//			File configFile = new File(SpringMybatisGeneratorApplication.class.getResource("classpath:generatorConfig.xml").getFile());
//			ConfigurationParser cp = new ConfigurationParser(warnings);
//
//			Configuration config =cp.parseConfiguration(configFile);
//			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//			MyBatisGenerator myBatisGenerator =new MyBatisGenerator(config, callback, warnings);
//			myBatisGenerator.generate(null);
//			System.out.println("*******************end generator*************");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (XMLParserException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InvalidConfigurationException e) {
//			e.printStackTrace();
//		}

	}

}

