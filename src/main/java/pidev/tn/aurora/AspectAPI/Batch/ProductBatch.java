package pidev.tn.aurora.AspectAPI.Batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pidev.tn.aurora.entities.Shop.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class ProductBatch {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private DataSource dataSource;


    /*----------------------[ CSV -> DB Scheduled]-------------------------*/
    @Bean
    public FlatFileItemReader<Product> readFromCsv() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<Product>();
        reader.setResource(new FileSystemResource("D://4SE5 2nd PART//PIDEV//Aurora//Aurora//src//main//resources//templates//assets//Product//price.csv"));
        reader.setLineMapper(new DefaultLineMapper<Product>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(Product.fields());
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>(){
                    {
                        setTargetType(Product.class);
                    }
                });
            }
        });
        return reader;
    }
    @Bean
    public JdbcBatchItemWriter<Product> writerIntoDB(){
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into product (id,description,model,name,price) values(:id,:description,:model,:name,:price) ON DUPLICATE KEY UPDATE price = VALUES(price)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
        return writer;
    }
    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<Product,Product>chunk(10)
                .reader(readFromCsv()).writer(writerIntoDB()).build();
    }
    @Bean
    public Job job() {
       return jobBuilderFactory.get("job").flow(step()).end().build();
    }

    /*-------------------------[DB -> Csv Run Program]---------------------*/
    @Bean
    public JdbcCursorItemReader<Product> reader(){
        JdbcCursorItemReader<Product> reader = new JdbcCursorItemReader<Product>();
        reader.setDataSource(dataSource);
        reader.setSql("select id,description,model,name,price from product");
        reader.setRowMapper(new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setModel(rs.getString("model"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                return p;
            }
        });
        return  reader;
    }
    @Bean
    public FlatFileItemWriter<Product> writer(){
        FlatFileItemWriter<Product> writer = new FlatFileItemWriter<Product>();
        writer.setResource(new FileSystemResource("D://4SE5 2nd PART//PIDEV//Aurora//Aurora//src//main//resources//templates//assets//Product//product.csv"));
        DelimitedLineAggregator<Product> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<Product> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id","description","model","name","price"});
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);
        return writer;
    }
   @Bean
    public Step executeStep(){
        return stepBuilderFactory.get("executeStep").<Product,Product>chunk(10).reader(reader()).writer(writer()).build();
    }

   @Bean
    public Job processJob(){
        return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).flow(executeStep()).end().build();
    }
}
