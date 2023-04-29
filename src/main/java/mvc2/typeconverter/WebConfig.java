package mvc2.typeconverter;

import mvc2.typeconverter.converter.IntegerToStringConverter;
import mvc2.typeconverter.converter.IpPortToStringConverter;
import mvc2.typeconverter.converter.StringToIntegerConverter;
import mvc2.typeconverter.converter.StringToIpPortConverter;
import mvc2.typeconverter.formatter.NumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        // 우선순위 때문에 주석처리
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        //추가
        registry.addFormatter(new NumberFormatter());
    }
}
