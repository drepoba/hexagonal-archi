package drepoba.configuration;
import ddd.DomainService;
import ddd.Stub;
import drepoba.domain.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = {Product.class},
        //basePackages = "drepoba.repository",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, Stub.class}
                )})
        //excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {StarShipInventoryStub.class})})
public class DomainConfiguration {
}
