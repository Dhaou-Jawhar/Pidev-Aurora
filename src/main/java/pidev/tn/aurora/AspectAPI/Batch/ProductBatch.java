package pidev.tn.aurora.AspectAPI.Batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import pidev.tn.aurora.entities.Shop.Product;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class ProductBatch {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private DataSource dataSource;

    @Bean
    public FlatFileItemReader<Product> readFromCsv() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<Product>();
        reader.setResource(new FileSystemResource("D://4SE5 2nd PART//PIDEV//Aurora//Aurora//src//main//resources//templates//assets//price.csv"));
        //reader.setResource(new ClassPathResource("csv_input.csv"));
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
        writer.setSql("insert into product (id,price) values (:id,:price)");
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
}
